package com.aws.iotdemo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.iotdemo.domain.Temperature;
import com.aws.iotdemo.model.TemperatureModel;
import com.aws.iotdemo.repository.TemperatureRepository;

@Service
public class TemperatureServiceImpl implements TemperatureService {

	@Autowired
	private TemperatureRepository temperatureRepository;

	@Override
	public List<TemperatureModel> getTodaysTemperature() {
		List<Temperature> temperatureList = temperatureRepository.findByDatetimeGreaterThan(LocalDateTime.now().minusHours(24).atZone(ZoneId.systemDefault()).toEpochSecond());
		
		List<TemperatureModel> temperatureModels = new ArrayList<TemperatureModel>();
		
		temperatureModels = temperatureList.stream()
				.map(temperature->temperatureToTemperatureModel(temperature)).collect(Collectors.toList());
		
		return temperatureModels;
	}
	
	private TemperatureModel temperatureToTemperatureModel(Temperature temperature) {
		
		TemperatureModel temperatureModel = new TemperatureModel();
		
		temperatureModel.setDateTime(temperature.getDatetime());
		temperatureModel.setHumidity(temperature.getHumidity());
		temperatureModel.setTemperature(temperature.getTemperature());
		temperatureModel.setId(temperature.getId());
		
		return temperatureModel;
	}
}
