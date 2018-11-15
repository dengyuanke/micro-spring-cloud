/**
 * 
 */
package com.dengyuanke.weatherbasic.service;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author dengyuanke
 */
@Service
@Slf4j
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 通过城市名称获得天气数据 : http://wthrcdn.etouch.cn/weather_mini?city=深圳
	 * 通过城市ID获得天气数据 : http://wthrcdn.etouch.cn/weather_mini?citykey=10128060
	 */

	private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";
	/**
	 * 缓存超时时间 30分钟
	 */
	private final Long TIME_OUT = 1800L;


	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_API + "?citykey=" + cityId;
		this.saveWeatherData(uri);
	}

	private void saveWeatherData(String uri) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		String key = uri;
		String strBody = null;

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

		if (response.getStatusCodeValue() == 200) {
			strBody = response.getBody();
		}

		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

	}
}