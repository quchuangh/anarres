package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.Permission;
import com.chuang.anarres.office.sys.mapper.PermissionMapper;
import com.chuang.anarres.office.sys.service.IPermissionService;
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
public class PermissionServiceImpl extends RowQueryService<PermissionMapper, Permission> implements IPermissionService {

}
