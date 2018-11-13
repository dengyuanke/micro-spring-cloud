package com.dengyuanke.weatherbasic.vo;

import lombok.Data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 城市列表
 * @author dengyuanke
 */
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CityList {

	@XmlElement(name = "d")
	private List<City> cityList;


}
