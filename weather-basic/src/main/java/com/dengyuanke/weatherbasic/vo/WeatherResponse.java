package com.dengyuanke.weatherbasic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 整个消息的返回对象
 * @author dengyuanke
 */
@Data
public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 消息数据
	 */
	private Weather data;
	/**
	 * 消息状态
	 */
	private String status;
	/**
	 * 消息描述
	 */
	private String desc;


}
