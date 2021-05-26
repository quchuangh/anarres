package com.chuang.anarres.service.single;

import com.chuang.anarres.model.DomainFetchLog;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.support.BiValue;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2021-02-10
 */
public interface IDomainFetchLogService extends IRowQueryService<DomainFetchLog> {

    void addTodayTimes(List<BiValue<String, Integer>> list);
    void addTodayTime(String domain, Integer times);

    void createTodayLogIfAbsent(String domain);
}
