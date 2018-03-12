/**
 * FileName:Result.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月31日下午7:43:17
 ********************************
 *Modifycation History:
 *date:2017年10月31日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.exception;

import com.alibaba.fastjson.JSONObject;

public class Result extends JSONObject {

	/**
	 * @FieldserialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public Result(){
		put("code",200);
		put("msg", "Success");
	}
	
	public static Result success(){
		return new Result();
	}
	
	public static Result error(Error error){
		Result r = new Result();
		r.put("code",error.getCode());
		r.put("msg", error.getMessage());
		return r;
	}
	
	public static Result error(String message){
		Result r = new Result();
		r.put("code",500);
		r.put("msg", message);
		return r;
	}
	
	public static Result error(int code,String message){
		Result r = new Result();
		r.put("code",code);
		r.put("msg", message);
		return r;
	}

	public static Result message(int code,String message){
		Result r = new Result();
		r.put("code",code);
		r.put("msg", message);
		return r;
	}
	
	public static Result returnObj(String name,Object obj){
		Result r = new Result();
		r.put(name, obj);
		return r;
	}
	
}
