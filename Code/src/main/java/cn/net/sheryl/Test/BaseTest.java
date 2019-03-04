package cn.net.sheryl.Test;

import java.math.BigDecimal;

public class BaseTest {
	public static void main(String[] args) {
//		short s1 = 1;s1 += 1;
		String sAddress = "好地方 返回 豆腐花 烦死 是否会开始".replaceAll(" +","");
		System.out.println(sAddress);
		
		BigDecimal abc = new BigDecimal(0.000);
		long num = 100;
		System.out.println(abc.compareTo(new BigDecimal(num)));
		
		String sql = "abcdef/n/t/tabc";
		System.out.println(sql);
	}
}
