package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Organization;
import com.chuang.tauceti.rowquery.IRowQueryService;

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
