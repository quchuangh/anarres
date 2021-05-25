package com.chuang.anarres.crud.service.impl;

import com.chuang.anarres.crud.entity.Organization;
import com.chuang.anarres.crud.mapper.OrganizationMapper;
import com.chuang.anarres.crud.service.IOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织表; 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Override
    public List<Organization> findJoined(String username) {
        return baseMapper.findJoined(username);
    }
}
