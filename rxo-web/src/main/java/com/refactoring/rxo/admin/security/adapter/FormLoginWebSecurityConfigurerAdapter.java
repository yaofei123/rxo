package com.refactoring.rxo.admin.security.adapter;


import com.refactoring.rxo.admin.security.filter.TokenFilter;
import com.refactoring.rxo.admin.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author: fei.yao
 * @date: 2018/5/23
 * @modified by:
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService securityUserService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private TokenService tokenService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return passwordEncoder().encode(rawPassword);
            }

            /**
             * @param rawPassword 明文
             * @param encodedPassword 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return passwordEncoder().matches(rawPassword, encodedPassword);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/plugins/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/lib/**");
        web.ignoring().antMatchers("/docs/**");
        web.ignoring().antMatchers("/favicon.ico");
        // 忽略登录界面
        web.ignoring().antMatchers("/login.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers("/", "/*.html","/api/**").permitAll()
                //只有系统管理员才有权限操作/admin 接口下的业务
                .antMatchers("/admin/**").hasRole("系统管理员")
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

        // 解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();
        http.headers().cacheControl();


        //新增token过滤器
        TokenFilter tokenFilter = new TokenFilter();
        tokenFilter.setTokenService(tokenService);
        tokenFilter.setUserDetailsService(securityUserService);
        tokenFilter.afterPropertiesSet();
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

}
