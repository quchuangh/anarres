package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.User;
import com.chuang.anarres.office.sys.mapper.UserMapper;
import com.chuang.anarres.office.sys.service.IUserService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Service
public class UserServiceImpl extends RowQueryService<UserMapper, User> implements IUserService {

}
