package com.dengyuanke.data.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 天气信息类
 * @author dengyuanke
 */
@Data
public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 空气指数
	 */
	private String aqi;
	/**
	 * 温度
	 */
	private String wendu;
	/**
	 * 感冒指数
	 */
	private String ganmao;

	private Yesterday yesterday;

	private List<Forecast> forecast;

}
