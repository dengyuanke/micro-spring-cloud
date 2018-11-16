package com.dengyuanke.weatherbasic.controller;

import com.dengyuanke.weatherbasic.service.CityDataService;
import com.dengyuanke.weatherbasic.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dengyuanke
 */
@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityDataService cityDataService;

	@GetMapping
	public List<City> listCity() throws Exception{
		return cityDataService.listCity();
	}

}
