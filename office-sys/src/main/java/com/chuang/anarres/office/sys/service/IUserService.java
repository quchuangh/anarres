package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.User;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 * 用户  服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IUserService extends IRowQueryService<User> {

    default Optional<User> findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername,username).oneOpt();
    }
}
