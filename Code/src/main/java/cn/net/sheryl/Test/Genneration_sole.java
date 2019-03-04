/**  
 * @Title: Genneration_sole.java
 * @Package cn.net.sheryl.Test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Sheryl
 * @date 2017年11月14日 上午9:34:51
 * @version V1.0  
 */
    
package cn.net.sheryl.Test;

/**
 * @ClassName: Genneration_sole
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Sheryl
 * @date 2017年11月14日 上午9:34:51
 */

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import org.apache.hadoop.hbase.util.Sleeper;
/**
 * 处理随机数的工具类
 * @author ysmstoneman
 *
 */
public class Genneration_sole {
 
 /**
  * 
  * <br>功能：<B>获取18位唯一码</B>
  * <br>思路： 用时间戳加上4位随机数生成
  * <OL>
  *   <LI>1、</LI>
  *   <LI>2、</LI>
  *   <LI>3、</LI>
  * </OL>
  * <br>相关性：
  * @return
  */
 public static String getRandomUUID() {
  //1、创建时间戳
  java.util.Date dateNow = new java.util.Date();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
  String dateNowStr = dateFormat.format(dateNow);
  StringBuffer sb = new StringBuffer(dateNowStr);
  //2、创建随机对象
  Random rd = new Random(); 
  
  //3、产生4位随机数
  String n = "";
  int rdGet; //取得随机数
  do {
   rdGet = Math.abs(rd.nextInt()) % 10 + 48; //产生48到57的随机数(0-9的键位值)
   // rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
   char num1 = (char) rdGet;
   String dd = Character.toString(num1);
   n += dd;
  } while (n.length() < 4);// 假如长度小于4
  sb.append(n);
  
  //4、返回唯一码
  return sb.toString();
 }
// public static void main(String[] args) {
//	 for (int i = 0; i < 5; i++) {
//		 String s = UUID.randomUUID().toString();
//		 s =  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
//		 System.out.println(s.substring(0, 12));
//		 }
//}
 
 public static String getOrderIdByUUId() {  
     int machineId = 1;//最大支持1-9个集群机器部署  
     int hashCodeV = UUID.randomUUID().toString().hashCode();  
     if(hashCodeV < 0) {//有可能是负数  
         hashCodeV = - hashCodeV;  
     }  
     // 0 代表前面补充0       
     // 4 代表长度为4       
     // d 代表参数为正数型  
     return machineId + String.format("%9d", hashCodeV);  
 }  
 
 
 public static void main(String[] args) {  
//	 Long[] arrLong = new Long[100];
//	 for (int i = 0; i < 100; i++) {
//		 System.out.println(getOrderIdByUUId());
//		 Long number = Long.parseLong(getOrderIdByUUId());
//		 System.out.println(number);
//		 arrLong[i] = number;
//		 for (int j = i; j > 0; j--) {
//			if (arrLong[i] == arrLong[j-1]) {
//				System.out.println("----------------------------------------------");
//			}
//		}
//	}
       
	 
	 
//	 String[] arrString = new String[1000];
//	 for (int i = 0; i < 1000; i++) {
//		 arrString[i] = getOrderIdByUUId();
//		 for (int j = i; j > 0; j--) {
//			if (arrString[i] == arrString[j-1]) {
//				System.out.println("----------------------------------------------");
//			}
//		}
//		 System.out.println(arrString[i] +"长度为"+ arrString[i].length());
//	}
	 for (int i = 0; i < 10000; i++) {
		 String number = getRandomUUID();
		 System.out.println(number + "长度：" + number.length() + "   ");
	}
	
 }  
}