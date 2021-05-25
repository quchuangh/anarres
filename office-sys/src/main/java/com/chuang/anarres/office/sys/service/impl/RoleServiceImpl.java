package com.chuang.anarres.office.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.office.sys.entity.Role;
import com.chuang.anarres.office.sys.mapper.RoleMapper;
import com.chuang.anarres.office.sys.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表;角色 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Override
    public List<Role> findAllRoles(String username) {

        List<Role> roles = baseMapper.findUserRoles(username);

        Role r = baseMapper.findOrgRole(username);
        if(null != r) {
            roles.add(r);
        }
        r = baseMapper.findPositionRole(username);
        if(null != r) {
            roles.add(r);
        }
        return roles;
    }

    @Override
    public List<Role> findUserRoles(String username) {
        return baseMapper.findUserRoles(username);
    }
}
