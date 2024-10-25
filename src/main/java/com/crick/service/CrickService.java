package com.crick.service;

import java.util.List;
import java.util.Optional;

import com.crick.entity.Match;
import com.crick.entity.UserInfo;

public interface CrickService {
	
	List<Match> getLiveMatchScores();
	
	 List<List<String>> getCWC2023PointTable();
	 
	 List<Match> getAllMatches();

	String userSave(UserInfo userInfo);

	List<UserInfo> findAllUser();

	UserInfo findById(Integer id);
	

}
