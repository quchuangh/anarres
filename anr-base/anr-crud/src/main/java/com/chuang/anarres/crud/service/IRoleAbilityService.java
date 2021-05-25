package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.RoleAbility;
import com.chuang.tauceti.rowquery.IRowQueryService;

public interface IRoleAbilityService extends IRowQueryService<RoleAbility> {

    boolean addAdminRoleAbility(Integer abilityId);
}
