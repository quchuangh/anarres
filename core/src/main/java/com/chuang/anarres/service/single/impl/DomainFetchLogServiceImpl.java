package com.chuang.anarres.service.single.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.mapper.DomainFetchLogMapper;
import com.chuang.anarres.model.DomainFetchLog;
import com.chuang.anarres.model.SeoDomain;
import com.chuang.anarres.service.single.IDomainFetchLogService;
import com.chuang.anarres.service.single.ISeoDomainService;
import com.chuang.tauceti.support.BiValue;
import org.apache.http.client.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ath
 * @since 2021-02-10
 */
@Service
public class DomainFetchLogServiceImpl extends ServiceImpl<DomainFetchLogMapper, DomainFetchLog> implements IDomainFetchLogService {

    @Resource private ISeoDomainService seoDomainService;

    @Override
    public void addTodayTimes(List<BiValue<String, Integer>> list) {
        if(list.isEmpty()) {
            return;
        }

        baseMapper.addTimes(list, DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
    }

    @Override
    public void addTodayTime(String domain, Integer times) {
        baseMapper.addTime(domain, times, DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
    }

    @Override
    public void createTodayLogIfAbsent(String domain) {
        boolean internal = seoDomainService.lambdaQuery().eq(SeoDomain::getDomain, domain).count() > 0;
        baseMapper.createIfAbsent(domain, internal ? 1:0, DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
    }
}
