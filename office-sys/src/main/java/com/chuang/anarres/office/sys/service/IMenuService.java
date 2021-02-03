package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Menu;
import com.chuang.anarres.office.sys.model.bo.MenuBO;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 菜单表; 服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IMenuService extends IRowQueryService<Menu> {


    List<MenuBO> menusWithFast(String username);

}
