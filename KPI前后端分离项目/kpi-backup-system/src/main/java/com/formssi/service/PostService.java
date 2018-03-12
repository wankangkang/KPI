/**
 * FileName:PostService.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月17日下午10:22:55
 ********************************
 *Modifycation History:
 *date:2018年1月17日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.service;

import java.util.List;
import java.util.Map;

import com.formssi.bean.Comment;
import com.formssi.bean.Post;
import com.formssi.bean.ReplyComment;

/**
 * @author Bill
 *
 */
public interface PostService {

	/**
	 * @Title:createPostService
	 * @Description:新建帖子操作
	 * @param:@param postContent  帖子内容
	 * @param:@param memberId  发帖者ID
	 * @param:@return
	 * @return:Post
	 * @throws
	 */
	public Post createPostService(String postContent,Long memberId);
	
	/**
	 * @Title:coment
	 * @Description:评论的数据操作
	 * @param:@param commentMemberId 评论者ID
	 * @param:@param commentContent  评论内容
	 * @param:@param postId          帖子ID
	 * @param:@return
	 * @return:Comment
	 * @throws
	 */
	public Comment coment(Long commentMemberId,String commentContent,Long postId);
	
	/**
	 * @Title:createReply
	 * @Description:回复的数据操作
	 * @param:@param commentId  评论ID
	 * @param:@param replyContent  回复内容
	 * @param:@param replyMemberId  回复者ID
	 * @param:@param replyedMemberId  被回复者ID
	 * @param:@return
	 * @return:ReplyComment
	 * @throws
	 */
	public ReplyComment createReply(Long commentId,String replyContent,Long replyMemberId,Long replyedMemberId);
	
	/**
	 * @Title:getPost
	 * @Description:获取指定条数的帖子
	 * @param:@param start  起始位置
	 * @param:@param end    结束位置
	 * @param:@return
	 * @return:List<Post>
	 * @throws
	 */
	public List<Post> getPost(Long start,Long end);
	
	/**
	 * @Title:getSpecificPersonPost
	 * @Description:根据具体成员获取指定条数的帖子
	 * @param:@param start  起始位置
	 * @param:@param end    结束位置
	 * @param:@param department  部门名称
	 * @param:@return
	 * @return:List<Post>
	 * @throws
	 */
	public List<Post> getSpecificPersonPost(Long start,Long end,String department);
	
	/**
	 * @Title:getSpecificPostInfo
	 * @Description:获取具体帖子的信息
	 * @param:@param start  起始位置
	 * @param:@param end    结束位置
	 * @param:@param postId 帖子ID
	 * @param:@return
	 * @return:Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> getSpecificPostInfo(Long start,Long end,Long postId);
}
