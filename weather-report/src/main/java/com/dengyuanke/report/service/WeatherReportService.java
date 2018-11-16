package com.dengyuanke.report.service;


import com.dengyuanke.report.vo.Weather;

/**
 * 天气预报服务
 * @author dengyuanke
 */
public interface WeatherReportService {

	/**
	 * 根据城市ID查询天气信息
	 * 
	 * @param cityId
	 * @return
	 */
	Weather getDataByCityId(String cityId);

}
