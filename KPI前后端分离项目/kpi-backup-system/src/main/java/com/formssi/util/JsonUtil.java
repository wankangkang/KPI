/**
 * FileName:JsonUtil.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月16日上午12:44:09
 ********************************
 *Modifycation History:
 *date:2018年1月16日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.util;

import com.alibaba.fastjson.JSON;

/**
 * @author Bill
 *
 */
public class JsonUtil {

	public static String objToStr(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	public static <T extends Object>T strToObj(String str,Class<T> clazz) {
		return JSON.parseObject(str,clazz);
	}
}
