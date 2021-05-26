package com.chuang.anarres.service.single;

import com.chuang.anarres.mapper.SpiderLogMapper;
import com.chuang.anarres.model.SpiderLog;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
public interface ISpiderLogService extends IRowQueryService<SpiderLog> {

    default Optional<SpiderLog> findByDate(LocalDate date){
        return lambdaQuery().eq(SpiderLog::getLogDate, date).oneOpt();
    }

    default boolean addSpider(int baidu, int haosou, int sogou, int yisou, int other, LocalDate logDate) {
        return ((SpiderLogMapper)getBaseMapper()).addSpider(baidu, haosou, sogou, yisou, other, logDate);
    }

    default boolean saveOrIgnore(SpiderLog spiderLog) {
        return ((SpiderLogMapper)getBaseMapper()).saveOrIgnore(spiderLog);
    }
}
