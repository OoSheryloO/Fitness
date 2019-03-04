package com.huban.construct;

public class ShowTaskModel {
	private long totalNumber;
	
	private long todayNumber;
	
	private int executePlan;
	
	private int executeJournal;

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public long getTodayNumber() {
		return todayNumber;
	}

	public void setTodayNumber(long todayNumber) {
		this.todayNumber = todayNumber;
	}

	public boolean getExecutePlan() {
		return executePlan > 0 ? true : false;
	}

	public void setExecutePlan(int executePlan) {
		this.executePlan = executePlan;
	}

	public boolean getExecuteJournal() {
		return executeJournal > 0 ? true : false;
	}

	public void setExecuteJournal(int executeJournal) {
		this.executeJournal = executeJournal;
	}
	
}
