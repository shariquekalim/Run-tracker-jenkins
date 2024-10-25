package com.crick.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.crick.entity.UserInfo;
import com.crick.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailService implements UserDetailsService {
	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo= repository.findByName(username);
		
		return userInfo.map(UserInfoUSerDetail::new)
		.orElseThrow(()->new UsernameNotFoundException("user not found" + username));

		
	}

		
	}


