package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.DictItem;

import java.util.List;

/**
 * <p>
 * 字典项; 服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
public interface IDictItemService extends ITreeService<DictItem> {
    default List<DictItem> findByType(String dictType) {
        return lambdaQuery().eq(DictItem::getDictTypeCode, dictType).list();
    }

}
