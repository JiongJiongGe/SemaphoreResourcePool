package order;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Wangjiongda
 * @date 2019-08-01 17:20
 * 订单消费者
 */
public class OrderConsumer {

    private static Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    public static void main(String[] args) throws Exception {
        OrderConsumer consumer = instance();
        consumer.consumer();
    }

    public void consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_order_group");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //从上次消费后的地方消费
        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.subscribe("TopicTestOrder", "TagA || TagC || TagD");
        consumer.registerMessageListener(new OrderMessageListener());
        consumer.start();
        System.out.println("开始消费....");
    }


    class OrderMessageListener implements MessageListenerOrderly {

        AtomicLong times = new AtomicLong(0);

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
            System.out.println("zzzzz");
            MessageExt msg = list.get(0);
            //设置非自动提交
            context.setAutoCommit(true);
//            System.out.println("Receiver Message = " + JSONObject.toJSONString(list));
            this.times.incrementAndGet();
            System.out.println("times = " + this.times.get() + "msg = " + new String(msg.getBody()));
            if (this.times.get() % 2 == 0 && this.times.get() % 3 != 0) {
                //提交成功
                return ConsumeOrderlyStatus.SUCCESS;
            } else if (this.times.get() % 3 == 0) {
                //挂起30s后再消费消息
                System.out.println("stop times = " + this.times.get());
                context.setSuspendCurrentQueueTimeMillis(30000);
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
            System.out.println("in ...");
            return ConsumeOrderlyStatus.SUCCESS;
        }
    }

    public static OrderConsumer instance() {
        return new OrderConsumer();
    }
}
