package com.aws.iotdemo.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aws.iotdemo.domain.Temperature;

@Repository
public interface TemperatureRepository extends CrudRepository<Temperature,Double>{
	
	@EnableScan
	List<Temperature> findByDatetimeGreaterThan(Long dateTime);
}
