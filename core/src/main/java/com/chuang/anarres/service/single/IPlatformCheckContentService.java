package com.chuang.anarres.service.single;

import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-12-13
 */
public interface IPlatformCheckContentService extends IRowQueryService<PlatformCheckContent> {

    default Optional<String> findCheckContent(PlatformType type, String domain) {
        return findOne(type, domain).map(PlatformCheckContent::getCheckContent);
    }

    default Optional<PlatformCheckContent> findOne(PlatformType type, String domain) {
        return lambdaQuery()
                .eq(PlatformCheckContent::getPlatformType, type)
                .eq(PlatformCheckContent::getDomain, domain)
                .oneOpt();
    }


    default PlatformCheckContent getOrCreate(PlatformType type, String domain) {
        return findOne(type, domain).orElseGet(() -> {
            PlatformCheckContent obj = new PlatformCheckContent()
                    .setDomain(domain)
                    .setPlatformType(type)
                    .setSubmitted(false)
                    .setLinkPublished(false)
                    .setResult("")
                    .setTemp("")
                    .setTemp2("")
                    .setCheckContent("")
                    .setUsername("");
            save(obj);
            return obj;
        });
    }
}
