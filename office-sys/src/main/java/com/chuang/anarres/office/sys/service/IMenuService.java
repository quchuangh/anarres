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

    default boolean saveAndCheckParents(Menu menu) {

        String parentPath;
        if(menu.getParentId() == 0) {// 表示是根目录
            parentPath = "0/";
        } else {
            Menu parent = findById(menu.getParentId())
                    .orElseThrow(() -> new BusinessException("父级菜单无法找到"));
            parentPath = parent.getParents() + "/" + parent.getId() + "/";
        }

        menu.setParents(parentPath);

        if(Optional.ofNullable(menu.getId()).map(id -> 0 == id).orElse(true)) {//是否不存在id
            return save(menu);
        } else {
            return updateById(menu);
        }
    }

}
