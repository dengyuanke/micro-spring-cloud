package com.dengyuanke.collection.service;


import com.dengyuanke.collection.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * 访问城市信息的客户端.
 * @author dengyuanke
 */
@FeignClient("weather-city")
public interface CityClient {

	@GetMapping("/cities")
	List<City> listCity() throws Exception;
}
