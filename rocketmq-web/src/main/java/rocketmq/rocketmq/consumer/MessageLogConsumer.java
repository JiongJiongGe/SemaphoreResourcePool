package rocketmq.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.stereotype.Component;
import rocketmq.constants.MqConstant;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Wangjiongda
 * @date 2019-08-13 21:22
 * 初始化messageLog consumer
 */
@Component
public class MessageLogConsumer {

    private static DefaultMQPushConsumer consumer;

    private static boolean started = false;

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
        });
    }

    @PreDestroy
    public void destory() {
        System.out.println("MessageLogConsumer consumer 关闭");
        consumer.shutdown();
    }
}
