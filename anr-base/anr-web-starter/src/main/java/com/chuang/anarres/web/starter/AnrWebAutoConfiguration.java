package com.chuang.anarres.web.starter;


import com.chuang.tauceti.rowquery.RowQueryConverter;
import com.chuang.tauceti.rowquery.handlers.AutoTimeHandler;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableSwagger2
public class AnrWebAutoConfiguration implements WebMvcConfigurer {
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
                .title("服务端接口文档")
                .description("所有/仅CRUD模块/除CRUD以外模块的文档")
                .termsOfServiceUrl("http://localhost:8280")
                .version("1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public AutoTimeHandler autoTimeHandler(OperatorManager operatorManager) {
        return new AutoTimeHandler(operatorManager);
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new RowQueryConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PageOrRestInterceptor());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // 为 String 类型自定义反序列化操作
            jacksonObjectMapperBuilder
                    .deserializerByType(String.class, new StdScalarDeserializer<Object>(String.class) {
                        @Override
                        public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
                                throws IOException {
                            // 去除前后空格
                            return StringUtils.trimWhitespace(jsonParser.getValueAsString());
                        }
                    });
        };
    }
}
