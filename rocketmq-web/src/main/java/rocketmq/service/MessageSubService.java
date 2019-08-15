package rocketmq.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rocketmq.constants.MqConstant;
import rocketmq.domain.MessageLogDo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Wangjiongda
 * @date 2019-08-11 21:52
 * 消息推送service
 */
@Service
public class MessageSubService {

    private static Logger logger = LoggerFactory.getLogger(MessageSubService.class);

    /**
     * 消息生产者
     */
    private static DefaultMQProducer producer;

    /**
     * producer是否start()
     */
    private static boolean started = false;

    /**
     * 构建时初始化
     */
    @PostConstruct
    public void initMq() {

        System.out.println("initMq 开启");

        new Thread(new Runnable() {

            @Override
            public void run() {
                if (producer != null) {
                    System.out.println("producer 已存在，无需再创建");
                } else {
                    producer = new DefaultMQProducer(MqConstant.MESSAGE_GROUP);
                    producer.setNamesrvAddr(MqConstant.NAMESRV_ARRE);
                    if (!started) {
                        try {
                            producer.start();
                            started = true;
                            System.out.println("producer 启动成功");
                        } catch (MQClientException e) {
                            System.out.println("error = " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("producer 已启动，无需再启动");
                    }
                }
            }
        }).start();
    }

    /**
     * 销毁
     */
    @PreDestroy
    public void desotry() {
        System.out.println("MessageSubService producer 关闭");
        producer.shutdown();
    }

    /**
     * 推送消息
     *
     * @param messageLogDo
     */
    public void pushMessage(MessageLogDo messageLogDo) {
        if (messageLogDo == null) {
            return;
        }
        try {
            Message message = new Message(MqConstant.MESSAGE_TOPIC, "*", "KEY" + messageLogDo.getId(), JSONObject.toJSONString(messageLogDo).getBytes());
            SendResult sendResult = producer.send(message, 30000);
            System.out.println("消息发送成功...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("消息推送失败，消息内容: " + JSONObject.toJSONString(messageLogDo));
        }
    }
}
