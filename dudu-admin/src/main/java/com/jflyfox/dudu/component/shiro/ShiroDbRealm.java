package com.jflyfox.dudu.component.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.component.util.EncryptUtils;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.service.IRoleService;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.util.encrypt.Md5Utils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.InvalidPermissionStringException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description：shiro权限认证
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm {
    private static final Logger log = Logger.getLogger(ShiroDbRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        log.info("Shiro doGetAuthenticationInfo...");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        Wrapper<SysUser> userroleWrapper = new EntityWrapper<>();
        userroleWrapper.eq("username", token.getUsername());
        SysUser user = userService.selectOne(userroleWrapper);
        // 账号不存在
        if (user == null || user.getId() == null) {
            return null;
        }
        String md5Password = "";
        try {
            String userPassword = user.getPassword();
            String decryptPassword = EncryptUtils.passwordDecrypt(userPassword);
            md5Password = new Md5Utils().getMD5(decryptPassword).toLowerCase();
        } catch (Exception e) {
            throw new AuthenticationException("认证异常");
        }

        if (!(user.getUsertype() == 1 || user.getUsertype() == 2)) {
            throw new InvalidPermissionStringException("您没有登录权限，请您重新输入!", "login");
        }

        SessionUser shiroUser = new SessionUser(user.getId());
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, md5Password, null, getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        log.info("Shiro doGetAuthorizationInfo...");
        SessionUser shiroUser = (SessionUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(shiroUser.getRoles());
//        info.addStringPermissions(shiroUser.getUrlSet());

        return info;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        log.info("Shiro onLogout...");
        super.clearCachedAuthorizationInfo(principals);
        SessionUser shiroUser = (SessionUser) principals.getPrimaryPrincipal();
        removeUserCache(shiroUser);
    }

    /**
     * 清除用户缓存
     *
     * @param shiroUser
     */
    public void removeUserCache(SessionUser shiroUser) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(shiroUser.getId(), super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }
}
