package com.example.baking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className SecurityConfig
 * @date 2023/05/30 15:26
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)// 开启方法授权的检查
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean// 添加此注解的目的是为了在Controller中自动装配自定义的登录验证界面
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean// 设置密码加密方式
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        // 返回此加密的编码器之后，用户输入的密码会通过此编码器加密之后再和数据库里面的密码进行比较
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 当访问需要登录认证才能访问的资源时，没有登录的时跳转到登录页面
        http.formLogin().loginPage("/login.html");// 弹出登录界面
        // 设置黑名单(需要登录即可访问的资源)
        String[] urls = {"/admin.html", "/personal.html", "/postArticle.html", "/articleManagement.html"};
        http.authorizeRequests()// 对请求进行授权
                .mvcMatchers(urls)// 设置黑名单路径数组
                .authenticated()// 需要通过登录认证
                .anyRequest()// 任意类型请求
                .permitAll();// 直接放行，既不需要登录也能访问
        // 关闭跨域攻击防御策略，否则所有post请求将失效
        http.csrf().disable();
    }
}
