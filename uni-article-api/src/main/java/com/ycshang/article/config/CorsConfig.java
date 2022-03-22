package com.ycshang.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-22 18:04
 **/
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //    session id多次访问一致
        corsConfiguration.setAllowCredentials(true);
        //    允许访问的客户端域名
        List<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("*");
        corsConfiguration.setAllowedOriginPatterns(allowedOriginPatterns);
        //    允许任何头
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}