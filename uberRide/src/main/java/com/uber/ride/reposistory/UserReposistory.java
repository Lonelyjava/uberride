package com.uber.ride.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uber.ride.entity.UserEntity;

@Repository
public interface UserReposistory extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByMobile(String mobile);



}
