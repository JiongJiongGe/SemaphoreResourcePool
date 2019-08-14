package rocketmq.controller.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.domain.MessageLogDo;
import rocketmq.service.MessageLogService;
import rocketmq.service.MessageSubService;
import rocketmq.util.RpcResult;

/**
 * @author Wangjiongda
 * @date 2019-08-13 17:47
 * message controller
 */
@RestController
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageSubService messageSubService;

    @Autowired
    private MessageLogService messageLogService;

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/send/message")
    public RpcResult<String> sendMessage() {
        MessageLogDo messageLogDo = new MessageLogDo();
        messageLogDo.setState(0);
        messageLogService.insertMessageLog(messageLogDo);
        logger.info("id = {}", messageLogDo.getId());
        messageSubService.pushMessage(messageLogDo);
        return RpcResult.ofSuccess("发送成功");
    }
}
