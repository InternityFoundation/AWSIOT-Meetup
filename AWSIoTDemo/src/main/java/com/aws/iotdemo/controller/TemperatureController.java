package com.aws.iotdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aws.iotdemo.model.TemperatureModel;
import com.aws.iotdemo.service.TemperatureService;

@RestController
@RequestMapping("/aws/temperature")
public class TemperatureController {
	
	@Autowired
	private TemperatureService temperatureService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TemperatureModel> getDayTemperature(HttpServletResponse response,
			HttpServletRequest request)  {
	
		List<TemperatureModel> temperatureModels = temperatureService.getTodaysTemperature();
		
		return temperatureModels;
	}
	

}
