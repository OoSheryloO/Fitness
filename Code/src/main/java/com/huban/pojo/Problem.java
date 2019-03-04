package com.huban.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Problem {
	
	private long id;
	private String pkid;
	private String content;
	private String createTime;
	private String modifyTime;
	private int status;
	private int questiontype;//通用状态()(阅读银行:(1:选择题 2:判断题 3:填空题))
	private int type;//评测系统 1 心里测试  2 阅读素养  3 学科/项目
	@JSONField(serialize=false)
	private String answerA;
	@JSONField(serialize=false)
	private String answerB; 
	@JSONField(serialize=false)
	private String answerC; 
	@JSONField(serialize=false)
	private String answerD; 
	private String answer;
	private String[] Answers;
	private int level;
	private int grade;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime.substring(0, 19);
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime.substring(0, 19);
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(int questiontype) {
		this.questiontype = questiontype;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	/**
	 * @return answers
	 */
	
	public String[] getAnswers() {
		String[] Answerss = new String[4];
		int i = 0;
		if (null != answerA) {
			Answerss[i] = answerA;
			i += 1;
		}
		if (null != answerA) {
			Answerss[i] = answerB;
			i += 1;
		}
		if (null != answerA) {
			Answerss[i] = answerC;
			i += 1;
		}
		if (null != answerA) {
			Answerss[i] = answerD;
			i += 1;
		}
		return Answerss;
	}
	/**
	 * @param answers the answers to set
	 */
	
	public void setAnswers(String[] answers) {
		Answers = answers;
	}
	
	
	
}
