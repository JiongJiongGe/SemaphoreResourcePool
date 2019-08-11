package schedule;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author Wangjiongda
 * @date 2019-08-04 21:28
 * 定时生产者
 */
public class ScheduleProducer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("test_schedule_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

    }
}
