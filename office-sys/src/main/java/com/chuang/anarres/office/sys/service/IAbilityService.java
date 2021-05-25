package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.anarres.office.sys.entity.LicensableAbility;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 权限表 权限 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
public interface IAbilityService extends IRowQueryService<Ability>, ITreeService<Ability> {

    List<LicensableAbility> findByUsername(String username);

    List<LicensableAbility> findByRoleId(Integer roleId);

    List<LicensableAbility> findByRoleIds(List<Integer> roleIds);
}
