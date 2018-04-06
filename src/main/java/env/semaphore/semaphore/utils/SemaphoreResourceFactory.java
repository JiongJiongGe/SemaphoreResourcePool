package env.semaphore.semaphore.utils;

import env.semaphore.semaphore.ErrorCode;
import env.semaphore.semaphore.RpcResult;
import env.semaphore.semaphore.constant.SemaphoreConstant;
import env.semaphore.semaphore.domain.EnvRoomDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        synchronized (envRooms){
            if(envRooms != null && envRooms.size() > 0){
                roomDo = envRooms.remove(0);
            }
        }
        if(roomDo == null){
            return RpcResult.ofError(ErrorCode.BUSSINESS_ERROR.getCode(), "房间资源已被占满");
        }else {
            try {
                semaphoreConstant.getResouceRoomSemaphore().acquire();
            }catch(InterruptedException e){
                logger.error("Semaphore acquire error : {}", e);
            }
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
        //synchronized (envRooms)
        envRooms.add(roomDo);
        semaphoreConstant.getResouceRoomSemaphore().release();;
        return RpcResult.ofSuccess(true);
    }

    private static class SemaphoreResourceFactoryHolder{
        private static SemaphoreResourceFactory factory = new SemaphoreResourceFactory();
    }
}
