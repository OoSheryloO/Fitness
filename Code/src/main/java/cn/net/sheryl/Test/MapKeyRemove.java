//package cn.net.sheryl.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.huban.pojo.User;
//
//public class MapKeyRemove {
//	public static void main(String[] args) {
//		
//		String s = "\\{\"data\": \\{\"exist\": false,\"user\": \\{\"userReadtime\": \"0\",\"userBirthday\": "
//				+ "\"2000-01-01 00:00:00\"\\},\"wh_code\": 60000,\"token\":"
//				+ " \"4C39FD90B196AB534A377160129986B7\"\\},\"message\": \"登陆成功\",\"wh_code\": 60000\\}";
//		String jsonText = "{\"book\":{\"author\":{\"money\":500,\"name\":\"南派三叔\"},\"name\":\"网络文学\"},\"name\":\"玄玉\"}"; 
//		JSONObject json = JSONObject.parseObject(s);
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		Map<String, Object> map2 = new HashMap<String, Object>();
////		User user = new User();
////		user.setUserPassword("dasdfs");
////		map2.put("user", user);
////		map2.put("wh_code", 60000);
////		map1.put("data", map2);
//		map1.put("message", "登陆成功");
//		map1.put("wh_code", 60000);
//		System.out.println(JSON.toJSON(jsonText));
//	}
////	public static void main(String[] args) {
////		String str2="a}b}c{a{b{c";  
////		String strs[]=str2.split("\\{");  
////		for(String str:strs){  
////		     System.out.println(str);  
////		} 
////	}
//	
//}
