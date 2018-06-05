package com.refactoring.rxo.admin.security.service;

import com.refactoring.rxo.admin.security.domain.SecurityUser;
import com.refactoring.rxo.admin.security.domain.Token;

/**
 * Token管理器<br>
 * 可存储到redis或者数据库<br>
 * 具体可看实现类<br>
 * 默认基于redis，实现类为 com.boot.security.server.service.impl.TokenServiceJWTImpl<br>
 * 如要换成数据库存储，将TokenServiceImpl类上的注解@Primary挪到com.boot.security.server.service.impl.TokenServiceDbImpl
 * 
 * 
 * @author 624191343@qq.com
 *
 *         2017年10月14日
 */
public interface TokenService {

	Token saveToken(SecurityUser loginUser);

	void refresh(SecurityUser loginUser);

	SecurityUser getLoginUser(String token);

	boolean deleteToken(String token);

}
