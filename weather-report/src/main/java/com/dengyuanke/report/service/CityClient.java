package com.dengyuanke.report.service;

import com.dengyuanke.report.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: dyk
 * Create time: 2018/11/22  14:49
 */
@FeignClient("weather-city")
public interface CityClient {
    @GetMapping("/cities")
    List<City> listCity() throws Exception;
}
