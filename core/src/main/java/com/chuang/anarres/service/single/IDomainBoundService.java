package com.chuang.anarres.service.single;

import com.chuang.anarres.model.DomainBound;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-08-15
 */
public interface IDomainBoundService extends IRowQueryService<DomainBound> {

    default Optional<DomainBound> findByDomain(String domain) {
        return lambdaQuery().eq(DomainBound::getDomain, domain).oneOpt();
    }
}
