package com.chuang.anarres.office.sys.configuration;

import com.chuang.anarres.office.sys.OperatorManager;
import com.chuang.tauceti.shiro.spring.web.jwt.configuration.ShiroJwtAutoConfiguration;
import com.chuang.urras.rowquery.RowQueryConverter;
import com.chuang.urras.rowquery.handlers.AutoTimeHandler;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * 让自己比ShiroWebAutoConfiguration 先导入，并在第一行写入 @Import(ShiroConfiguration.class)
 * 用来保证 ShiroConfiguration.class 比 ShiroWebAutoConfiguration 先加载。免得内存中出现一堆实现
 */
@Configuration
@EnableSwagger2
@AutoConfigureBefore(ShiroJwtAutoConfiguration.class)
public class OfficeSysModuleAutoConfiguration implements WebMvcConfigurer {
    @Bean("allApisSwagger")
    public Docket allApisSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("所有")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chuang"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统后台服务端接口文档")
                .description("所有/仅CRUD模块/除CRUD以外模块的文档")
                .termsOfServiceUrl("http://localhost:8280")
                .version("1.0")
                .build();
    }

    @Bean
    public AutoTimeHandler autoTimeHandler() {
        return new AutoTimeHandler(operatorManager());
    }

    @Bean
    public OperatorManager operatorManager() {
        return new OperatorManager();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new RowQueryConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageOrRestInterceptor());
    }
}
