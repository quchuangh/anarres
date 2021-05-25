package com.chuang.anarres.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.crud.entity.Ability;
import com.chuang.anarres.crud.entity.LicensableAbility;
import com.chuang.anarres.crud.entity.Role;
import com.chuang.anarres.crud.mapper.AbilityMapper;
import com.chuang.anarres.crud.service.IAbilityService;
import com.chuang.anarres.crud.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 权限 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
@Service
public class AbilityServiceImpl extends ServiceImpl<AbilityMapper, Ability> implements IAbilityService {

    @Resource private IRoleService roleService;

    @Override
    public List<LicensableAbility> findByUsername(String username) {
        List<Role> roles = roleService.findAllRoles(username);
        if(roles.isEmpty()) {
            return Collections.emptyList();
        }

        return findByRoleIds(
          roles.stream().map(Role::getId).collect(Collectors.toList())
        );
    }

    @Override
    public List<LicensableAbility> findByRoleId(Integer roleId) {
        return baseMapper.findByRoleId(roleId);
    }

    @Override
    public List<LicensableAbility> findByRoleIds(List<Integer> roleIds) {
        if(roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return baseMapper.findByRoleIds(roleIds);
    }



}
