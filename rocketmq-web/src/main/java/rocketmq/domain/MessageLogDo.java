package rocketmq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wangjiongda
 * @date 2019-08-11 15:32
 * 日志domain
 */
public class MessageLogDo implements Serializable {

    private static final long serialVersionUID = 3122981454243366784L;

    private Long id;

    /**
     * 状态 0、刚生成;1、处理后
     */
    private Integer state;

    /**
     * 修改时间
     */
    private Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
