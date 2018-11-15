package com.dengyuanke.weatherbasic.job;

import java.util.List;

import com.dengyuanke.weatherbasic.service.CityDataService;
import com.dengyuanke.weatherbasic.service.WeatherDataService;
import com.dengyuanke.weatherbasic.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 天气数据同步任务
 * @author dengyuanke
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {
	
	@Autowired
	private CityDataService cityDataServiceImpl;
	
	@Autowired
	private WeatherDataService weatherDataServiceImpl;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("Start 天气数据同步任务");
		
		// 读取城市列表
		List<City> cityList = null;
		try {
			cityList = cityDataServiceImpl.listCity();
		} catch (Exception e) {
			log.error("获取城市信息异常！", e);
		}
		
		for (City city : cityList) {
			String cityId = city.getCityId();
			log.info("天气数据同步任务中，cityId:" + cityId);
			// 根据城市ID获取天气
			weatherDataServiceImpl.syncDataByCityId(cityId);
		}
		
		log.info("End 天气数据同步任务");
	}

}
