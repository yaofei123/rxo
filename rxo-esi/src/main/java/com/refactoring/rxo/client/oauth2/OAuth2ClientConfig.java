
package com.refactoring.rxo.client.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2018年4月5日 下午19:52:21
 */
@Component
@Configuration
@EnableResourceServer
public class OAuth2ClientConfig extends ResourceServerConfigurerAdapter {

    //对应oauth_client_details的 resource_ids字段 如果表中有数据 client_id只能访问响应resource_ids的资源服务器
    private static final String RESOURCE_ID = "rxo-esi";

    @Resource
    private ObjectMapper objectMapper; //springmvc启动时自动装配json处理类

    @Autowired(required = false)
    private TokenStore redisTokenStore;

    @Autowired(required = false)
    private JwtTokenStore jwtTokenStore;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        if (jwtTokenStore != null) {
            resources.tokenStore(jwtTokenStore);
        } else if (redisTokenStore != null) {
            resources.tokenStore(redisTokenStore);
        }
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                })
                .and()
                .authorizeRequests()
                .antMatchers("/demo/**").authenticated()
                .and()
                .httpBasic();
    }
}
