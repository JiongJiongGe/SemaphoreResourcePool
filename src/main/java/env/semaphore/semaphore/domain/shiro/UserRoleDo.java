package env.semaphore.semaphore.domain.shiro;

import java.io.Serializable;

/**
 * @Intro: 用户角色表
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:33
 */
public class UserRoleDo implements Serializable{

    private static final long serialVersionUID = 8603511020557432541L;

    private Integer id;

    private Integer userId;  //用户Id

    private Integer roleId;  //角色Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
