package com.uber.ride.service;

import java.util.List;

import com.uber.ride.dto.SearchUberDto;
import com.uber.ride.entity.UberCabEntity;
import com.uber.ride.entity.UserEntity;

public interface UserService {
	
	public UserEntity userRegistartion(UserEntity userentity);

	public List<SearchUberDto> saerchCabUber(SearchUberDto searchUberDto);

}
