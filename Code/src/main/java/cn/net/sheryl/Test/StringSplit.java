package cn.net.sheryl.Test;

public class StringSplit {
	public static void main(String[] args) {
		String splitString = "2343543523@::@";
		String[] arrSplit = splitString.split("@::@");
		System.out.println(arrSplit[0] + "长度" +arrSplit.length);
	}
}
