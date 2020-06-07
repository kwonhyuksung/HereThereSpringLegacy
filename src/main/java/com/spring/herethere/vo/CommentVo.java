package com.spring.herethere.vo;

import java.util.Date;

public class CommentVo {
	private int postSeq;
	private int postTypeCode;
	private int boardCode;
	private String userId;
	private String postTitle;
	private String postContent;
	private int hitCount;
	private Date logDate;
	private Date renewalDate;
	private int commentSeq;
	private int groupNo;
	private int groupOrder;
	private int depth;
	private int parentSeq;
	private int replyCount;

	public CommentVo() {
	}

	public CommentVo(int postSeq, int postTypeCode, int boardCode, String userId, String postTitle, String postContent, int hitCount, Date logDate,
			Date renewalDate, int commentSeq, int groupNo, int groupOrder, int depth, int parentSeq, int replyCount) {
		super();
		this.postSeq = postSeq;
		this.postTypeCode = postTypeCode;
		this.boardCode = boardCode;
		this.userId = userId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.hitCount = hitCount;
		this.logDate = logDate;
		this.renewalDate = renewalDate;
		this.commentSeq = commentSeq;
		this.groupNo = groupNo;
		this.groupOrder = groupOrder;
		this.depth = depth;
		this.parentSeq = parentSeq;
		this.replyCount = replyCount;
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

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	@Override
	public String toString() {
		return "CommentVo [postSeq=" + postSeq + ", postTypeCode=" + postTypeCode + ", boardCode=" + boardCode + ", userId=" + userId + ", postTitle="
				+ postTitle + ", postContent=" + postContent + ", hitCount=" + hitCount + ", logDate=" + logDate + ", renewalDate=" + renewalDate
				+ ", commentSeq=" + commentSeq + ", groupNo=" + groupNo + ", groupOrder=" + groupOrder + ", depth=" + depth + ", parentSeq="
				+ parentSeq + ", replyCount=" + replyCount + "]";
	}

}
