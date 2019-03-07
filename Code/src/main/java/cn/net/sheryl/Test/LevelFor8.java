package cn.net.sheryl.Test;

public class LevelFor8 {
	//小鸡吃米算法
//	public static int haveRice(int m,int n){  
//	    if(m<0)  
//	        return 0;  
//	    else if(m==0)  
//	        return 1;  
//	    else {  
//	        int result=0;  
//	        for(int i=1;i<=n;i++){  
//	            result+=haveRice(m-i,n);  
//	        }  
//	        return result;  
//	    }  
//	}
	
	//进制装换
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("十进制转换到其它进制：");
        int x = 20 ;
        String str1 = Integer.toHexString(x) ;      ///10进制转换成16进制的字符串
        System.out.println(str1);
        String str2 = Integer.toOctalString(x) ;     ///10进制转换成8进制的字符串
        System.out.println(str2);
        int num = 8 ;
        float num2 = (float) 0.6;
        System.out.println(Float.parseFloat(Integer.toOctalString((int)(num + num2 * 10)))/10);
        
        String str3 = Integer.toBinaryString(x) ;    ///10进制转换成2进制的字符串
        System.out.println(str3);
        String str4 = Integer.toString(123456,7) ;  ///10进制转换成7进制的字符串
        System.out.println(str4);
        ///String str4 = Integer.toString(i,x) ;  ///10进制的数字i转换成x进制的字符串
         
         
         
        System.out.println("其它制转换到十进制：");
        int y1= Integer.valueOf("FFFF",16);     ///16进制转换成10进制
        System.out.println(y1);
        int y2=Integer.valueOf("776",8);        ///8进制转换成10进制
        System.out.println(y2);
        int y3=Integer.valueOf("0101",2);       //2进制转换成10进制
        System.out.println(y3);
        int y4=Integer.valueOf("101",7);        //7进制转换成10进制
        System.out.println(y4);
         
        ///Integer.valueOf("str",x); ///可以为任意进制的字符串str转换成x进制的10进制数
         
        System.out.println("其它的可能用到的函数：");
        //static int parseInt(String s, int radix) //使用第二个参数指定的基数，将字符串参数解析为有符号的整数。
        int n = Integer.parseInt("776", 8) ; ///8进制转换成10进制
        System.out.println(n);
        ///Integer.valueOf()返回一个“integer对象”和Integer.parseInt()返回一个“int值”的区别在于，返回值不同
        ///基本常识，其他的非10进制的数的保存，基本都是以字符串的形式
         
         
        ///例子：7进制到8进制的转换
        String q = "6523" ; ///7进制的字符串
        String b = Integer.toString(Integer.valueOf(q,7),8) ;///这样7进制就变成8进制了
    }
	
//	public static void main(String[] args) {
//		long lNum = 10;
//		int iLevel = (int) (Float.parseFloat("0.1") * 10);
//		System.out.println(iLevel);
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			
//		}
//		int iTotal = (int)(lNum + iLevel);
//		int num1;
//		int num2;
//		
//		
//		
//		if (iTotal) {
//			
//		}
//	}
}
