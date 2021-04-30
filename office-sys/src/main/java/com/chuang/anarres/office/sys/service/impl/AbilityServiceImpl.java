package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.anarres.office.sys.mapper.AbilityMapper;
import com.chuang.anarres.office.sys.service.IAbilityService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 权限 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
@Service
public class AbilityServiceImpl extends RowQueryService<AbilityMapper, Ability> implements IAbilityService {

}
