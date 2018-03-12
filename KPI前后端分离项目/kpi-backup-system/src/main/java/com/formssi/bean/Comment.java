/**
 * FileName:ReplyComment.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月16日下午10:22:35
 ********************************
 *Modifycation History:
 *date:2018年1月16日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

import java.util.List;

/**
 * @author Bill
 *
 */
public class Comment {

	private Long postId;//评论的帖子ID
	private Long commentId;// 评论的ID
	private Member commentMember;// 评论者信息
	private String commentContent;// 评论内容
	private String commentTime;// 评论时间
	private List<ReplyComment> replyComment;// 回复评论列表

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Member getCommentMember() {
		return commentMember;
	}

	public void setCommentMember(Member commentMember) {
		this.commentMember = commentMember;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public List<ReplyComment> getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(List<ReplyComment> replyComment) {
		this.replyComment = replyComment;
	}

	@Override
	public String toString() {
		return "Comment [postId=" + postId + ", commentId=" + commentId + ", commentMember=" + commentMember
				+ ", commentContent=" + commentContent + ", commentTime=" + commentTime + ", replyComment="
				+ replyComment + "]";
	}

}
