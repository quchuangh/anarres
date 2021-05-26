package com.chuang.anarres.service.single;

import com.chuang.anarres.model.JsGroup;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-09-30
 */
public interface IJsGroupService extends IRowQueryService<JsGroup> {

    default Optional<JsGroup> findByName(String name) {
        return lambdaQuery().eq(JsGroup::getName, name).oneOpt();
    }
}
