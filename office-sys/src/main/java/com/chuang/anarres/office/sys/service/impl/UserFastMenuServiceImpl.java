package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.UserFastMenu;
import com.chuang.anarres.office.sys.service.IUserFastMenuService;
import com.chuang.anarres.office.sys.mapper.UserFastMenuMapper;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户快捷菜单  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Service
public class UserFastMenuServiceImpl extends RowQueryService<UserFastMenuMapper, UserFastMenu> implements IUserFastMenuService {

}
