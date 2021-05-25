package com.chuang.anarres.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.crud.entity.RoleAbility;
import com.chuang.anarres.crud.mapper.RoleAbilityMapper;
import com.chuang.anarres.crud.service.IRoleAbilityService;
import com.chuang.anarres.crud.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleAbilityServiceImpl extends ServiceImpl<RoleAbilityMapper, RoleAbility> implements IRoleAbilityService {

    @Resource private IRoleService roleService;
    @Override
    public boolean addAdminRoleAbility(Integer abilityId) {
        return roleService.findByCode("admin").map(role -> {
            RoleAbility ra = new RoleAbility()
                    .setAbilityId(abilityId)
                    .setRoleId(role.getId())
                    .setLicensable(true);
            return save(ra);
        }).orElse(false);
    }
}
