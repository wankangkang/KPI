/**
 * FileName:ImportFlagDao.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年11月7日下午11:44:09
 ********************************
 *Modifycation History:
 *date:2017年11月7日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.formssi.bean.ImportFlag;

@Mapper
@Transactional
public interface ImportFlagDao {
	int saveFlag(ImportFlag importFlag);
	
	int count(Map<String,Object> map);
}
