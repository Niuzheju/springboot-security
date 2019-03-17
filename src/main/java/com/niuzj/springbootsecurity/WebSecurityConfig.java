package com.niuzj.springbootsecurity;

import com.niuzj.springbootsecurity.filter.CustomerFilter;
import com.niuzj.springbootsecurity.model.User;
import com.niuzj.springbootsecurity.service.CustomerUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置拦截规则
     * 每种规则都对于一个SecurityConfigurer,每一个SecurityConfigurer对应一个过滤器或者拦截器
     * authorizeRequests方法对应ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry--FilterSecurityInterceptor
     * formLogin方法对应FormLoginConfigurer--UsernamePasswordAuthenticationFilter
     * HttpSecurity内部会维护一个Filter集合
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**", "/login/**", "/logout")
        .permitAll()//放行前面的url

        .anyRequest()
        .authenticated()
        .and().csrf().disable()//spring security4默认开启csrf,这里需要关闭,否则报错
        //配置登录页面,登录处理url,登录成功页面,登录失败页面
        .formLogin().loginPage("/login/login.html").loginProcessingUrl("/login").successForwardUrl("/index.html").failureForwardUrl("/login/login.html")
        .permitAll()
        //退出登录设置
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login/login.html").invalidateHttpSession(true)
        .and().addFilter(new CustomerFilter());//添加自定义过滤器,用于验证指定的url
    }

    /**
     * 自定义ProviderManager
     */
    @Bean
    public ProviderManager providerManager(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(new CustomerUserDetailService());//UserDetailService
        daoAuthenticationProvider.setPasswordEncoder(User.PASSWORD_ENCODER);//PasswordEncoder
        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider));
    }

}
