package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.Role;
import com.chuang.anarres.office.sys.mapper.RoleMapper;
import com.chuang.anarres.office.sys.service.IRoleService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 角色 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Service
public class RoleServiceImpl extends RowQueryService<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }
}
