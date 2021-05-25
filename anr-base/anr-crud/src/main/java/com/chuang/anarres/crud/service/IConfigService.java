package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Config;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 * 系统配置表  服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
public interface IConfigService extends IRowQueryService<Config> {

    default Optional<Config> findByCode(String code) {
        return lambdaQuery().eq(Config::getCode, code).oneOpt();
    }
}
