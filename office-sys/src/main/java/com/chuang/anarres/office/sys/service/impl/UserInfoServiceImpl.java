package com.chuang.anarres.office.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.office.sys.entity.UserInfo;
import com.chuang.anarres.office.sys.mapper.UserInfoMapper;
import com.chuang.anarres.office.sys.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
