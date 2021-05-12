package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.DictItem;
import com.chuang.anarres.office.sys.mapper.DictItemMapper;
import com.chuang.anarres.office.sys.service.IDictItemService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典项; 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Service
public class DictItemServiceImpl extends RowQueryService<DictItemMapper, DictItem> implements IDictItemService {

}
