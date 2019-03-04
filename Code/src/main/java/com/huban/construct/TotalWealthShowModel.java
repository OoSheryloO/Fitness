package com.huban.construct;

import java.math.BigDecimal;

public class TotalWealthShowModel {
	private int ReadSaveSingleNumber;//存储单
	
	private int ReadSaveDepositNumber;//存折
	
	private int ReadSaveCheckNumber;//支票
	
	private BigDecimal ReadSaveWordsNumber;//读 数
	
	private BigDecimal IntegralNumber;//积分
	
	private BigDecimal GoldNumber;//金币
	
	private int CouponNumber;//卡券
	
	private BigDecimal Salary;//工资
	
	private int OtherAward;//其他奖励

	public int getReadSaveSingleNumber() {
		return ReadSaveSingleNumber;
	}

	public void setReadSaveSingleNumber(int readSaveSingleNumber) {
		ReadSaveSingleNumber = readSaveSingleNumber;
	}

	public int getReadSaveDepositNumber() {
		return ReadSaveDepositNumber;
	}

	public void setReadSaveDepositNumber(int readSaveDepositNumber) {
		ReadSaveDepositNumber = readSaveDepositNumber;
	}

	public int getReadSaveCheckNumber() {
		return ReadSaveCheckNumber;
	}

	public void setReadSaveCheckNumber(int readSaveCheckNumber) {
		ReadSaveCheckNumber = readSaveCheckNumber;
	}

	public BigDecimal getReadSaveWordsNumber() {
		return ReadSaveWordsNumber;
	}

	public void setReadSaveWordsNumber(BigDecimal readSaveWordsNumber) {
		ReadSaveWordsNumber = readSaveWordsNumber;
	}

	public BigDecimal getIntegralNumber() {
		return IntegralNumber;
	}

	public void setIntegralNumber(BigDecimal integralNumber) {
		IntegralNumber = integralNumber;
	}

	public BigDecimal getGoldNumber() {
		return GoldNumber;
	}

	public void setGoldNumber(BigDecimal goldNumber) {
		GoldNumber = goldNumber;
	}

	public int getCouponNumber() {
		return CouponNumber;
	}

	public void setCouponNumber(int couponNumber) {
		CouponNumber = couponNumber;
	}

	public BigDecimal getSalary() {
		return Salary;
	}

	public void setSalary(BigDecimal salary) {
		Salary = salary;
	}

	public int getOtherAward() {
		return OtherAward;
	}

	public void setOtherAward(int otherAward) {
		OtherAward = otherAward;
	}
	
	
}
