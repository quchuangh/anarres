package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Role;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 角色表 角色 服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IRoleService extends IRowQueryService<Role> {

    List<Role> findByUsername(String username);
}
