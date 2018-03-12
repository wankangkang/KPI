package com.formssi.util;

import org.springframework.stereotype.Component;

import com.formssi.annotation.Param;

@Component
public class ParamUtil<K ,V> {

	@Param(key= {"year","month","department","memberName","workType"},value= {"year","month","department","memberName","workType"})
	public ParamMap<K, V> getParamMap(ParamMap<K, V> params,int year ,int month,String department,String memberName,String workType){
		
		return params;
	}
	
	@Param(key= {"rank","department","memberName"},value= {"rank","department","memberName"})
	public ParamMap<K, V> getParamMap(ParamMap<K, V> params,String rank ,String department,String memberName){

		return params;
	}
	
	@Param(key= {"accountName","password"},value= {"accountName","password"})
	public ParamMap<K, V> getParamMap(ParamMap<K, V> params,String accountName ,String password){

		return params;
	}
	
	@Param(key= {"accountName","email"},value= {"accountName","email"})
	public ParamMap<K, V> getEmailParamMap(ParamMap<K, V> params,String accountName ,String email){

		return params;
	}
	
	@Param(key= {"date","memberName","department"},value= {"date","memberName","department"})
	public ParamMap<K, V> getKpiParamMap(ParamMap<K, V> params,String date ,String memberName,String department){

		return params;
	}
	
	@Param(key= {"memberName","department","workType"},value= {"memberName","department","workType"	})
	public ParamMap<K, V> getPersonalKpiParamMap(ParamMap<K, V> params,String memberName,String department,String workType){

		return params;
	}
	
	@Param(key= {"memberId","kpiBalance"},value= {"memberId","kpiBalance"})
	public ParamMap<K, V> getKpiBalanceParamMap(ParamMap<K, V> params,Long memberId,double kpiBalance){

		return params;
	}
}
