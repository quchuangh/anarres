package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Position;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 职位表; 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
public interface IPositionService extends IRowQueryService<Position> {

    List<Position> findByUser(String username);

    default List<Position> findByOrg(String... org) {
        return findByOrg(Arrays.asList(org));
    }

    default List<Position> findByOrg(List<String> orgCodes) {
        return lambdaQuery().in(Position::getOrganizationCode, orgCodes)
                .list();
    }
}
