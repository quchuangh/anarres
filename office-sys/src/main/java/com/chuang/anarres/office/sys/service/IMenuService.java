package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.Menu;
import com.chuang.anarres.office.sys.model.bo.TreeMenuBO;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;
import java.util.Optional;

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
