package com.chuang.anarres.office.sys.controller.basic;

import org.apache.shiro.SecurityUtils;

public interface IAutoPermissionController {

    default String basePermission() {
        return null;
    }

    default void checkPermission(String suffix) {
        String base = basePermission();
        if(base != null) {
            SecurityUtils.getSubject().checkPermission(basePermission() + suffix);
        }
    }
}
