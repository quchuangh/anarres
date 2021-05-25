package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.bo.AL;
import com.chuang.anarres.crud.entity.bo.FullRoleAbilityBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAssignService {

    List<FullRoleAbilityBO> fullRoleAbilities(String username, Integer roleId);

    @Transactional(rollbackFor = Throwable.class)
    Boolean assign(String username, Integer roleId, List<AL> abilities);
}
