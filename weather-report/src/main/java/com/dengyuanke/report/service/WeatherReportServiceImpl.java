package com.dengyuanke.report.service;

import com.dengyuanke.report.vo.Weather;
import com.dengyuanke.report.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 天气预报服务
 * @author dengyuanke
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {


	@Autowired
	private WeatherDataClient weatherDataClient;

	@Override
	public Weather getDataByCityId(String cityId) {
		// 由天气数据API微服务来提供数据
		WeatherResponse response = weatherDataClient.getDataByCityid(cityId);
		return response.getData();
	}
}
