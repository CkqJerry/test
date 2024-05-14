package com.ckq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     * 可创建两个Bean一个配置测试，一个配置生产的环境不同的配置
     */
    @Bean
    public Docket restApi(Environment environment) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境,通过 enable() 接收此参数判断是否要显示
        boolean isShow = true;//= environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                // 配置分组
                .groupName("标准接口")
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(isShow)
                .apiInfo(apiInfo("Spring Boot中使用Swagger3构建RESTful APIs", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                //通过.select()方法，去配置扫描接口
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api . 配合@EnableOpenApi 找到API位置，不需要再在启动类上配置
                .apis(RequestHandlerSelectors.basePackage("com.cbry.swagger.controller"))
                //指定路径处理,PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://ip:port/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("更多请关注: https://blog.csdn.net/qq_37334150")
                .termsOfServiceUrl("https://blog.csdn.net/qq_37334150")
                .contact(new Contact("cbry", "https://blog.csdn.net/qq_37334150", "xxxx@163.com"))
                .version(version)
                .build();
    }
}
