package com.dengyuanke.data.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 昨日天气信息
 * @author dengyuanke
 */
@Data
public class Yesterday implements Serializable {

	private static final long serialVersionUID = 1L;

	private String date;

	private String high;

	private String fx;

	private String low;

	private String fl;

	private String type;
}
