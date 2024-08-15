package com.tia102g5.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tia102g5.loginfilter.LoginFilter;

@Configuration
public class FilterConfig {

	
//	@Bean
//    public FilterRegistrationBean<LoginFilter> loginFilter() {
//        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoginFilter());
//        registrationBean.addUrlPatterns("/admin"); // 設定過濾的 URL 模式
//        return registrationBean;
//    }
}
