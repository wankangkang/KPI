/**
 * FileName:post.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月16日下午8:36:05
 ********************************
 *Modifycation History:
 *date:2018年1月16日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.bean;

/**
 * @author Bill
 *帖子类
 */
public class Post {

	private Member member;// 发帖者信息
	private Long postId;// 帖子ID
	private String postContent;// 帖子内容
	private String postTime;// 发帖时间
	private Long commentSize;

	public Object clone() {
		Post post;
		try {
			post = (Post) super.clone();
		} catch (Exception e) {
			return null;
		}
		return post;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	public Long getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Long commentSize) {
		this.commentSize = commentSize;
	}

	@Override
	public String toString() {
		return "Post [member=" + member + ", postId=" + postId + ", postContent=" + postContent + ", postTime="
				+ postTime + ", commentSize=" + commentSize + "]";
	}
	
}
