/**
 * 
 */
package com.dengyuanke.weatherbasic.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.dengyuanke.weatherbasic.vo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dengyuanke
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {

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
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_API + "?citykey=" + cityId;
		return this.doGetWeatherData(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_API + "?city=" + cityName;
		return this.doGetWeatherData(uri);
	}

	private WeatherResponse doGetWeatherData(String uri) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		String key = uri;
		String strBody = null;

		// 先查缓存，没有再查服务
		if (!this.stringRedisTemplate.hasKey(key)) {
			log.info("不存在 key " + key);

			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

			if (response.getStatusCodeValue() == 200) {
				strBody = response.getBody();
			}

			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
		} else {
			log.info("存在 key " + key + ", value=" + ops.get(key));

			strBody = ops.get(key);
		}

		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse weather = null;

		try {
			weather = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			log.error("JSON反序列化异常！",e);
		}

		return weather;
	}
}