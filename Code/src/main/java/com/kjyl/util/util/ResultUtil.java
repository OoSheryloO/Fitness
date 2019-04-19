//package com.kjyl.util.util;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.alibaba.fastjson.JSON;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by MeetLucky on 16/5/23.
// */
//public class ResultUtil {
//    private static ResultUtil instance;
//
//    public static ResultUtil sharedInstance() {
//        if (instance == null) {
//            instance = new ResultUtil();
//        }
//        return instance;
//    }
//    
//    public Map<String, Object> TrueData(Object data, String message, int code) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (null != data ) {
////			result.put(BaseUtil.dataKey, JSON.parse(JSON.toJSONString(data)));
//			result.put(BaseUtil.dataKey, JSON.toJSON(data));
//		}
//		result.put(BaseUtil.messageKey, message);
//		result.put(BaseUtil.newRetureKey, code);
//		return result;
//	}
//	
//	public Map<String, Object> FalseData(String message, int code) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put(BaseUtil.messageKey, message);
//		result.put(BaseUtil.newRetureKey, code);
//		return result;
//	}
//    
//    /**
//     * 其他交互错误返回值处理方法
//     *
//     * @return 返回错误描述的 JSON 键值
//     */
//    public Map<String, Object> otherError(BaseUtil.ErrorMessageType ErrorType, HttpServletRequest request) {
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        result.put(BaseUtil.statusKey, false);
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put(BaseUtil.codeKey, ErrorType.getCode());
//        data.put(BaseUtil.messageKey, ErrorDetail.ErrorMessage(ErrorType));
//        result.put(BaseUtil.dataKey, data);
//        return result;
//    }
//    
//    public Map<String, Object> otherErrorCusomer(String Error, HttpServletRequest request) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, false);
//        result.put(BaseUtil.codeKey, BaseUtil.ErrorMessageType.OtherError.getCode());//固定值其他错误
//        result.put(BaseUtil.messageKey, Error);//自定义错误信息
//        return result;
//    }
//    /**
//     * 操作成功返回值处理方法
//     *
//     * @return 返回操作成功描述的 JSON 键值
//     */
//    public Map<String, Object> handleCorrect(Object message) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, true);
//        result.put(BaseUtil.dataKey, message);
//        return result;
//    }
//
//    /**
//     * 错误码
//     */
//    public enum ErrorCode {
//        Unknown {   //  未知错误
//
//            public int getCode() {
//                return 0;
//            }
//        },
//
//        Parameter { //  参数错误
//
//            public int getCode() {
//                return 1;
//            }
//        },
//
//        Authorized { // 认证失败
//
//            public int getCode() {
//                return 2;
//            }
//        };
//
//        public abstract int getCode();
//    }
//
//    /**
//     * 提交的参数不能转换为 JSONObject 时报错
//     *
//     * @return 返回错误描述的 JSON 键值
//     */
//    public Map<String, Object> unknownError() {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, false);
//
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put(BaseUtil.codeKey, ErrorCode.Unknown.getCode());
//        data.put(BaseUtil.messageKey, "系统发生未知错误");
//
//        result.put(BaseUtil.dataKey, data);
//
//        return result;
//    }
//
//    /**
//     * 没有参数错误返回值处理方法
//     *
//     * @return 返回错误描述的 JSON 键值
//     */
//    public Map<String, Object> parameterError() {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, false);
//
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put(BaseUtil.codeKey, ErrorCode.Parameter.getCode());
//        data.put(BaseUtil.messageKey, "请求参数不能为空");
//
//
//        result.put(BaseUtil.dataKey, data);
//
//        return result;
//    }
//
//    /**
//     * 用户未登入或者认证失败时报错
//     *
//     * @return 返回错误描述的 JSON 键值
//     */
//    public Map<String, Object> authorizedError() {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, false);
//
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put(BaseUtil.codeKey, ErrorCode.Authorized.getCode());
//        data.put(BaseUtil.messageKey, "用户未能通过认证");
//
//        result.put(BaseUtil.dataKey, data);
//
//        return result;
//    }
//
//    /**
//     * 其他交互错误返回值处理方法
//     *
//     * @return 返回错误描述的 JSON 键值
//     */
//    public Map<String, Object> otherError(int code, String message) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, false);
//
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put(BaseUtil.codeKey, code);
//        data.put(BaseUtil.messageKey, message);
//
//        result.put(BaseUtil.dataKey, data);
//
//        return result;
//    }
//
//    /**
//     * 操作成功返回值处理方法
//     *
//     * @return 返回操作成功描述的 JSON 键值
//     */
//    public Map<String, Object> handleCorrect(String message) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(BaseUtil.statusKey, true);
//        result.put(BaseUtil.dataKey, message);
//
//        return result;
//    }
//    
//}
