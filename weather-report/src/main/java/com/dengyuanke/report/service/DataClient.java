package com.dengyuanke.report.service;

import com.dengyuanke.report.vo.City;
import com.dengyuanke.report.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: dyk
 * Create time: 2018/12/25  11:21
 */
@FeignClient(name = "weather-zuul-api",fallback = DataClientFallback.class)
public interface DataClient {

    /**
     * 获取城市列表
     * @return
     * @throws Exception
     */
    @GetMapping("/cities")
    List<City> listCity() throws Exception;

    /**
     * 根据城市Id查询天气数据
     * @param cityId
     * @return
     */
    @GetMapping("/weather/cityId/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}
