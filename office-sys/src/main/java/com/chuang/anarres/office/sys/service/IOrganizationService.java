package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Organization;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 组织表; 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
public interface IOrganizationService extends IRowQueryService<Organization>, ITreeService<Organization> {
    List<Organization> findJoined(String username);
}
