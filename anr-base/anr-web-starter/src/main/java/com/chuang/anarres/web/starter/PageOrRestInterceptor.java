package com.chuang.anarres.web.starter;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class PageOrRestInterceptor implements HandlerInterceptor {

    public static final String PAGE_VIEW_ATTR = "[[IS_PAGE_VIEW]]";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();

        //Controller方法是否使用@ResponseBody注解
        boolean b2 = !method.isAnnotationPresent(ResponseBody.class);
        //Controller是否使用@RestController注解
        boolean b3 = !hm.getBeanType().isAnnotationPresent(RestController.class);
        request.setAttribute(PAGE_VIEW_ATTR, b2 && b3);
        return true;
    }
}
