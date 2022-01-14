package com.uber.ride.service;

import com.uber.ride.dto.SearchUberDto;
import com.uber.ride.dto.UberRideDto;
import com.uber.ride.entity.UberCabEntity;

public interface UberService {
	
	public UberCabEntity uberCabRegistartion(UberCabEntity uberCabEntity);

	public UberRideDto uberCabBooks( UberRideDto uberRideDto);

}
