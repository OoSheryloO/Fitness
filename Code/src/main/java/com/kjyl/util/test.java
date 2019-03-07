package com.kjyl.util;

public class test {
	public static void main(String[] args) {
		 String s1 = "已经习惯了回车和换行一次搞定\n，敲一个回车键，即是回";
		 System.out.println(s1);
		 s1 = "已经习惯了回车和换行一次搞定\r，敲一个回车键，即是回";
		 System.out.println(s1);
		 s1 = "已经习惯了回车和换行一次搞定\r\n，敲一个回车键，即是回";
		 System.out.println(s1);
	}
}
