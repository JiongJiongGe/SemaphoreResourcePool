package env.semaphore.semaphore.service.impl.shiro;

import env.semaphore.semaphore.domain.shiro.UserDo;
import env.semaphore.semaphore.mapper.shiro.UserMapper;
import env.semaphore.semaphore.service.shiro.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:19
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过name获取对象
     *
     * @param name 用户名
     * @return
     */
    @Override
    public UserDo getByName(String name){
        return userMapper.queryByName(name);
    }
}
