package env.semaphore.semaphore.mapper.shiro;

import env.semaphore.semaphore.domain.shiro.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:17
 */
@Mapper
@Component
public interface UserMapper {

    UserDo queryByName(@Param("name") String name);
}
