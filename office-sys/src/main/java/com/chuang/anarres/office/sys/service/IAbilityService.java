package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 权限表 权限 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
public interface IAbilityService extends IRowQueryService<Ability> {

    default List<Ability> findByUsername(String username) {
        return null;
    }
}
