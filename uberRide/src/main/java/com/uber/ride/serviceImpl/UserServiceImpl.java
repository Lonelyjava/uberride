package com.uber.ride.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.logging.Logger;
import com.uber.ride.controller.UberCabController;
import com.uber.ride.dto.SearchUberDto;
import com.uber.ride.entity.UberCabEntity;
import com.uber.ride.entity.UserEntity;
import com.uber.ride.reposistory.UberReposistory;
import com.uber.ride.reposistory.UserReposistory;
import com.uber.ride.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserReposistory userRepo;
	
	@Autowired
	UberReposistory uberRepo;
	
	
	@Override
	public UserEntity userRegistartion(UserEntity userentity) {
		Date date = new Date();
		userentity.setRegisterDate(date);
		userRepo.save(userentity);
		LOGGER.info("User create successully..");
		return userentity;
	}


	@Override
	public List<SearchUberDto> saerchCabUber(SearchUberDto searchUberDto) {
		List<SearchUberDto> serchDto = new ArrayList<SearchUberDto>();
		if(!searchUberDto.getLocation().isEmpty() && !searchUberDto.getMobile().isEmpty()) {
			
			
			Optional<UserEntity> userEntity = userRepo.findByMobile(searchUberDto.getMobile());
			if(userEntity.isPresent()) {
				List<UberCabEntity> uberCabEntity = uberRepo.findByLocation(searchUberDto.getLocation());
				for(UberCabEntity u:uberCabEntity) {
					SearchUberDto searDto = new SearchUberDto();
					searDto.setCarNo(u.getCarNo());
					searDto.setLocation(u.getLocation());
					searDto.setMobile(u.getDriverMobile());
					serchDto.add(searDto);
				}
				
			}else {
				LOGGER.info("user does not exists ...");
			}
			
		}
		
		return serchDto;
	}


}
