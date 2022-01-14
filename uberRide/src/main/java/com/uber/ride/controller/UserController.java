package com.uber.ride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.logging.Logger;
import com.uber.ride.dto.SearchUberDto;
import com.uber.ride.entity.UserEntity;
import com.uber.ride.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<String> userRegistration(@RequestBody UserEntity userEntity) {
		try {
			if (!userEntity.getName().isEmpty() &&  !userEntity.getMobile().isEmpty()) {
				userService.userRegistartion(userEntity);
			}else {
				return new ResponseEntity<>("Name and Mobile Number Can not be Null .", HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			System.out.println("Exception :"+e.getMessage());
		}
		
		return new ResponseEntity<>("User Registartion Successfully.", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/searchUber")
	@ResponseBody
	public List<SearchUberDto> searchUberCab(@RequestBody SearchUberDto searchUberDto) {
		List<SearchUberDto> serchDto=null;
		try {
			if (!searchUberDto.getMobile().isEmpty() && !searchUberDto.getLocation().isEmpty()) {
				serchDto  =	userService.saerchCabUber(searchUberDto);
				LOGGER.info("Below cab availbale for this location "+searchUberDto.getLocation());
			}else {
				LOGGER.info("Mobile and Location can not be null..");
			}
		} catch (Exception e) {
			System.out.println("Exception :"+e.getMessage());
		}
		
		return serchDto;
	}
}
