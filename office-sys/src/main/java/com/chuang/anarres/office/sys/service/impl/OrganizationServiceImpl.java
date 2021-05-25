package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.Organization;
import com.chuang.anarres.office.sys.mapper.OrganizationMapper;
import com.chuang.anarres.office.sys.service.IOrganizationService;
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
