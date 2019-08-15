package rocketmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketmq.domain.MessageLogDo;
import rocketmq.mapper.MessageLogMapper;

/**
 * @author Wangjiongda
 * @date 2019-08-11 21:48
 */
@Service
public class MessageLogService {

    @Autowired
    private MessageLogMapper messageLogMapper;

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    public MessageLogDo getById(Long id) {
        return messageLogMapper.getById(id);
    }

    /**
     * insert数据
     *
     * @param messageLogDo
     * @return
     */
    public Integer insertMessageLog(MessageLogDo messageLogDo) {
        return messageLogMapper.insertMessageLog(messageLogDo);
    }

    /**
     * 根据id更新数据状态
     *
     * @param messageLogDo
     * @return
     */
    public Integer updateMessage(MessageLogDo messageLogDo) {
        return messageLogMapper.updateMessage(messageLogDo);
    }
}
