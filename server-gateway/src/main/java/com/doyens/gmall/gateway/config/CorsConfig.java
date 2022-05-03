package com.doyens.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){
        //创建一个cors跨域配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //设置允许访问的网络
        configuration.addAllowedOrigin("*");
        // 设置是否从服务器获取cookie
        configuration.setAllowCredentials(true);
        // 设置请求方法 * 表示任意
        configuration.addAllowedMethod("*");
        // 所有请求头信息 * 表示任意
        configuration.addAllowedHeader("*");
        //配置源对象
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsWebFilter(corsConfigurationSource);
    }
}
