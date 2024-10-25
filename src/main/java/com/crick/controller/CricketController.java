package com.crick.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crick.dto.AuthRequest;
import com.crick.entity.UserInfo;
import com.crick.service.CrickService;
import com.crick.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cricket")
@CrossOrigin("*")
@EnableMethodSecurity
public class CricketController {

    private CrickService crickService;

    public CricketController(CrickService crickService) {
        this.crickService = crickService;
    }
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager manager;

    @GetMapping("/welcome")
    public String welcome() {
    	return "Welcome , this is crickinfo ";
    }
    
    // api for getting live matches

    @GetMapping("/live")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getLiveMatchScores() throws InterruptedException {
        System.out.println("getting live match");
        return new ResponseEntity<>(this.crickService.getLiveMatchScores(), HttpStatus.OK);
    }

    @GetMapping("/point-table")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(this.crickService.getCWC2023PointTable(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<com.crick.entity.Match>> getAllMatches() {
        return new ResponseEntity<>(this.crickService.getAllMatches(), HttpStatus.OK);
    }
    
    @PostMapping("/user-save")
    public ResponseEntity<UserInfo> saveUser(@RequestBody @Valid UserInfo userInfo){
    	crickService.userSave(userInfo);
		return new ResponseEntity<UserInfo> (HttpStatus.CREATED);
    }
    
    
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    	Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
    	
    	if(authenticate.isAuthenticated()) {
    		return jwtService.generateToken(authRequest.getName());
    	}
    	else {
			throw new UsernameNotFoundException("invalid user ");
		}
    
    	
    }
    
    @GetMapping("/all-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserInfo>> getUsers(){
    	
		return ResponseEntity.ok(crickService.findAllUser());
    }
    
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserInfo findById(@PathVariable Integer id) {	
		return crickService.findById(id);
    	
    }
	
}
