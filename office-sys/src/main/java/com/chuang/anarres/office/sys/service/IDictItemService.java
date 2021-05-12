package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.DictItem;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 字典项; 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
public interface IDictItemService extends IRowQueryService<DictItem>, ITreeService<DictItem> {
    default List<DictItem> findByType(String dictType) {
        return lambdaQuery().eq(DictItem::getDictTypeCode, dictType).list();
    }

}
