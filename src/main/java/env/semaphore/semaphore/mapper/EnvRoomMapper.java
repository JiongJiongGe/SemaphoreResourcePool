package env.semaphore.semaphore.mapper;

import env.semaphore.semaphore.domain.EnvRoomDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/04/02
 * @Time: 20:17
 */
@Mapper
@Component
public interface EnvRoomMapper {

    List<EnvRoomDo> queryList();
}
