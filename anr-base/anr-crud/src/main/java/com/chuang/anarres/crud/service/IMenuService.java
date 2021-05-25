package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.Menu;
import com.chuang.anarres.crud.entity.bo.TreeMenuBO;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.List;

/**
 * <p>
 * 菜单表; 服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface IMenuService extends IRowQueryService<Menu>, ITreeService<Menu> {


    List<TreeMenuBO> userMenus(String username);

}
