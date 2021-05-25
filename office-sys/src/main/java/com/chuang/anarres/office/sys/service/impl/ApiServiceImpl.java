package com.chuang.anarres.office.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.office.sys.entity.Api;
import com.chuang.anarres.office.sys.mapper.ApiMapper;
import com.chuang.anarres.office.sys.service.IApiService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-04-30
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements IApiService {

}
