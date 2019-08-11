import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2019/7/2
 * @Time: 下午4:25
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        Consumer consumer = consumerHandler();
        consumer.consumer();
    }

    /**
     * 消费核心方法
     *
     * @throws MQClientException
     */
    public void consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unidcque_group_name");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("TopicY", "tag");
        consumer.registerMessageListener(new SubListener());
        consumer.start();
        System.out.println("Consumer Started...");
    }

    /**
     * 消费listener
     */
    class SubListener implements MessageListenerConcurrently {

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            System.out.println("in .....");
            System.out.printf("%s Receive New Message: %s %n", Thread.currentThread().getName(), JSONObject.toJSONString(msgs));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    private static Consumer consumerHandler() {
        return new Consumer();
    }
}
