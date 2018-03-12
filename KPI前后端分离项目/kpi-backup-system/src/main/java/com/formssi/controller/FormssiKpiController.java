/**
 * FileName:SmpKpiController.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月8日下午11:59:58
 ********************************
 *Modifycation History:
 *date:2017年11月8日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.formssi.bean.FormssiKpi;
import com.formssi.bean.Holiday;
import com.formssi.bean.KpiBalanceInfo;
import com.formssi.constans.WorkType;
import com.formssi.dao.FormssiKpiDao;
import com.formssi.dao.HolidayDao;
import com.formssi.exception.Result;
import com.formssi.filter.CorsFilter;
import com.formssi.service.FormssiKpiService;
import com.formssi.util.DateUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping("/sys/kpi")
@RestController
public class FormssiKpiController {
	@Autowired
	@Qualifier("formssiKpiServiceImpl2")
	FormssiKpiService formssiKpiService;
	@Autowired
	ParamUtil<String, Object> paramUtil;
	@Autowired
	HolidayDao holidayDao;
	@Autowired
	FormssiKpiDao formssiKpiDao;
	@Autowired
	CorsFilter corsFilter;
	
	@ApiOperation(value="生成excel")
	@GetMapping("/generate")
	public void generate(HttpServletRequest request,HttpServletResponse response,
			/*@ApiParam(name="date",value="指定年月的随便一个日期",required=true)@PathParam(value = "date") String date,
			@ApiParam(name="department",value="部门名称",required=true)@PathParam(value = "department") String department,
			@ApiParam(name="fileType",value="excel后缀名",required=true)@PathParam(value = "fileType") String fileType*/
			@ApiParam(name="date",value="指定年月的随便一个日期",required=true)@RequestParam String date,
			@ApiParam(name="department",value="部门名称",required=true)@RequestParam String department,
			@ApiParam(name="fileType",value="excel后缀名",required=true)@RequestParam String fileType) throws IOException{
		int[] dateArray = DateUtil.getDateArray(date);
		int year = dateArray[0];
		int month = dateArray[1];
		String fileName = department + year + month + "01." + fileType;
		ParamMap<String, Object> params = new ParamMap<String, Object>();
		byte[] data = formssiKpiService.generate(paramUtil.getParamMap(params, year, month,department,null,null), fileType);
		response.reset();
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		//response.setContentType("application/x-msdownload");
		//前端使用cookie时需配置返回头的来源域为具体域名，否则使用*
		for (Map.Entry<String, String> entry : corsFilter.getHeaderMap().entrySet()) {
			if (!("Content-Type".equals(entry.getKey()) || "Accss-Control-Expose-Headers".equals(entry.getKey()))) {
				response.setHeader(entry.getKey(), entry.getValue());
			}
		}
        /*response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");  
        //配置允许请求的方法类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //设置超时过期时间
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
        //设置cookie认证为允许
        response.setHeader("Access-Control-Allow-Credentials", "true");*/
		IOUtils.write(data, response.getOutputStream());
		//return Result.success();
	}
	
	@ApiOperation("操作Kpi数据接口")
	@GetMapping("/operateKpi")
	public Result operateKPI(@ApiParam(name="formssiKpiListStr",value="kpi數據",required=true)@RequestParam(value="formssiKpiListStr") String formssiKpiListStr,
			@ApiParam(name="kpiData",value="kpi差额",required=true)@RequestParam(value="kpiData")double kpiData
			/*@ApiParam(name="formssiKpiList",value="kpi數據",required=true)@RequestBody List<FormssiKpi> formssiKpiList,
			@ApiParam(name="kpiData",value="kpi差额",required=true)@RequestParam double kpiData*/){
		
		List<FormssiKpi> formssiKpiList = new ArrayList<>();
		formssiKpiList = JSONArray.parseArray(formssiKpiListStr).toJavaList(FormssiKpi.class);
		formssiKpiService.operateKpi(formssiKpiList,kpiData);
		return Result.success();
	}
	
	@ApiOperation("导入正常Kpi")
	@PostMapping("/importNormalKpi")
	public Result importNormalKpi(@ApiParam(name="date",value="指定年月的随便一个日期",required=true)@PathParam(value="date") String date,
			@ApiParam(name="department",value="部门名称",required=true)@PathParam(value="department") String department
			/*@ApiParam(name="date",value="指定年月的随便一个日期",required=true)@RequestParam(value="date") String date,
			@ApiParam(name="department",value="部门名称",required=true)@RequestParam(value="department") String department*/){
		
		return formssiKpiService.batchImport(date,department);
	}
	
	@ApiOperation("获取个人异常Kpi和指定年月的节假日与补班日")
	@PostMapping("/getPersonalKpiandHoliday")
	public String getPersonalKpi(@ApiParam(name="memberName",value="操作的成员名",required=true)@PathParam(value="memberName") String memberName,
	@ApiParam(name="department",value="部门名称",required=true)@PathParam(value="department") String department,
	@ApiParam(name="date",value="该年月随便一个日期",required=true)@PathParam(value="date") String date
			/*@ApiParam(name="memberName",value="操作的成员名",required=true)@RequestParam(value="memberName") String memberName,
			@ApiParam(name="department",value="部门名称",required=true)@RequestParam(value="department") String department,
			@ApiParam(name="date",value="该年月随便一个日期",required=true)@RequestParam(value="date") String date*/) {
		ParamMap<String, Object> params = new ParamMap<>();
		int[] dateArray =DateUtil.getDateArray(date);
		JSONObject jsonObject = new JSONObject();
		List<FormssiKpi> formssiKpiList = Optional.ofNullable(formssiKpiService.getFormssiKpi(paramUtil.getParamMap(params, dateArray[0], dateArray[1], department, memberName,WorkType.NormalWork.name()))).orElse(new ArrayList<>());
		//清空参数给下一个查询sql赋值参数
		params.clear();
		List<Holiday> holidayList = holidayDao.getHolidayList(paramUtil.getParamMap(params, dateArray[0], dateArray[1],null,null,null));
		params.clear();
		double normalDaySize = DateUtil.getDatesList(dateArray[0], dateArray[1], holidayList).size();
		double statisticKpi = normalDaySize + formssiKpiDao.getPersonalKpi(paramUtil.getParamMap(params, dateArray[0], dateArray[1],department,memberName,null));
		jsonObject.put("code", 200);
		jsonObject.put("holidays", holidayList);
		jsonObject.put("formssiKpiList", formssiKpiList);
		jsonObject.put("statisticKpi", statisticKpi);
		return jsonObject.toString();
	}
	
	@ApiOperation("获取指定部门各个级别的kpi统计数据")
	@GetMapping("/getKpiData")
	public Result getKpiData(@ApiParam(name="date",value="指定年月的随便一个日期",required=true)@RequestParam(value="date") String date,
			@ApiParam(name="department",value="部门名称",required=true)@RequestParam(value="department") String department){
		ParamMap<String, Object> params = new ParamMap<>();
		int[] dateArray =DateUtil.getDateArray(date);
		return formssiKpiService.getKpiData(paramUtil.getParamMap(params, dateArray[0], dateArray[1], department,null,null));
	}
	
	@ApiOperation("获取指定部门所有成员的Kpi余额")
	@GetMapping("/getKpiBalanceInfo")
	public Result getKpiBalanceInfo(@ApiParam(name="department",value="部门名称",required=true)@RequestParam(value="department") String department){
		return formssiKpiService.getKpiBalance(department);
	}
	
	@ApiOperation("更新指定成员的Kpi余额")
	@PostMapping("/updateKpiBalance")
	public Result updateKpiBalance(@ApiParam(name="kpiBalanceListStr",value="需传kpiBlanceInfo中的memberId和kpiBalance的值即可",required=true)@RequestParam(value="kpiBalanceListStr") String kpiBalanceListStr){
		List<KpiBalanceInfo> kbiList = new ArrayList<>();
		kbiList = JSONArray.parseArray(kpiBalanceListStr).toJavaList(KpiBalanceInfo.class);
		return formssiKpiService.updateKpiBalanceInfo(kbiList);
	}
}
