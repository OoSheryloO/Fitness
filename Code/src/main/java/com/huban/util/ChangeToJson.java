package com.huban.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * File_Name:JsonUtil.java Package_Name:main
 * Date:2016年7月15日下午4:48:30 
 * Help:Json 操作。采用阿里巴巴的jar包。
 */
public class ChangeToJson {
	


	/**
	* 传入json，转换成指定Object
	* 
	* @param jsonStr
	*            json
	* @param clazz
	* @return 
	* @date 2016年7月19日 上午11:55:06
	*/
	public static <T> T toBean_fastJson(String jsonStr, Class<T> clazz) {
	return JSON.parseObject(jsonStr, clazz);
	}


	/**
	* 将对象转换成json字符串，
	* 
	* @param obj
	*            对象。
	* @param isFormat
	*            是否做格式化。
	* @return
	* @date 2016年7月19日 下午12:26:28
	*/
	public static String toJson_fastJson(Object obj, boolean isFormat) {
	if (isFormat) {
	return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat);
	} else {
	return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
	}
	}


	/**
	* JSON数据转换成List<T>对象
	* 
	* @param jsonStr
	*            JSON数据
	* @param clazz
	*            泛型
	* @return
	* @date 2016年7月19日 下午12:29:41
	*/
	public static <T> List<T> parseList_faskJson(String jsonStr, Class<T> clazz) {
	return JSON.parseArray(jsonStr, clazz);
	}	
}
