package env.semaphore.semaphore.constant;

import java.util.concurrent.Semaphore;

/**
 * @Intro: semaphore 常量配置
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/2
 * @Time: 下午8:41
 */
public class SemaphoreConstant {

    /**
     *  信号量(控制资源池)
     */
    private Semaphore resouceRoomSemaphore;

    /**
     *  信号量大小，即资源池的大小
     */
    private int semaphoreSize = 10;

    public SemaphoreConstant(){
        resouceRoomSemaphore = new Semaphore(semaphoreSize);
    }

    public Semaphore getResouceRoomSemaphore() {
        return resouceRoomSemaphore;
    }

    public int getSemaphoreSize() {
        return semaphoreSize;
    }

    public void setSemaphoreSize(int semaphoreSize) {
        this.semaphoreSize = semaphoreSize;
    }
}
