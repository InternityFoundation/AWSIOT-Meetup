package com.aws.iotdemo.service;

import java.util.List;

import com.aws.iotdemo.model.TemperatureModel;

public interface TemperatureService {

	List<TemperatureModel> getTodaysTemperature();
}
