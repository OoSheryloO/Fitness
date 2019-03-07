package cn.net.sheryl.Test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonDemo {
		public static void main(String[] args) {
			List<pojo> pojos = new ArrayList<pojo>();
			pojo pj= new pojo();
			pj.setA((long)123);
			pj.setB("");
			pj.setC((int)1);
			pj.setD(" ");
			pj.setE("kj");
			pj.setF("987654321");
			pj.setH(0);
			pojos.add(pj);
			pojo pj1= new pojo();
			pj1.setA((long)123);
			pj1.setB("");
			pj1.setC((int)1);
			pj1.setD(" ");
			pj1.setE("kj");
			pj1.setF("987654321");
			pj1.setH(0);
			pojos.add(pj1);
			
	String jsonString=JSONObject.toJSONString(pj, SerializerFeature.WriteMapNullValue);
//			System.out.println(jsonString);
//			System.out.println(JSONObject.toJSONString(pj)+"dfdsfdsfs");
//			System.out.println(JSONObject.toJSON(pj)+"jsonobject  tojson");
//			System.out.println(JSON.toJSON(pj)+"json  tojson");
			
			Object object = new Object();
			object = JSONObject.toJSON(pj);
	
			String NULL = JSONObject.toJSONString((Object)pj, SerializerFeature.NotWriteDefaultValue);
//			System.out.println("hfhf"+NULL);
			
			String a = ToJsonString(pj);
//			System.out.println(a);
			
			String ac = JSON.toJSONString(pj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
//			System.out.println(JSONObject.toJSON(ac));
			
			System.out.println(JSONObject.toJSONString(pojos, SerializerFeature.WriteNullListAsEmpty));
		}
//	}
//
//	}
//
//	
		
		
	public static String ToJsonString(pojo obj) {
			return JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat,
					SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
					SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
					SerializerFeature.WriteNullNumberAsZero);
		}
}
