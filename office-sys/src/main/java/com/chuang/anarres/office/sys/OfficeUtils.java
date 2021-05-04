package com.chuang.anarres.office.sys;

import com.chuang.anarres.office.sys.model.ShiroUser;
import com.chuang.tauceti.shiro.spring.web.jwt.JwtPayload;
import com.chuang.tauceti.shiro.spring.web.jwt.filter.JwtAuthFilter;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.third.servlet.HttpKit;
import org.apache.shiro.SecurityUtils;

import java.util.Optional;

public class OfficeUtils {

    public static Optional<JwtPayload> jwt() {
        return HttpKit.getRequest().map(request -> (JwtPayload) request.getAttribute(JwtAuthFilter.JWT_PAYLOAD_REQUEST_ATTR));
    }

    public static Optional<JwtPayload> jwtTokenStr() {
        return HttpKit.getRequest().map(request -> (JwtPayload) request.getAttribute(JwtAuthFilter.JWT_STR_REQUEST_ATTR));
    }

    public static Optional<ShiroUser> shiroUser() {
        return Optional.ofNullable((ShiroUser) SecurityUtils.getSubject().getPrincipal());
    }

    public static ShiroUser shiroUserNotNull() {
        return shiroUser().orElseThrow(() -> new BusinessException("没有登录~"));
    }

}
