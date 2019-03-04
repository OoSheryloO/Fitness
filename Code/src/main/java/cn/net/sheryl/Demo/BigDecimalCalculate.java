package cn.net.sheryl.Demo;

import java.math.BigDecimal;


public class BigDecimalCalculate {
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(Long.parseLong("1000") * 10000).setScale(4)
				.divide(new BigDecimal(100))
				.multiply(new BigDecimal(10));
		bigDecimal = new BigDecimal(0.000001);
		double number = 32;
		double num = 10000;
		System.out.println("@::@".length());
		System.out.println(new BigDecimal(number).divide(new BigDecimal(10000)).setScale(4));
//		System.out.println(bigDecimal.setScale(4));
	}
	
	
}
