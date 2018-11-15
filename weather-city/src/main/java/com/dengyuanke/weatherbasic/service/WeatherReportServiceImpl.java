package com.dengyuanke.weatherbasic.service;

import com.dengyuanke.weatherbasic.vo.Weather;
import com.dengyuanke.weatherbasic.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 天气预报服务
 * @author dengyuanke
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

	@Autowired
	private WeatherDataService weatherDataServiceImpl;
	
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse result = weatherDataServiceImpl.getDataByCityId(cityId);
		return result.getData();
	}

}
