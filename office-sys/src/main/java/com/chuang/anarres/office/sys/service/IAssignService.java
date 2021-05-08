package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.model.bo.AL;
import com.chuang.anarres.office.sys.model.bo.FullRoleAbilityBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAssignService {

    List<FullRoleAbilityBO> fullRoleAbilities(String username, Integer roleId);

    @Transactional(rollbackFor = Throwable.class)
    Boolean assign(String username, Integer roleId, List<AL> abilities);
}
