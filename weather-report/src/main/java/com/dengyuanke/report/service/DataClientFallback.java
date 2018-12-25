package com.dengyuanke.report.service;

import java.util.ArrayList;
import java.util.List;

import com.dengyuanke.report.vo.City;
import com.dengyuanke.report.vo.WeatherResponse;
import org.springframework.stereotype.Component;


/**
 * DataClient Fallback.
 * 
 * @since 1.0.0 2017年11月13日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Component
public class DataClientFallback implements DataClient {

	@Override
	public List<City> listCity() throws Exception {
		List<City> cityList = null;
		cityList = new ArrayList<>();
		City city = new City();
		city.setCityId("101280601");
		city.setCityName("深圳");
		cityList.add(city);
		
		city = new City();
		city.setCityId("101280301");
		city.setCityName("惠州");
		cityList.add(city);
		return cityList;
	}

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		return new WeatherResponse();
	}

}
