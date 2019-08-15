package rocketmq.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import rocketmq.constants.MqConstant;
import rocketmq.domain.MessageLogDo;
import rocketmq.service.MessageLogService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Wangjiongda
 * @date 2019-08-13 21:22
 * 初始化messageLog consumer
 */
@Component
public class MessageLogConsumer {

    private static DefaultMQPushConsumer consumer;

    private static boolean started = false;

    @Autowired
    private MessageLogService messageLogService;

    /**
     * 初始化消费者
     */
    @PostConstruct
    public void initConsumer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" initConsumer 开启");
                if (consumer != null) {
                    System.out.println("consumer 已存在，无需再创建");
                } else {
                    consumer = new DefaultMQPushConsumer(MqConstant.MESSAGE_GROUP);
                    consumer.setNamesrvAddr(MqConstant.NAMESRV_ARRE);
                    consumer.registerMessageListener(new MessageLogListener());
                    try {
                        consumer.subscribe(MqConstant.MESSAGE_TOPIC, "*");
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    }
                    if (!started) {
                        try {
                            consumer.start();
                            started = true;
                            System.out.println("consumer 启动成功");
                        } catch (MQClientException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("consumer 已启动无需再次启动");
                    }
                }
            }
        }).start();
    }

    @PreDestroy
    public void destory() {
        System.out.println("MessageLogConsumer consumer 关闭");
        consumer.shutdown();
    }

    class MessageLogListener implements MessageListenerOrderly {

        AtomicLong times = new AtomicLong(0);

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
            System.out.println("开始消费消息...");
          //  try {
                MessageExt messageExt = null;
                if (!CollectionUtils.isEmpty(list)) {
                    messageExt = list.get(0);
                }
                if (messageExt != null) {
                    String messageBody = new String(messageExt.getBody());
                    MessageLogDo messageLog = JSONObject.parseObject(messageBody, MessageLogDo.class);
                    if (messageLog.getId() == 7) {
                        System.out.println("times = " + this.times.incrementAndGet());
                    }
                    MessageLogDo dbLog = messageLogService.getById(messageLog.getId());
                    if (dbLog != null && dbLog.getState() == 1) {
                        System.out.println("此消息已被处理，无需重复处理");
                        return ConsumeOrderlyStatus.SUCCESS;
                    }
                    //处理消息
                    messageLog.setState(1);
                    messageLog.setModified(new Date());
                    messageLogService.updateMessage(messageLog);
                    System.out.println("消息消费成功..");
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                return ConsumeOrderlyStatus.SUCCESS;
//            } catch (Exception e) {
//                System.out.println("cosumer error = " + e.getMessage());
//                context.setSuspendCurrentQueueTimeMillis(10000);
//                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//            }
        }
    }
}
