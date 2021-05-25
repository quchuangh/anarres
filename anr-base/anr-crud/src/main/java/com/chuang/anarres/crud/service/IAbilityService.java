package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Ability;
import com.chuang.anarres.crud.entity.LicensableAbility;

import java.util.List;

/**
 * <p>
 * 权限表 权限 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
public interface IAbilityService extends ITreeService<Ability> {

    List<LicensableAbility> findByUsername(String username);

    List<LicensableAbility> findByRoleId(Integer roleId);

    List<LicensableAbility> findByRoleIds(List<Integer> roleIds);

}
