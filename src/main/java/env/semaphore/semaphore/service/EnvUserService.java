package env.semaphore.semaphore.service;


import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvUserDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/3/9
 * @Time: 下午7:29
 */
public interface EnvUserService {

    RpcResult<List<EnvUserDo>> findList();
}
