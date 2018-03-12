/**
 * FileName:PostController.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月17日下午8:47:58
 ********************************
 *Modifycation History:
 *date:2018年1月17日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formssi.exception.Result;
import com.formssi.service.PostService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Bill
 *
 */
@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	//前端需传值：发帖者ID,O
	@ApiOperation("创建帖子")
	@PostMapping("/createPost")
	public Result createPost(@ApiParam(name="postContent",value="帖子内容",required=true)@PathParam(value="postContent") String postContent,
			@ApiParam(name="memberId",value="发帖者ID",required=true)@PathParam(value="memberId") Long memberId
			/*@ApiParam(name="postContent",value="帖子内容",required=true)@RequestParam(value="postContent") String postContent,
			@ApiParam(name="memberId",value="发帖者ID",required=true)@RequestParam(value="memberId") Long memberId*/) {
		return Result.returnObj("post", postService.createPostService(postContent, memberId));
	}
	
	@ApiOperation("获取前几个帖子")
	@GetMapping("/getNewestPost")
	public Result getMostCommentPost(@ApiParam(name="start",value="起始位置",required=true)@RequestParam(value="start") Long start,
			@ApiParam(name="step",value="步长",required=true)@RequestParam(value="step") Long step) {
		return Result.returnObj("postList", postService.getPost(start, start+step-1));
	}
	
	@ApiOperation("获取指定项目组的前几个帖子")
	@GetMapping("/getSpecificDepartmentPost")
	public Result getSpecificDepartmentPost(@ApiParam(name="start",value="起始位置",required=true)@RequestParam(value="start") Long start,
			@ApiParam(name="step",value="步长",required=true)@RequestParam(value="step") Long step,
			@ApiParam(name="department",value="部门名称",required=true)@RequestParam(value="department") String department) {
		return Result.returnObj("postList", postService.getSpecificPersonPost(start, start+step-1, department));
	}
	
	@ApiOperation("评论指定的帖子")
	@PostMapping("/comment")
	public Result comment(@ApiParam(name="commentMemberId",value="评论者ID",required=true)@PathParam(value="commentMemberId") Long commentMemberId,
			@ApiParam(name="commentContent",value="评论内容",required=true)@PathParam(value="commentContent") String commentContent,
			@ApiParam(name="postId",value="帖子ID",required=true)@PathParam(value="postId") Long postId
			/*@ApiParam(name="commentMemberId",value="评论者ID",required=true)@RequestParam(value="commentMemberId") Long commentMemberId,
			@ApiParam(name="commentContent",value="评论内容",required=true)@RequestParam(value="commentContent") String commentContent,
			@ApiParam(name="postId",value="帖子ID",required=true)@RequestParam(value="postId") Long postId*/) {
		return Result.returnObj("comment",postService.coment(commentMemberId, commentContent, postId));
	}
	
	@ApiOperation("回复指定帖子的评论或回复")
	@PostMapping("/replyComment")
	public Result replyComment(@ApiParam(name="commentId",value="回复评论的ID",required=true)@PathParam(value="commentId") Long commentId,
			@ApiParam(name="replyMemberId",value="回复者的ID",required=true)@PathParam(value="replyMemberId") Long replyMemberId,
			@ApiParam(name="replyContent",value="回复内容",required=true)@PathParam(value="replyContent") String replyContent,
			@ApiParam(name="replyedMemberId",value="被回复者的ID",required=true)@PathParam(value="replyedMemberId") Long replyedMemberId
			/*@ApiParam(name="commentId",value="回复评论的ID",required=true)@RequestParam(value="commentId") Long commentId,
			@ApiParam(name="replyMemberId",value="回复者的ID",required=true)@RequestParam(value="replyMemberId") Long replyMemberId,
			@ApiParam(name="replyContent",value="回复内容",required=true)@RequestParam(value="replyContent") String replyContent,
			@ApiParam(name="replyedMemberId",value="被回复者的ID",required=true)@RequestParam(value="replyedMemberId") Long replyedMemberId*/) {
		return Result.returnObj("reply",postService.createReply(commentId, replyContent, replyMemberId, replyedMemberId));
	}
	
	@ApiOperation("获取指定帖子的信息")
	@GetMapping("/getSpecificPostInfo")
	public Result getSpecificPostInfo(@ApiParam(name="start",value="评论的起始位置",required=true)@RequestParam(value="start") Long start,
			@ApiParam(name="step",value="评论的步长",required=true)@RequestParam(value="step") Long step,
			@ApiParam(name="postId",value="帖子ID",required=true)@RequestParam(value="postId") Long postId) {
		return Result.returnObj("postInfo", postService.getSpecificPostInfo(start, start+step-1, postId));
	}
}
