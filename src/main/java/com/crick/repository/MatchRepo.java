package com.crick.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crick.entity.Match;
@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
	
	 Optional<Match> findByTeamHeading(String teamHeading);
	

}
