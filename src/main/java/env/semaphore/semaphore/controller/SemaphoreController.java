package env.semaphore.semaphore.controller;

import com.google.gson.Gson;
import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.domain.EnvRoomDo;
import env.semaphore.semaphore.service.EnvRoomService;
import env.semaphore.semaphore.utils.SemaphoreResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/04/02
 * @Time: 20:12
 */
@RestController
@RequestMapping("/semaphore")
public class SemaphoreController {

    private static Logger logger = LoggerFactory.getLogger(SemaphoreController.class);

    @RequestMapping(value = "/request/resource")
    public RpcResult<Boolean> requestResource(){
        RpcResult<EnvRoomDo> roomDo = preempteRoom();
        if(roomDo.getErrorCode() == 200) {
            logger.info("roomDo name = {}", roomDo.getValue().getName());
            releaseRoom(roomDo.getValue());
        } else {
            logger.info("error msg = {}", roomDo.getErrorMsg());
        }
        return RpcResult.ofSuccess(true);
    }

    /**
     * 抢占房间
     */
    private RpcResult<EnvRoomDo> preempteRoom(){
        return SemaphoreResourceFactory.getFactory().getRoom();
    }

    /**
     * 释放房间
     *
     * @param roomDo
     * @return
     */
    private RpcResult<Boolean> releaseRoom(EnvRoomDo roomDo){
        return SemaphoreResourceFactory.getFactory().releaseRoom(roomDo);
    }

}
