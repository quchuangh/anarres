package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.UserFastMenu;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户快捷菜单  服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IUserFastMenuService extends IRowQueryService<UserFastMenu> {

    default Set<String> findByUsername(String username) {
        return lambdaQuery().eq(UserFastMenu::getUsername, username).list()
                .stream()
                .map(UserFastMenu::getMenuCode)
                .collect(Collectors.toSet());
    }
}
