package order;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Wangjiongda
 * @date 2019-07-30 21:07
 * 订单生产者
 */
public class OrderProducer {

    private static Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public static void main(final String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test_order_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicTestOrder", tags[i % tags.length], "KEY" + i, ("HelloRocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                    System.out.println("orderId = " + arg + "size = " + list.size());
                    Integer id = (Integer) arg;
                    int index = id % list.size();
                    return list.get(index);
                }
            }, i, 100000);
            System.out.println(JSONObject.toJSONString(sendResult));
        }
        producer.shutdown();
    }
}
