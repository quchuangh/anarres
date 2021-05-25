package com.chuang.anarres.crud.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.chuang.anarres.crud.entity.I18n;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 国际化  Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2020-12-21
 */
@InterceptorIgnore(illegalSql = "true")
public interface I18nMapper extends BaseMapper<I18n> {

}
