package env.semaphore.semaphore.domain.shiro;

import java.io.Serializable;

/**
 * @Intro: 权限Do
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:31
 */
public class PermissionDo implements Serializable{


    private static final long serialVersionUID = -1259411179962106931L;

    private Integer id;

    private String permissName;  //权限名称

    private String permissUrl;   //权限Url

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissName() {
        return permissName;
    }

    public void setPermissName(String permissName) {
        this.permissName = permissName;
    }

    public String getPermissUrl() {
        return permissUrl;
    }

    public void setPermissUrl(String permissUrl) {
        this.permissUrl = permissUrl;
    }
}
