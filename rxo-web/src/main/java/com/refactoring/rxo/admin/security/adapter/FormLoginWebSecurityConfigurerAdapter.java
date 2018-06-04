package com.refactoring.rxo.admin.security.adapter;


import com.refactoring.rxo.admin.security.handler.AuthFailureHandler;
import com.refactoring.rxo.admin.security.handler.AuthSuccessHandler;
import com.refactoring.rxo.admin.security.service.SecurityUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author: fei.yao
 * @date: 2018/5/23
 * @modified by:
 */
@Configuration
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Bean
    UserDetailsService customUserService() {
        return new SecurityUserService();
    }


    PasswordEncoder passwordEncoder = new StandardPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return passwordEncoder.encode(rawPassword);
            }

            /**
             * @param rawPassword 明文
             * @param encodedPassword 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return passwordEncoder.matches(rawPassword, encodedPassword);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] permitted = new String[]{
                "/", "/home", "/register", "/about",
                "/css/**", "/docs/**", "/fonts/**", "/img/**", "/js/**", "/plugins/**"};
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers(permitted);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(new AuthSuccessHandler())
                .failureHandler(new AuthFailureHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                //只有系统管理员才有权限操作/admin 接口下的业务
                .antMatchers("/admin/**").hasRole("系统管理员")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();


        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

}
