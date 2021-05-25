package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.enums.Gender;
import com.chuang.anarres.enums.Language;
import com.chuang.anarres.enums.UserStatus;
import com.chuang.anarres.office.sys.OfficeUtils;
import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.anarres.office.sys.entity.Role;
import com.chuang.anarres.office.sys.entity.User;
import com.chuang.anarres.office.sys.entity.UserInfo;
import com.chuang.anarres.office.sys.model.ShiroUser;
import com.chuang.anarres.office.sys.service.*;
import com.chuang.tauceti.shiro.spring.web.jwt.realm.IShiroService;
import com.chuang.tauceti.shiro.spring.web.jwt.realm.LoginToken;
import com.chuang.tauceti.support.BiValue;
import com.chuang.tauceti.support.ThreeValue;
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

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService implements IAuthService, IShiroService {

    @Resource private IUserService userService;
    @Resource private IUserInfoService userInfoService;
    @Resource private IRoleService roleService;
    @Resource private IAbilityService abilityService;



    @Override
    public Optional<String> createUser(String username, String name, @Nullable String ipBound, @Nullable String macBound) {
        ThreeValue<String, String, String> three = randomSaltAndPassword();
        User user = new User();
        user.setUsername(username)
                .setIpBound(ipBound)
                .setLoginTimes(0)
                .setMacBound(macBound)
                .setLoginSuccessTimes(0)
                .setPassword(three.getTwo())
                .setSalt(three.getThree())
                .setState(UserStatus.NORMAL);

        UserInfo info = new UserInfo();
        info.setName(name)
                .setLanguage(Language.zh_CN)
                .setUsername(username)
                .setBirthday(LocalDate.now())
                .setPhone("")
                .setEmail("")
                .setAvatar("")
                .setGender(Gender.MALE);

        userService.save(user);
        userInfoService.save(info);

        return Optional.ofNullable(three.getOne());
    }

    @Override
    public boolean forceChangePassword(String username, String password) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new BusinessException("无法找到用户"));
        BiValue<String, String> bi = randomSaltWithPassword(password);


        user.setPassword(bi.getOne())
                .setSalt(bi.getTwo());

        return userService.updateById(user);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new BusinessException("无法找到用户"));
        boolean match = match(oldPassword, user.getSalt(), user.getPassword());

        if(match) {
            return forceChangePassword(username, newPassword);
        } else {
            throw new AuthenticationException("原密码错误");
        }
    }


    //=======================================    SHIRO     ======================================================
    @Override
    public AuthenticationInfo getAuthenticationInfoByLoginToken(LoginToken token, String realmName) {
        try {
            String username = token.getUsername();
            Optional<User> opt = userService.findByUsername(username);

            if (!opt.isPresent()) {
                throw new AuthenticationException("用户名不存在");
            }
            User user = opt.get();

            ShiroUser shiroUser = ConvertKit.toBean(user, ShiroUser::new);

            return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(Hex.decode(user.getSalt())), realmName);
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
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

        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        BeanKit.copyProperties(info, user);


        List<Role> roles = roleService.findAllRoles(user.getUsername());

        List<String> roleCodes = roles.stream()
                .filter(Role::getEnabled)
                .map(Role::getRole)
                .collect(Collectors.toList());
        List<Integer> roleIds = roles.stream()
                .filter(Role::getEnabled)
                .map(Role::getId)
                .collect(Collectors.toList());

        List<String> abilities = abilityService.findByRoleIds(roleIds)
                .stream()
                .filter(Ability::getEnabled)
                .map(Ability::getAbility)
                .collect(Collectors.toList());


        user.setRoles(roleCodes);
        user.setAbilities(abilities);

        log.info("login success");
    }

    @Override
    public void loginFailure(LoginToken token, AuthenticationException e) {
        log.info("login fail");
    }


}
