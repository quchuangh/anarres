package com.chuang.anarres.office.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.office.sys.entity.Config;
import com.chuang.anarres.office.sys.mapper.ConfigMapper;
import com.chuang.anarres.office.sys.service.IConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表  服务实现类
 * </p>
 *
 * @author chuang
 * @since 2021-05-11
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
