package com.dengyuanke.weatherbasic.service;


import com.dengyuanke.weatherbasic.vo.WeatherResponse;

/**
 * @author dengyuanke
 */
public interface WeatherDataService {

	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityName
	 * @return
	 */
	WeatherResponse getDataByCityName(String cityName);


	/**
	 * 根据城市ID同步天气数据
	 *
	 * @param cityId
	 * @return
	 */
	void syncDataByCityId(String cityId);
}
