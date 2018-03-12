/**
 * FileName:ReplyComment.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月18日下午9:45:07
 ********************************
 *Modifycation History:
 *date:2018年1月18日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

/**
 * @author Bill
 *
 */
public class ReplyComment {

	private Long replyCommentId;// 回复ID
	private Long parentCommentId;// 回复评论的ID
	private String replyContent;// 回复内容
	private String replyTime;// 回复时间
	private Member replyedMember;// 被回复者信息
	private Member replyMember;// 回复者信息

	public Long getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(Long replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Member getReplyedMember() {
		return replyedMember;
	}

	public void setReplyedMember(Member replyedMember) {
		this.replyedMember = replyedMember;
	}

	public Member getReplyMember() {
		return replyMember;
	}

	public void setReplyMember(Member replyMember) {
		this.replyMember = replyMember;
	}

	@Override
	public String toString() {
		return "ReplyComment [replyCommentId=" + replyCommentId + ", parentCommentId=" + parentCommentId
				+ ", replyContent=" + replyContent + ", replyTime=" + replyTime + ", replyedMember=" + replyedMember
				+ ", replyMember=" + replyMember + "]";
	}

}
