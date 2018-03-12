package com.formssi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.formssi.bean.Member;
import com.formssi.exception.Error;
import com.formssi.exception.Result;
import com.formssi.service.MemberService;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
//http://192.168.31.202:9001/swagger-ui.html
@Api("用戶操作Api")
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	ParamUtil<String , Object> paramUtil;

	@ApiOperation("查找成员")
	@PostMapping("/getMember")
	public Result getMember(@ApiParam(name="memberId",value = "成员ID",required = true)@PathParam(value = "memberId") Long memberId
			/*@ApiParam(name="memberId",value = "成员ID",required = true)@RequestParam(value = "memberId") Long memberId*/) {
		return Result.returnObj("member", memberService.getMember(memberId));
	}
	
	
	@ApiOperation("新增成员")
	@ApiResponses(@ApiResponse(code=0,message="Success"))
	@PostMapping(value="/addUser")
	public Result addMember(@PathParam(value = "member") @ApiParam(name = "member",value="新增的成员",required = true) Member member
			/*@RequestParam(value = "member") @ApiParam(name = "member",value="新增的成员",required = true) Member member*/) {
		//如果没有被异常捕获，则操作成功
		return Result.returnObj("member", memberService.saveMember(member));
	}
	
	//注销用户功能
	@ApiOperation("删除用户功能")
	@ApiResponses(@ApiResponse(code=0,message="Success"))
	@GetMapping("/deleteMember")
	public Result deleteUser(@ApiParam(name="memberId",value = "成员ID",required = true)@RequestParam Long memberId) {
		
		return memberService.deleteMember(memberId)==0? Result.error(Error.OPERATION_FAIL):Result.success();
	}
	
	//修改用户级别
	@ApiOperation("修改级别")
	@ApiResponses(@ApiResponse(code=0,message="Success"))
	@PostMapping("/modifyMemberRank")
	public Result modifyRank(@ApiParam(name="memberListStr",value = "成员List,只需传memberId和rank",required = true)@PathParam(value="memberListStr") String memberListStr) {
		List<Member> memberList = new ArrayList<>();
		memberList = JSONArray.parseArray(memberListStr).toJavaList(Member.class);
		return memberService.updateAllMember(memberList)== 0?Result.error(Error.FAILED_MODIFY_RANK):Result.success();
	}
	
	//获取所有用户
	@ApiOperation("获取部门所有成员")
	@PostMapping("/getAllMember")
	public Result getAllMember(@ApiParam(name="accountName",value = "登陆用户名",required = true)@PathParam(value = "accountName") String accountName
			/*@ApiParam(name="accountName",value = "登陆用户名",required = true)@RequestParam(value = "accountName") String accountName*/){
		ParamMap<String, Object> param = new ParamMap<>();
		return Result.returnObj("member", memberService.list(paramUtil.getParamMap(param, null, accountName, null)));
	}
	
}
