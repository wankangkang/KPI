package com.formssi.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formssi.bean.KpiStyle;
import com.formssi.dao.KpiStyleDao;
import com.formssi.exception.Result;
import com.formssi.service.KpiStyleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class KpiStyleController {

	@Autowired
	KpiStyleService kpiStyleService;
	@Autowired
	KpiStyleDao kpiStyleDao;
	@ApiOperation("获取kpi样式")
	@GetMapping("/getKpiStyle")
	public Result getKpiStyle(@ApiParam(name="department",value="部门名称",required=true)@RequestParam String department) {
		return Result.returnObj("kpiStyle", Optional.ofNullable(kpiStyleService.getKpiStyle(department)).orElse(kpiStyleService.getKpiStyle("default")));
	}
	
	@ApiOperation("保存kpi样式")
	@PostMapping("/saveKpiStyle")
	public Result saveKpiStyle(@ApiParam(name="kpiStyle",value="kpi样式，只传项目编号，名称以及部门",required=true)@PathParam(value="kpiStyle") KpiStyle kpiStyle) {
		kpiStyleService.saveKpiStyle(kpiStyle);
		return Result.success();
	}
}
