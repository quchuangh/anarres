package com.chuang.anarres.service.single;

import com.chuang.anarres.model.SeoDomain;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface ISeoDomainService extends IRowQueryService<SeoDomain> {

    default Optional<SeoDomain> findByDomain(String domain) {
        return lambdaQuery().eq(SeoDomain::getDomain, domain).oneOpt();
    }

    default Optional<SeoDomain> findByReference(String reference) {
        return lambdaQuery().eq(SeoDomain::getProposalReference, reference).oneOpt();
    }


}
