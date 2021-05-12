package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.DictType;
import com.chuang.anarres.office.sys.mapper.DictTypeMapper;
import com.chuang.anarres.office.sys.service.IDictTypeService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型; 服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Service
public class DictTypeServiceImpl extends RowQueryService<DictTypeMapper, DictType> implements IDictTypeService {

}
