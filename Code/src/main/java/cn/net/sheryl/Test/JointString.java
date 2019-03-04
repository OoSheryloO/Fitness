package cn.net.sheryl.Test;

public class JointString {
	public static void main(String[] args) {
//		String sTestString = "安徽省@::@宣城市@::@宣州区";
//		String[] joinsStrings = sTestString.split("@::@");
//		for (int i = 0; i < joinsStrings.length; i++) {
//			System.out.println(joinsStrings[2]);
//			System.out.println(joinsStrings[2].substring(0, 2));
//		}
		String sTestString = "027@::@000000000000001";
		String[] joinsStrings = sTestString.split("@::@");
		System.out.println(joinsStrings[1]);
	}
	
}
