package com.chuang.anarres.office.sys;

import com.chuang.anarres.office.sys.model.vo.ShiroUser;
import com.chuang.tauceti.shiro.spring.web.jwt.JwtPayload;
import com.chuang.tauceti.support.exception.BusinessException;
import org.apache.shiro.SecurityUtils;

import java.util.Optional;

public class OfficeUtils {

    public static Optional<JwtPayload> jwt() {
        return Optional.ofNullable((JwtPayload) SecurityUtils.getSubject().getPrincipal());
    }

    public static Optional<ShiroUser> shiroUser() {
        return jwt().map(payload -> (ShiroUser) payload.getBody());
    }

    public static ShiroUser shiroUserNotNull() {
        return shiroUser().orElseThrow(() -> new BusinessException("没有登录~"));
    }
}
