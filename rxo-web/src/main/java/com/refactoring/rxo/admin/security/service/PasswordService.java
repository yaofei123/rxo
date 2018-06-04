package com.refactoring.rxo.admin.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by fai.yao on 2017/2/18.
 */
@Service
public class PasswordService implements PasswordEncoder{

	PasswordEncoder passwordEncoder = new StandardPasswordEncoder();


	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
