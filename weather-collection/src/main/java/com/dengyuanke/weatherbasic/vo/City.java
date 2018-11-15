package com.dengyuanke.weatherbasic.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 城市
 * @author dengyuanke
 */
@Data
public class City {

	private String cityId;

	private String cityName;

	private String cityCode;

	private String province;

}
