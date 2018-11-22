package com.dengyuanke.collection.job;

import java.util.ArrayList;
import java.util.List;

import com.dengyuanke.collection.service.CityClient;
import com.dengyuanke.collection.service.WeatherDataCollectionService;
import com.dengyuanke.collection.vo.City;
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
	private WeatherDataCollectionService weatherDataCollectionServiceImpl;

	@Autowired
	private CityClient cityClient;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("Start 天气数据同步任务");
		
		//由城市数据API微服务来提供数据
		List<City> cityList = null;
		try {
			cityList=cityClient.listCity();
		} catch (Exception e) {
			log.error("获取城市信息异常！", e);
			throw new RuntimeException("获取城市信息异常",e);
		}
		
		for (City city : cityList) {
			String cityId = city.getCityId();
			log.info("天气数据同步任务中，cityId:" + cityId);
			// 根据城市ID获取天气
			weatherDataCollectionServiceImpl.syncDataByCityId(cityId);
		}
		
		log.info("End 天气数据同步任务");
	}

}
