package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Api;
import com.chuang.tauceti.httpclient.HttpMethod;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.tools.basic.HashKit;

import java.util.List;

/**
 * <p>
 * 接口  服务类
 * </p>
 *
 * @author chuang
 * @since 2021-04-30
 */
public interface IApiService extends IRowQueryService<Api> {
    default List<Api> findEnablers() {
        return lambdaQuery()
                .eq(Api::getEnabled, true).list();
    }

    default String genCode(String url, HttpMethod method) {
        if (url.endsWith("/") || url.endsWith("\\")) {
            return genCode(url.substring(0, url.length() - 1), method);
        } else {
            return HashKit.md5(method + ":" + url);
        }
    }

}
