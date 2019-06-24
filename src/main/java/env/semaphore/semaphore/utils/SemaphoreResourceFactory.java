package env.semaphore.semaphore.utils;

import env.semaphore.semaphore.ErrorCode;
import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.constant.SemaphoreConstant;
import env.semaphore.semaphore.domain.EnvRoomDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/2
 * @Time: 下午8:51
 */
public class SemaphoreResourceFactory {

    private static Logger logger = LoggerFactory.getLogger(SemaphoreResourceFactory.class);

    private SemaphoreConstant semaphoreConstant;

    private List<EnvRoomDo> envRooms;

    private SemaphoreResourceFactory(){
        semaphoreConstant = new SemaphoreConstant();
        envRooms = new ArrayList<EnvRoomDo>(semaphoreConstant.getSemaphoreSize());
        for(int i = 0; i < semaphoreConstant.getSemaphoreSize(); i++){
            EnvRoomDo envRoomDo = new EnvRoomDo();
            envRoomDo.setId(i);
            envRoomDo.setName("教室_" + i);
            envRooms.add(envRoomDo);
        }
    }

    public static SemaphoreResourceFactory getFactory(){
        return SemaphoreResourceFactoryHolder.factory;
    }

    /**
     * 抢占资源
     *
     * @return
     */
    public RpcResult<EnvRoomDo> getRoom(){
        EnvRoomDo roomDo = null;
        try {
            if(semaphoreConstant.getResouceRoomSemaphore().tryAcquire(10, TimeUnit.SECONDS)){
                if (envRooms != null && envRooms.size() > 0) {
                    roomDo = envRooms.remove(0);
                }
            } else {
                logger.info("超时了...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(roomDo == null){
            return RpcResult.ofError(ErrorCode.BUSSINESS_ERROR.getCode(), "房间资源已被占满");
        }else {
            return RpcResult.ofSuccess(roomDo);
        }
    }

    /**
     * 释放资源
     *
     * @param roomDo
     * @return
     */
    public RpcResult<Boolean> releaseRoom(EnvRoomDo roomDo){
        envRooms.add(roomDo);
        semaphoreConstant.getResouceRoomSemaphore().release();
        return RpcResult.ofSuccess(true);
    }

    private static class SemaphoreResourceFactoryHolder{
        private static SemaphoreResourceFactory factory = new SemaphoreResourceFactory();
    }
}
