package com.chuang.anarres.office.sys.service.impl;

import com.chuang.anarres.office.sys.entity.UserInfo;
import com.chuang.anarres.office.sys.mapper.UserInfoMapper;
import com.chuang.anarres.office.sys.service.IUserInfoService;
import com.chuang.urras.rowquery.RowQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Service
public class UserInfoServiceImpl extends RowQueryService<UserInfoMapper, UserInfo> implements IUserInfoService {

}
