package com.huban.publicway;

/**
 * <p>Title: ConstantWay.java</p>
 * <p>Description: 抽取的公共方法</p>
 * <p>Company: huban</p>
 * @author Sheryl
 * @created 2017年10月23日 上午9:35:18
 */
public class ConstantWay {
	/** 拼接字符串 移除 成员
	 * @created 2017年10月23日 上午10:04:27
	 * @param sJoint 拼接串 
	 * @param sSeparator 分隔符 
	 * @param sJointer 移除成员
	 * @return String
	 */
	public static String JointString(String sJoint, String sSeparator, String sJointer) {
		String[] arrJoint = sJoint.split(sSeparator);

		sJoint = "";
		for (int i = 0; i < arrJoint.length; i++) {
			if (!arrJoint[i].equals(sJointer)) {
				sJoint = arrJoint[i] + sSeparator;
			}
		}

		arrJoint = sJoint.split(sSeparator);
		sJoint = "";
		for (int i = 0; i < arrJoint.length - 1; i++) {
			sJoint = arrJoint[i] + sSeparator;
		}
		sJoint += arrJoint[arrJoint.length - 1];

		return sJoint;
	}
}
