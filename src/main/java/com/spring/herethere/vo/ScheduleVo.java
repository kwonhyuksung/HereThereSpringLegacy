package com.spring.herethere.vo;

import java.util.Date;

public class ScheduleVo {
	private int postSeq;
	private int postTypeCode;
	private int boardCode;
	private String userId;
	private String postTitle;
	private String postContent;
	private int hitCount;
	private Date logDate;
	private Date renewalDate;
	private int scheduleSeq;
	private Date startDate;
	private Date endDate;
	private int term;
	private String thema;
	private String originName;
	private String saveName;
	private String savePath;
	private int dibsCount;
	private int recommendCount;

	public ScheduleVo() {
	}

	public ScheduleVo(int postSeq, int postTypeCode, int boardCode, String userId, String postTitle, String postContent, int hitCount, Date logDate,
			Date renewalDate, int scheduleSeq, Date startDate, Date endDate, int term, String thema, String originName, String saveName,
			String savePath, int dibsCount, int recommendCount) {
		this.postSeq = postSeq;
		this.postTypeCode = postTypeCode;
		this.boardCode = boardCode;
		this.userId = userId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.hitCount = hitCount;
		this.logDate = logDate;
		this.renewalDate = renewalDate;
		this.scheduleSeq = scheduleSeq;
		this.startDate = startDate;
		this.endDate = endDate;
		this.term = term;
		this.thema = thema;
		this.originName = originName;
		this.saveName = saveName;
		this.savePath = savePath;
		this.dibsCount = dibsCount;
		this.recommendCount = recommendCount;
	}

	public int getPostSeq() {
		return postSeq;
	}

	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}

	public int getPostTypeCode() {
		return postTypeCode;
	}

	public void setPostTypeCode(int postTypeCode) {
		this.postTypeCode = postTypeCode;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public int getScheduleSeq() {
		return scheduleSeq;
	}

	public void setScheduleSeq(int scheduleSeq) {
		this.scheduleSeq = scheduleSeq;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getDibsCount() {
		return dibsCount;
	}

	public void setDibsCount(int dibsCount) {
		this.dibsCount = dibsCount;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	@Override
	public String toString() {
		return "ScheduleVo [postSeq=" + postSeq + ", postTypeCode=" + postTypeCode + ", boardCode=" + boardCode + ", userId=" + userId
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", hitCount=" + hitCount + ", logDate=" + logDate + ", renewalDate="
				+ renewalDate + ", scheduleSeq=" + scheduleSeq + ", startDate=" + startDate + ", endDate=" + endDate + ", term=" + term + ", thema="
				+ thema + ", originName=" + originName + ", saveName=" + saveName + ", savePath=" + savePath + ", dibsCount=" + dibsCount
				+ ", recommendCount=" + recommendCount + "]";
	}

}
