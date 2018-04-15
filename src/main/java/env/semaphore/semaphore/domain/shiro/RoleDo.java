package env.semaphore.semaphore.domain.shiro;

import java.io.Serializable;

/**
 * @Intro:  角色Do
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:29
 */
public class RoleDo implements Serializable{

    private static final long serialVersionUID = -6746286236204563569L;

    private Integer id;

    private String roleName;  //角色名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
