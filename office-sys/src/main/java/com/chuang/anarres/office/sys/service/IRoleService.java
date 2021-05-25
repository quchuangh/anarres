package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.enums.RoleType;
import com.chuang.anarres.office.sys.entity.Role;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色表;角色 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
public interface IRoleService extends IRowQueryService<Role> {

    List<Role> findAllRoles(String username);

    List<Role> findUserRoles(String username);

    default List<Role> findUserRoles() {
        return lambdaQuery().eq(Role::getRoleType, RoleType.USER_ROLE).list();
    }

    default Optional<Role> findByCode(String admin) {
        return lambdaQuery().eq(Role::getRole, admin).oneOpt();
    }

}
