package com.uber.ride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.logging.Logger;
import com.uber.ride.dto.SearchUberDto;
import com.uber.ride.dto.UberRideDto;
import com.uber.ride.entity.UberCabEntity;
import com.uber.ride.service.UberService;

@RestController
@RequestMapping("/uber")
public class UberCabController {
	
	private static final Logger LOGGER = Logger.getLogger(UberCabController.class);
	@Autowired
	UberService uberService;

	@PostMapping("/registration")
	public ResponseEntity<String> uberCabRegistration(@RequestBody UberCabEntity uberCabEntity) {
		try {
			if (!uberCabEntity.getDriverName().isEmpty() &&  !uberCabEntity.getDriverMobile().isEmpty()&& !uberCabEntity.getCarNo().isEmpty() && !uberCabEntity.getLocation().isEmpty()) {
				uberService.uberCabRegistartion(uberCabEntity);
			}else {
				LOGGER.info("Driver Name and Mobile Number and Can No and Location! Can not be Null ");
				return new ResponseEntity<>(" Driver Name and Mobile Number and Can No and Location! Can not be Null .", HttpStatus.ACCEPTED);
				
			}
		} catch (Exception e) {
			LOGGER.info("Exception :" +e.getMessage());
		}
		
		return new ResponseEntity<>("UserCab Detail Registartion Successfully.", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/cabbook")
	public ResponseEntity<String> uberCabBook(@RequestBody UberRideDto uberRideDto) {
		UberRideDto uberRideDtos=null;
		try {
			if (!uberRideDto.getCarNo().isEmpty() && !uberRideDto.getMobile().isEmpty() && !uberRideDto.getFromLocation().isEmpty()&&!uberRideDto.getToLocation().isEmpty()) {
				uberRideDtos=uberService.uberCabBooks(uberRideDto);
			}else {
				LOGGER.info("Car No ,Form Location and To Location and Mobile Number  ! Can not be Null ");
				return new ResponseEntity<>(" Car No ,Form Location and To Location and Mobile Number  ! Can not be Null .", HttpStatus.ACCEPTED);
				
			}
		} catch (Exception e) {
			LOGGER.info("Exception :" +e.getMessage());
		}
		
		return new ResponseEntity<>("...Uber Cab Booked...\n ----Riding ------\n"+"\n From Location :"+uberRideDto.getFromLocation() +"\n To Location : "+uberRideDto.getToLocation()+"\n Total Fare Cost : "+uberRideDtos.getTotalCost()+" Rs.", HttpStatus.ACCEPTED);
	}
}
