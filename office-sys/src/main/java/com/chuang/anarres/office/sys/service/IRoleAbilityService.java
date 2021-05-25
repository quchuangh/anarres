package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.RoleAbility;
import com.chuang.urras.rowquery.IRowQueryService;

public interface IRoleAbilityService extends IRowQueryService<RoleAbility> {

    boolean addAdminRoleAbility(Integer abilityId);
}
