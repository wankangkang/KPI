package com.formssi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.formssi.bean.Holiday;
import com.formssi.dao.HolidayDao;
import com.formssi.util.DateUtil;
import com.formssi.util.ParamMap;
import com.formssi.util.ParamUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Deprecated
public class HolidayController {

	@Autowired
	HolidayDao holidayDao;
	@Autowired
	ParamUtil<String, Object> paramUtil;
	@ApiOperation("获取指定年月的节假日与补班日")
	@GetMapping("/getHoliday")
	public String getHolidayList(@ApiParam(name="date",value="该年月随便一个日期",required=true)@RequestParam String date){
		ParamMap<String, Object> param = new ParamMap<String, Object>();
		JSONObject jsonObject = new JSONObject();
		int[] dateArray =DateUtil.getDateArray(date);
		param.put("year", dateArray[0]);
		param.put("month", dateArray[1]);
		List<Holiday> holidayList = holidayDao.getHolidayList(paramUtil.getParamMap(param, dateArray[0], dateArray[1],null,null,null));
		jsonObject.put("code", 200);
		jsonObject.put("holidays", holidayList);
		return jsonObject.toString();
	}
	
}
