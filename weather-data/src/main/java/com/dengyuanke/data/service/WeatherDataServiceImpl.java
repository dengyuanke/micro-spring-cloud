/**
 * 
 */
package com.dengyuanke.data.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.dengyuanke.data.vo.WeatherResponse;
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
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 通过城市名称获得天气数据 : http://wthrcdn.etouch.cn/weather_mini?city=深圳
	 * 通过城市ID获得天气数据 : http://wthrcdn.etouch.cn/weather_mini?citykey=10128060
	 */

	private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";

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

		// 先查缓存，没吵到抛出异常
		if (!this.stringRedisTemplate.hasKey(key)) {
			log.info("不存在 key " + key);

			throw new RuntimeException("没有相应的天气信息");
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
			throw new RuntimeException("天气信息解析失败");
		}

		return weather;
	}
}