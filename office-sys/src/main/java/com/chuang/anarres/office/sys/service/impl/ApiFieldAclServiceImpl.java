package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.ApiFieldAcl;
import com.chuang.anarres.office.sys.mapper.ApiFieldAclMapper;
import com.chuang.anarres.office.sys.service.IApiFieldAclService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api字段权限  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-01
 */
@Service
public class ApiFieldAclServiceImpl extends RowQueryService<ApiFieldAclMapper, ApiFieldAcl> implements IApiFieldAclService {

}
