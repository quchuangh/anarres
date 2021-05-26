package com.chuang.anarres.service.single;

import com.chuang.anarres.model.TargetDomain;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-10-17
 */
public interface ITargetDomainService extends IRowQueryService<TargetDomain> {

    default Set<String> add(Collection<String> urls, Boolean xinHuanOnly) {
        Set<String> list = urls.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        Set<String> has = lambdaQuery()
                .select(TargetDomain::getTargetDomain)
                .in(TargetDomain::getTargetDomain, list)
                .list()
                .stream()
                .map(TargetDomain::getTargetDomain)
                .collect(Collectors.toSet());

        list = CollectionKit.subtract(list, has, HashSet::new);

        Set<TargetDomain> set = list.stream().map(s -> new TargetDomain().setTargetDomain(s)
                    .setUsed(false)
                    .setUsedBy("")
                    .setXinhuan(xinHuanOnly)
        ).collect(Collectors.toSet());

        saveBatch(set);
        return list;
    }

    default boolean useXinhuan(String user, Integer id) {
        return lambdaUpdate()
                .set(TargetDomain::getUsed, true)
                .set(TargetDomain::getUsedBy, user)
                .set(TargetDomain::getUsedTime, LocalDateTime.now())
                .set(TargetDomain::getXinhuan, true)
                .eq(TargetDomain::getId, id)
                .eq(TargetDomain::getUsed, false)
                .update();

    }
}
