package com.huban.Utils;

import java.util.HashMap;
import java.util.Map;

public class LotteryRewardPercentTest {
	//打赏获得抽奖机会算法  打赏次数限制
		public static final double FirstLevelLimit = 5;
		public static final double SecondLevelLimit = 9;
		public static final double ThirdlyLevelLimit = 12;
		//%
		public static final double FirstLevelPercent = 0.05;//5%
		public static final double SecondLevelPercent = 0.11;//11%
		public static final double ThirdlyLevelPercent = 0.2;//20%
	
	public static boolean ArithmeticForReWard(int count) {
	Map<String, Object> result = new HashMap<String,Object>();
	double num =  Math.random();
	boolean flag =  false;double coun = count;
	if (	Double.compare(coun,FirstLevelLimit) <= 0) {//0~5 0~5% 总:100  概率区间0/100  5/100  
		if (	Double.compare(num, count/(FirstLevelLimit/FirstLevelPercent)) <= 0 ) {
			flag = true;
		}
	}else if (Double.compare(coun,SecondLevelLimit) <= 0) {//6~9  7%~11% 总:81   概率区间6/81 9/81   
		if (	Double.compare(num, count/(SecondLevelLimit/SecondLevelPercent)) <= 0) {
			flag = true;
		}
	}else if (Double.compare(coun,ThirdlyLevelLimit) <= 0) {//10~12  16%~20% 总:60  概率区间9/60 12/60 
		if (	Double.compare(num, count/(ThirdlyLevelLimit/ThirdlyLevelPercent)) <= 0) {
			flag = true;
		}
	}else {
		if (Double.compare((double)20,(double)num*100) >= 0) {
			flag = true;
		}
	}
	return flag;
}
	public static void main(String[] args) {
		int a = 10000;
		for (int k = 0; k < 100; k++) {
			for (int i = 13; i < 14; i++) {
				int num = 0;
				for (int j = 0; j < a; j++) {
					boolean flag = ArithmeticForReWard(i);
					if (flag) {
						num = num + 1;
					}
				}System.out.println(num+";总共:"+a+"第"+(i+1)+"次输出");
			}
		}
		
		
	}
}
