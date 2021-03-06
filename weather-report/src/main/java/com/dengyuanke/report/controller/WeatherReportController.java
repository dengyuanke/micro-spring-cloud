package com.dengyuanke.report.controller;

import com.dengyuanke.report.service.DataClient;
import com.dengyuanke.report.service.WeatherReportService;
import com.dengyuanke.report.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * 天气预报API
 * @author dengyuanke
 */
@RestController
@RequestMapping("/report")
@Slf4j
public class WeatherReportController {

	@Autowired
	private WeatherReportService weatherReportService;

	@Autowired
	private DataClient cityClient;

	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
		// 由城市数据API微服务来提供数据

		List<City> cityList = null;
		try {
			//调用城市数据API
			cityList=cityClient.listCity();
		} catch (Exception e) {
			log.error("获取城市信息异常！", e);
			throw new RuntimeException("获取城市信息异常!",e);
		}

		model.addAttribute("title", "老卫的天气预报");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityList);
		model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report", "reportModel", model);
	}


}
