package com.example.shopping.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        StringBuilder desc = new StringBuilder();
        desc.append("图书管理系统接口文档");
        return new OpenAPI()
                .info(new Info()
                        .title("图书管理系统 - 接口文档")
                        .description(desc.toString())
                        .version("V1.0")
                        .contact(new Contact().name("wqk"))
                )

                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                new SecurityScheme()
                                        .name(HttpHeaders.AUTHORIZATION)
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                        )
                );

    }
    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        return openApi -> {
            // 全局添加鉴权参数
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((s, pathItem) -> {
                    // 登录接口/验证码不需要添加鉴权参数
                    if (s.equals("/user/login") || s.equals("/user/register")|| s.equals("/user/captcha")) {
                        return;
                    }
                    // 接口添加鉴权参数
                    pathItem.readOperations()
                            .forEach(operation ->
                                    operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                            );
                });
            }
        };
    }

}
