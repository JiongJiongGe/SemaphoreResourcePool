package env.semaphore.semaphore.controller;

import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvUserDo;
import env.semaphore.semaphore.service.EnvUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/3/8
 * @Time: 下午7:04
 */
@RestController
@RequestMapping("semaphore")
public class SemaphoreController {

    @Autowired
    private EnvUserService envUserService;

    @RequestMapping(value = "/list")
    public RpcResult<List<EnvUserDo>> list(){
        return envUserService.findList();
    }
}
