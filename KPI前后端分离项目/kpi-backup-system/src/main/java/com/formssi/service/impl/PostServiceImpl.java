/**
 * FileName:PostServiceImpl.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月17日下午10:24:04
 ********************************
 *Modifycation History:
 *date:2018年1月17日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formssi.bean.Comment;
import com.formssi.bean.Member;
import com.formssi.bean.Post;
import com.formssi.bean.ReplyComment;
import com.formssi.dao.MemberDao;
import com.formssi.redis.RedisClient;
import com.formssi.service.PostService;
import com.formssi.util.DateUtil;
import com.formssi.util.JsonUtil;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

/**
 * @author Bill
 *
 */
@Service
public class PostServiceImpl implements PostService {

	//帖子评论回复的hash的key
	private static final String POST_COMMENT_KEY = "postComment";
	//帖子评论回复的自增长key,作为hash的field
	private static final String POST_COMMENT_ID_KEY = "postCommentIdKey";
	//系统时间序列的key
	private static final String SORT_BY_SYSTEM_TIME_KEY = "sortBySystemTimeKey";
	//系统时间序列排序使用的权重自增key
	private static final String SORT_BY_SYSTEM_TIME_SCORE = "sortBySystemTimeScore";
	//个人时间key的前缀
	private static final String SORT_BY_PERSONAL_TIME_KEY_PREFIX = "sortByPersonalTimeKey_";
	//个人时间权重
	private static final String SORT_BY_PERSONAL_TIME_SCORE = "sortByPersonalTimeScore";
	//帖子前缀
	private static final String POST_KEY_PREFIX = "post_";
	//评论前缀
	private static final String COMMENT_KEY_PREFIX = "comment_";
	@Autowired
	RedisClient redisClient;
	@Autowired
	MemberDao memberDao;
	@Override
	public Post createPostService(String postContent,Long memberId) {
		Post post = new Post();
		Member member = memberDao.getMember(memberId);
		post.setMember(member);
		post.setPostContent(postContent);
		post.setPostTime(DateUtil.formatDateTime(new Date()));
		//将值放入相应的分类中，使用redis事物处理
		redisClient.operate(jedis -> {
			Pipeline pl = jedis.pipelined();
			pl.incr(POST_COMMENT_ID_KEY);
			pl.incr(SORT_BY_SYSTEM_TIME_SCORE);
			pl.incr(SORT_BY_PERSONAL_TIME_SCORE);
			List<Object> list = pl.syncAndReturnAll();
			post.setPostId((Long)list.get(0));
			//开启事物
			Transaction multi = jedis.multi();
			//写入数据
			//放入系统时间序列，以用来根据初始化时的排序
			multi.zadd(SORT_BY_SYSTEM_TIME_KEY, (Long)list.get(1), post.getPostId()+"");
			//放入个人时间 序列
			multi.zadd(SORT_BY_PERSONAL_TIME_KEY_PREFIX+member.getDepartment(), (Long)list.get(2),post.getPostId()+"");
			//将帖子放入帖子的hash对象中
			multi.hset(POST_COMMENT_KEY, post.getPostId()+"", JsonUtil.objToStr(post));
			//提交事物
			multi.exec();
			return null;
		});
		return post;
	}

	//评论
	@Override
	public Comment coment(Long commentMemberId,String commentContent,Long postId) {
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		comment.setCommentMember(memberDao.getMember(commentMemberId));
		comment.setCommentTime(DateUtil.formatDateTime(new Date()));
		comment.setPostId(postId);
		redisClient.operate(jedis -> {
			comment.setCommentId(jedis.incr(POST_COMMENT_ID_KEY));
			//将评论推入指定帖子的list中
			jedis.lpush(POST_KEY_PREFIX+postId, JsonUtil.objToStr(comment));
			return null;
		});
		return comment;
	}
	
	//回复功能
	@Override
	public ReplyComment createReply(Long commentId,String replyContent,Long replyMemberId,Long replyedMemberId) {
		ReplyComment reply = new ReplyComment();
		reply.setParentCommentId(commentId);
		reply.setReplyContent(replyContent);
		reply.setReplyedMember(memberDao.getMember(replyedMemberId));
		reply.setReplyMember(memberDao.getMember(replyMemberId));
		reply.setReplyTime(DateUtil.formatDateTime(new Date()));
		redisClient.operate(jedis -> {
			reply.setReplyCommentId(jedis.incr(POST_COMMENT_ID_KEY));
			jedis.lpush(COMMENT_KEY_PREFIX+commentId, JsonUtil.objToStr(reply));
			return null;
		});
		
		return reply;
	}
	
	//根据系统时间获取的帖子排序显示
	@Override
	public List<Post> getPost(Long start,Long end){
		Post post = new Post();
		List<Post> postList = Optional.ofNullable(redisClient.operate(jedis ->{
			Set<String> postIdSet = jedis.zrevrange(SORT_BY_SYSTEM_TIME_KEY, start, end);
			List<Post> innerPostList = new ArrayList<>();
			postIdSet.stream().forEach(postId -> {
				//innerPostList.add(JsonUtil.strToObj(jedis.hget(POST_COMMENT_KEY, postId),Post.class));
				Post postClone = (Post) post.clone();
				Pipeline pl = jedis.pipelined();
				pl.hget(POST_COMMENT_KEY, postId+"");
				pl.llen(POST_KEY_PREFIX+postId);
				List<Object> list = pl.syncAndReturnAll();
				postClone = (Post)JsonUtil.strToObj((String)list.get(0),Post.class);
				postClone.setCommentSize((Long)list.get(1));
				innerPostList.add(postClone);
			});
			return innerPostList;
		})) .orElse(new ArrayList<Post>());
		return postList;
	}
	
	//根据个人时间获取的帖子排序显示
	@Override
	public List<Post> getSpecificPersonPost(Long start,Long end,String department){
		Post post = new Post();
		List<Post> postList = Optional.ofNullable(redisClient.operate(jedis ->{
			Set<String> postIdSet = jedis.zrevrange(SORT_BY_PERSONAL_TIME_KEY_PREFIX+department, start, end);
			List<Post> innerPostList = new ArrayList<>();
			postIdSet.stream().forEach(postId -> {
				Post postClone = (Post) post.clone();
				Pipeline pl = jedis.pipelined();
				pl.hget(POST_COMMENT_KEY, postId);
				pl.llen(POST_KEY_PREFIX+postId);
				List<Object> list = pl.syncAndReturnAll();
				postClone = (Post)JsonUtil.strToObj((String)list.get(0),Post.class);
				postClone.setCommentSize((Long)list.get(1));
				innerPostList.add(postClone);
			});
			return innerPostList;
		})) .orElse(new ArrayList<Post>());
		return postList;
	}
	
	//根据实际帖子ID获取帖子的具体信息，包括评论和回复
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSpecificPostInfo(Long start,Long end,Long postId){
		//Post post = Optional.ofNullable(redisClient.operate(jedis -> (Post)JsonUtil.strToObj(jedis.hget(POST_COMMENT_KEY, postId+"")))).orElseGet(Post::new);
		Map<String, Object> postInfo = redisClient.operate(jedis ->{
			Map<String, Object> map = new HashMap<>();
			Pipeline pl = jedis.pipelined();
			pl.hget(POST_COMMENT_KEY, postId+"");
			pl.lrange(POST_KEY_PREFIX+postId, start, end);
			List<Object> list = pl.syncAndReturnAll();
			Post post = JsonUtil.strToObj((String)list.get(0), Post.class);
			List<Comment> commentList = new ArrayList<>();
			//可能没有评论存在，使用optional避免空指针情况
			Optional.ofNullable((List<String>)list.get(1)).orElseGet(ArrayList<String>::new).stream().forEach(commentStr ->{
				Comment comment = JsonUtil.strToObj(commentStr,Comment.class);
				List<ReplyComment> replyCommentList = new ArrayList<>();
				//此处先做成获取所有回复
				jedis.lrange(COMMENT_KEY_PREFIX+comment.getCommentId(), 0, -1).stream().forEach(replyStr ->{
					replyCommentList.add(JsonUtil.strToObj(replyStr,ReplyComment.class));
				});
				comment.setReplyComment(replyCommentList);
				commentList.add(comment);
			});
			map.put("post", post);
			map.put("commentList", commentList);
			return map;
		});
		return postInfo;
	}
}
