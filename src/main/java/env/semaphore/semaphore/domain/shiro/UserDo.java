package env.semaphore.semaphore.domain.shiro;

import java.io.Serializable;

/**
 * @Intro:  用户Do
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/13
 * @Time: 下午8:13
 */
public class UserDo implements Serializable{

    private static final long serialVersionUID = 668328460524894879L;

    private Integer id;

    private String name;    //用户昵称

    private String password;  //用户密码

    private String salt;  //加密密码的盐

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.name+this.salt;
    }
}
