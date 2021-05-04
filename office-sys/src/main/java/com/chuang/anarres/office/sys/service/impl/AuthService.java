package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.OfficeUtils;
import com.chuang.anarres.office.sys.entity.User;
import com.chuang.anarres.office.sys.entity.UserInfo;
import com.chuang.anarres.office.sys.model.ShiroUser;
import com.chuang.anarres.office.sys.service.*;
import com.chuang.tauceti.shiro.spring.web.jwt.realm.IShiroService;
import com.chuang.tauceti.shiro.spring.web.jwt.realm.LoginToken;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.BeanKit;
import com.chuang.tauceti.tools.basic.reflect.ConvertKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class AuthService implements IAuthService, IShiroService {

    @Resource private IUserService userService;
    @Resource private IUserInfoService userInfoService;
    @Resource private IRoleService roleService;
    @Resource private IAbilityService permissionService;

//=======================================    SHIRO     ======================================================
    @Override
    public AuthenticationInfo getAuthenticationInfoByLoginToken(LoginToken token, String realmName) {
        String username = token.getUsername();
        Optional<User> opt = userService.findByUsername(username);

        if(!opt.isPresent()) {
            throw new AuthenticationException("用户名不存在");
        }
        User user = opt.get();

        ShiroUser shiroUser = ConvertKit.toBean(user, ShiroUser::new);

        return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(Hex.decode(user.getSalt())), realmName);
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ShiroUser user = OfficeUtils.shiroUser().orElseThrow(() -> new BusinessException("用户未登录"));
        info.addRoles(user.getRoles());
        info.addStringPermissions(user.getAbilities());
        return info;
    }

    @Override
    public void loginSuccess(LoginToken token) {
        UserInfo info = userInfoService.findByUsername(token.getUsername())
                .orElseThrow(() -> new BusinessException("{}用户信息缺失，请联系管理员修复", token.getUsername()));


        BeanKit.copyProperties(info, SecurityUtils.getSubject().getPrincipal());
        log.info("login success");
    }

    @Override
    public void loginFailure(LoginToken token, AuthenticationException e) {
        log.info("login fail");
    }
}
