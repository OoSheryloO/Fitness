/**  
 * @Title: FastjsonRemoveNullValue.java
 * @Package cn.net.sheryl.Demo
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Sheryl
 * @date 2017年11月2日 上午11:18:28
 * @version V1.0  
 */
    
package cn.net.sheryl.Demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import cn.net.sheryl.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: FastjsonRemoveNullValue
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年11月2日 上午11:18:28
 *
 */

public class FastjsonRemoveNullValue {

	public static void main(String[] args) {

		List<User> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			User entity = new User();
			entity.setId((long) i);
			entity.setPassword("");
			entity.setUsername("来自中文" + i);
			entity.setToken(UUID.randomUUID().toString());
			// list.add(entity);
			list.add(0, entity);
		}
		String json = JSON.toJSONString(list);
		System.out.println(json);
		/*
		 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
		 * WriteMapNullValue——–是否输出值为null的字段,默认为false
		 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
		 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
		 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
		 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		 */
		// 使用双引号
		System.out.println(JSONObject.toJSONString(list, SerializerFeature.QuoteFieldNames));
		// 输出值为null的字段
		System.out.println(JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue));
		System.out.println(JSONObject.toJSONString(list, SerializerFeature.WriteNullNumberAsZero));
		System.out.println(JSONObject.toJSONString(new ArrayList<>(), SerializerFeature.WriteNullListAsEmpty));
		System.out.println(JSONObject.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty));
		System.out.println(JSONObject.toJSONString(list, SerializerFeature.SortField));
	}
}

