package env.semaphore.semaphore.domain.shiro;

import java.io.Serializable;

/**
 * @Intro: 角色权限表
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:34
 */
public class RolePermissionDo implements Serializable{

    private static final long serialVersionUID = 3617582318534221045L;

    private Integer id;

    private Integer roleId;  //角色Id

    private Integer permissId;  //权限Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissId() {
        return permissId;
    }

    public void setPermissId(Integer permissId) {
        this.permissId = permissId;
    }
}
