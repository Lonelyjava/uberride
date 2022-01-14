package com.uber.ride.reposistory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.ride.entity.UberCabEntity;

@Repository
public interface UberReposistory extends JpaRepository<UberCabEntity, Long>{
	
	public List<UberCabEntity> findByLocation(String location);

	public Optional<UberCabEntity> findByCarNo(String carNo);

}
