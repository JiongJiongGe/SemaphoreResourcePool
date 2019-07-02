package rocketmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.util.RpcResult;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2019/6/25
 * @Time: 下午4:15
 */
@RestController
public class WebController {

    /**
     * 构建controller
     * @return
     */
    @RequestMapping(value = "/web")
    public RpcResult<String> web () {
        return RpcResult.ofSuccess("成功");
    }
}
