package com.dengyuanke.report.service;

import com.dengyuanke.report.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 * Author: dyk
 * Create time: 2018/11/22  14:51
 */
@FeignClient("weather-data")
public interface WeatherDataClient {
    /**
     * 根据城市Id查询天气数据
     * @param cityId
     * @return
     */
    @GetMapping("/weather/cityId/{cityId}")
    WeatherResponse getDataByCityid(@PathVariable("cityId") String cityId);
}
