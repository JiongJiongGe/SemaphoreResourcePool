package env.semaphore.semaphore.service.shiro;

import env.semaphore.semaphore.domain.shiro.UserDo;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:18
 */
public interface UserService {

    /**
     * 通过name获取对象
     *
     * @param name 用户名
     * @return
     */
    public UserDo getByName(String name);
}
