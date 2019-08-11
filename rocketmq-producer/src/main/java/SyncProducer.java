import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @Intro: 同步生产者
 * @Author: WangJiongDa(yunkai)
 * @Date: 2019/7/3
 * @Time: 下午3:28
 */
public class SyncProducer {


    public void producer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unidcque_group_name");
        //producer.setCreateTopicKey("TopicY");
        producer.setNamesrvAddr("127.0.0.1:9876");
     //   producer.setVipChannelEnabled(false);
        producer.start();
        //for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicY", "tag", ("calmwang_" + 0).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, 30000);
            System.out.printf("%s Result", JSONObject.toJSON(sendResult));
        //}
        producer.shutdown();
    }

    public static SyncProducer syncProducerHandler() {
        return new SyncProducer();
    }
}
