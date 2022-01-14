package com.uber.ride.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.ride.entity.UberRideDetialsEntity;

@Repository
public interface UberRideDetialsReposistory extends JpaRepository<UberRideDetialsEntity, Long>{

}
