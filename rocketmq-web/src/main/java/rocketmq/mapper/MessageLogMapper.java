package rocketmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import rocketmq.domain.MessageLogDo;

/**
 * @author Wangjiongda
 * @date 2019-08-11 15:53
 */
@Component
@Mapper
public interface MessageLogMapper {

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    MessageLogDo getById(Long id);

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
