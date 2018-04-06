package env.semaphore.semaphore.service.impl;

import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvRoomDo;
import env.semaphore.semaphore.mapper.EnvRoomMapper;
import env.semaphore.semaphore.service.EnvRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/04/02
 * @Time: 20:29
 */
@Service("envRoomService")
public class EnvRoomServiceImpl implements EnvRoomService {

    @Autowired
    private EnvRoomMapper envUserMapper;

    @Override
    public RpcResult<List<EnvRoomDo>> findList(){
        return RpcResult.ofSuccess(envUserMapper.queryList());
    }
}
