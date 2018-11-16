package com.dengyuanke.data.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 未来天气信息.
 * @author dengyuanke
 */
@Data
public class Forecast implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 日期，包含未来 5 天
	 */
	private String date;

	/**
	 * 最高温度
	 */
	private String high;
	/**
	 * 风向
	 */
	private String fengxiang;
	/**
	 * 最低温度
	 */
	private String low;
	/**
	 * 风力
	 */
	private String fengli;
	/**
	 * 天气类型
	 */
	private String type;


}
