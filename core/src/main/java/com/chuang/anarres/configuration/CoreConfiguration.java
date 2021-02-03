package com.chuang.anarres.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ComponentScan("com.chuang.anarres")
@MapperScan(
        basePackages= {"com.chuang.anarres"},
        markerInterface = BaseMapper.class
)
public class CoreConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 配置数据源
     */
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource beanDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
//        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);


        return paginationInterceptor;
    }
}
