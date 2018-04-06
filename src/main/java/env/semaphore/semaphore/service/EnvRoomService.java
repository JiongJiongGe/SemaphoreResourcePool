package env.semaphore.semaphore.service;


import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvRoomDo;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/04/02
 * @Time: 20:29
 */
public interface EnvRoomService {

    RpcResult<List<EnvRoomDo>> findList();
}
