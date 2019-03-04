package com.huban.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class MD5_2 {
 
    private static final String[] GOAL = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
     
    private String algorithm;   //加密方式
    private String salt;    //盐值
     
    public MD5_2(){
         
    }
     
    public MD5_2(String algorithm, String salt) {
        this.algorithm = algorithm;
        this.salt = salt;
    }
     
    public String getMD5(String object){
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            //digest在底层也调用了update方法
            result = byteArrayToString(digest.digest(addSalt(object).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toUpperCase();
    }
 
    /**
     * 在加密对象后加盐
     * @param object
     * @return
     */
    private String addSalt(String object){
        if(object == null){
            object = "";
        }
        if(salt == null || "".equals(salt)){
            return object;
        } else {
            return object + "{"+ salt.toString() +"}";
        }
    }
     
    private String byteArrayToString(byte[] ss){
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < ss.length; i++){
            result.append(byteToString(ss[i]));
        }
        return result.toString();
    }
     
    private String byteToString(byte ss){
        int temp;
        temp = ss < 0 ? ss + 256 : ss;
        return GOAL[temp / 16] + GOAL[temp % 16];//自己实现转化
        /*
            用现有的方法实现转化
            StringBuffer s = new StringBuffer();
            if(temp < 16)s.append("0");
            s.append(Integer.toHexString(temp));
            return s.toString();   
         */
    }
     
    public static void main(String[] args) {
        MD5_2 md = new MD5_2("MD5", "hello");
        System.out.println(md.getMD5("hello"));
    }
}