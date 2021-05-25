package com.chuang.anarres.rbac.configuration;

import com.chuang.anarres.rbac.OfficeUtils;
import com.chuang.anarres.rbac.model.ShiroUser;
import com.chuang.anarres.web.starter.AnrWebConfigurer;
import com.chuang.anarres.web.starter.OperatorManager;
import com.chuang.tauceti.shiro.spring.web.jwt.configuration.ShiroJwtAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 让自己比ShiroWebAutoConfiguration 先导入，并在第一行写入 @Import(ShiroConfiguration.class)
 * 用来保证 ShiroConfiguration.class 比 ShiroWebAutoConfiguration 先加载。免得内存中出现一堆实现
 */
@Configuration
@EnableSwagger2
@AutoConfigureBefore(ShiroJwtAutoConfiguration.class)
public class OfficeSysModuleAutoConfiguration implements WebMvcConfigurer, AnrWebConfigurer {

    @Override
    @Bean
    public OperatorManager operatorManager() {
        return OperatorManager.create(() -> OfficeUtils.shiroUser().map(ShiroUser::getName));
    }
}
