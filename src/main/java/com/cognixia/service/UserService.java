package com.cognixia.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognixia.model.LoginDetails;

@Service
@FeignClient("user-service")
public interface UserService {
	
	@PostMapping("user/login")
	public int login(@RequestBody LoginDetails loginDetails);
	
}
