package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.UserInfo;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 * 用户信息  服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IUserInfoService extends IRowQueryService<UserInfo> {

    default Optional<UserInfo> findByUsername(String username) {
        return lambdaQuery().eq(UserInfo::getUsername, username).oneOpt();
    }
}
