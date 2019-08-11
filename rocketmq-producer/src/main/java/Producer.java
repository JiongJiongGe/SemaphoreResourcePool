/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2019/6/27
 * @Time: 下午5:28
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //同步消息
        SyncProducer syncProducer = SyncProducer.syncProducerHandler();
        syncProducer.producer();
    }
}


