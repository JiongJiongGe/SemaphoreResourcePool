package rocketmq.mapper;

import org.springframework.stereotype.Repository;
import rocketmq.domain.MessageLogDo;

/**
 * @author Wangjiongda
 * @date 2019-08-11 15:53
 */
@Repository
public interface MessageLogMapper {

    /**
     * insert数据
     *
     * @param messageLogDo
     * @return
     */
    Integer insertMessageLog(MessageLogDo messageLogDo);

    /**
     * 根据id更新数据状态
     *
     * @param messageLogDo
     * @return
     */
    Integer updateMessage(MessageLogDo messageLogDo);
}
