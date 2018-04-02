package env.semaphore.semaphore.service.impl;

import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvUserDo;
import env.semaphore.semaphore.mapper.EnvUserMapper;
import env.semaphore.semaphore.service.EnvUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/3/9
 * @Time: 下午7:29
 */
@Service("envUserService")
public class EnvUserServiceImpl implements EnvUserService {

    @Autowired
    private EnvUserMapper envUserMapper;

    @Override
    public RpcResult<List<EnvUserDo>> findList(){
        return RpcResult.ofSuccess(envUserMapper.queryList());
    }
}
