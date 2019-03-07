//package wtb.smUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSON;
//
//import wtb.core.json.LoginType;
//
//
//
//public class ResultUtil {
//	 private static ResultUtil instance;
//
//	    public static ResultUtil sharedInstance() {
//	        if (instance == null) {
//	            instance = new ResultUtil();
//	        }
//
//	        return instance;
//	    }
//	    /**
//	     * 错误码
//	     */
//	    public enum ErrorCode {
//	        Unknown {   //  未知错误
//
//	            public int getCode() {
//	                return 0;
//	            }
//	        },
//
//	        Parameter { //  参数错误
//
//	            public int getCode() {
//	                return 1;
//	            }
//	        },
//
//	        Authorized { // 认证失败
//
//	            public int getCode() {
//	                return 2;
//	            }
//	        };
//
//	        public abstract int getCode();
//	    }
//	    /**
//	     * 其他交互错误返回值处理方法
//	     * @param message 信息
//	     * @return 返回错误描述的 JSON 键值
//	     */
//	    public Map<String, Object> otherError(String message,String code) {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, false);
//	        
//	        Map<String, Object> data = new HashMap<String, Object>();
//	        data.put(BaseUtil.messageKey, message);
//	        result.put(BaseUtil.dataKey, data);
//	        if (code!=null) {
//	        	LoginType loginType=new LoginType();
//	        	loginType.setCode(code);
//	        	result.put(BaseUtil.loginType,  JSON.parse(JSON.toJSONString(loginType)));
//			}
//	        return result;
//	    }
//
//	    /**
//	     * 操作成功返回值处理方法
//	     * @param data 数据
//	     * @return 返回操作成功描述的 JSON 键值
//	     */
//	    public Map<String, Object> handleCorrect(Object data,String code) {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, true);
//	        result.put(BaseUtil.dataKey,  JSON.parse(JSON.toJSONString(data)));
//	        if (code!=null) {
//	        	LoginType loginType=new LoginType();
//	        	loginType.setCode(code);
//	        	result.put(BaseUtil.loginType,  JSON.parse(JSON.toJSONString(loginType)));
//			}
//	        return result;
//	    }
//	    
//	   /**
//	    * 主要用于列表的返回   
//	    * @param data 数据
//	    * @param count 数量
//	    * @param code 是否需要登录
//	    * @return
//	    */
//	    public Map<String, Object> listData(Object data,int count,String code) {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, true);
//	        if (code!=null) {
//	        	LoginType loginType=new LoginType();
//	        	loginType.setCode(code);
//	        	result.put(BaseUtil.loginType,  JSON.parse(JSON.toJSONString(loginType)));
//			}
//	        result.put(BaseUtil.countKey,  count);
//	        result.put(BaseUtil.dataKey,  JSON.parse(JSON.toJSONString(data)));
//	        return result;
//	    }
//	    
//	    
//		   /**
//		    * 主要用于单个数据，详情的返回
//		    * @param data 数据
//		    * @param count 数量
//		    * @param code 是否需要登录
//		    * @return
//		    */
//		    public Map<String, Object> simpleData(Object data,String code) {
//		        Map<String, Object> result = new HashMap<String, Object>();
//		        result.put(BaseUtil.statusKey, true);
//		        if (code!=null) {
//		        	LoginType loginType=new LoginType();
//		        	loginType.setCode(code);
//		        	result.put(BaseUtil.loginType,  JSON.parse(JSON.toJSONString(loginType)));
//				}
//		        result.put(BaseUtil.dataKey,  JSON.parse(JSON.toJSONString(data)));
//		        return result;
//		    }
//		    
//		    
//	    /**
//	     * 提交的参数不能转换为 JSONObject 时报错
//	     *
//	     * @return 返回错误描述的 JSON 键值
//	     */
//	    public Map<String, Object> unknownError() {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, false);
//
//	        Map<String, Object> data = new HashMap<String, Object>();
//	        data.put(BaseUtil.codeKey, ErrorCode.Unknown.getCode());
//	        data.put(BaseUtil.messageKey, "系统发生未知错误");
//
//	        result.put(BaseUtil.dataKey, data);
//
//	        return result;
//	    }
//	    /**
//	     * 没有参数错误返回值处理方法
//	     *
//	     * @return 返回错误描述的 JSON 键值
//	     */
//	    public Map<String, Object> parameterError() {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, false);
//
//	        Map<String, Object> data = new HashMap<String, Object>();
//	        data.put(BaseUtil.codeKey, ErrorCode.Parameter.getCode());
//	        data.put(BaseUtil.messageKey, "请求参数不能为空");
//
//
//	        result.put(BaseUtil.dataKey, data);
//
//	        return result;
//	    }
//	    
//	    
//	    /**
//	     * 用户未登入或者认证失败时报错
//	     *
//	     * @return 返回错误描述的 JSON 键值
//	     */
//	    public Map<String, Object> authorizedError() {
//	        Map<String, Object> result = new HashMap<String, Object>();
//	        result.put(BaseUtil.statusKey, false);
//
//	        Map<String, Object> data = new HashMap<String, Object>();
//	        data.put(BaseUtil.codeKey, ErrorCode.Authorized.getCode());
//	        data.put(BaseUtil.messageKey, "用户未能通过认证");
//
//	        result.put(BaseUtil.dataKey, data);
//
//	        return result;
//	    }
//}
