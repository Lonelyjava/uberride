package com.uber.ride.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.logging.Logger;
import com.uber.ride.dto.UberRideDto;
import com.uber.ride.entity.UberCabEntity;
import com.uber.ride.entity.UberRideDetialsEntity;
import com.uber.ride.entity.UserEntity;
import com.uber.ride.reposistory.UberReposistory;
import com.uber.ride.reposistory.UberRideDetialsReposistory;
import com.uber.ride.reposistory.UserReposistory;
import com.uber.ride.service.UberService;

@Service
public class UberServiceImpl implements UberService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UberReposistory uberRepo;

	@Autowired
	UserReposistory userRepo;
	
	@Autowired
	UberRideDetialsReposistory uberRideDetialsReposistory;

	@Override
	public UberCabEntity uberCabRegistartion(UberCabEntity uberCabEntity) {
		Date date = new Date();
		uberCabEntity.setRegisterDate(date);
		uberRepo.save(uberCabEntity);
		LOGGER.info("uber cab register successully..");
		return uberCabEntity;
	}

	@Override
	public UberRideDto uberCabBooks(UberRideDto uberRideDto) {
		UberRideDto serchDto = new UberRideDto();
		if (!uberRideDto.getMobile().isEmpty() && !uberRideDto.getCarNo().isEmpty()) {
			UberRideDto uberDto = new UberRideDto();

			Optional<UserEntity> userEntity = userRepo.findByMobile(uberRideDto.getMobile());
			Optional<UberCabEntity> uberCabEntity = uberRepo.findByCarNo(uberRideDto.getCarNo());
			if (userEntity.isPresent() && uberCabEntity.isPresent()) {

				if (!uberRideDto.getFromLocation().isEmpty() && !uberRideDto.getToLocation().isEmpty()) {
					UberRideDetialsEntity uberRide= new UberRideDetialsEntity();
					Date date = new Date();
					int totaldistance = 2;
					int distance = totaldistance * 1000;
					int meter = 0;
					int totalCost = 0;
					while (meter <= totaldistance) {
						if (meter <= 100) {
							totalCost = 100;
						}
						if (meter > 100 && meter <= 500) {
							totalCost = totalCost + 50;
						}
						if (meter > 500) {
							totalCost = totalCost + 10;
						}
						meter = meter + 1;
					}
					serchDto.setTotalCost(totalCost);
					uberRide.setCarNo(uberRideDto.getCarNo());
					uberRide.setFromLocation(uberRideDto.getFromLocation());
					uberRide.setMobile(uberRideDto.getMobile());
					uberRide.setToLocation(uberRideDto.getToLocation());
					uberRide.setRideDate(date);
					uberRide.setTotalCost(totalCost);
					uberRideDetialsReposistory.save(uberRide);
					LOGGER.info("uber ride information has been saved ...");
					System.out.println("uber ride get information saved ....");
				}

			} else {
				LOGGER.info("user does car details does not exists ...");
			}

		}
		return serchDto;
	}

}
