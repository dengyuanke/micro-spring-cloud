package com.dengyuanke.weatherbasic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.dengyuanke.weatherbasic.util.XmlBuilder;
import com.dengyuanke.weatherbasic.vo.City;
import com.dengyuanke.weatherbasic.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


/**
 * 城市数据服务
 * @author dengyuanke
 */
@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";

		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}

		br.close();

		// XML转为Java对象
		CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());

		return cityList.getCityList();
	}

}
