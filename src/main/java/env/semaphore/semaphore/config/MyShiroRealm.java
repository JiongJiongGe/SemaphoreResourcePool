package env.semaphore.semaphore.config;

import com.google.gson.Gson;
import env.semaphore.semaphore.domain.shiro.UserDo;
import env.semaphore.semaphore.service.shiro.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Intro:
 * @Author: WangJiongDa(yunkai)
 * @Date: 2018/4/14
 * @Time: 下午9:59
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserDo userDo  = (UserDo)principals.getPrimaryPrincipal();
        //权限模拟
        authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("/semaphore/request/resource");
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String name = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserDo userDo = userService.getByName(name);
        logger.info("userDo = {}", new Gson().toJson(userDo));
        if(userDo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userDo, //用户名
                userDo.getPassword(), //密码
                ByteSource.Util.bytes(userDo.getCredentialsSalt()),//salt=username+
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
