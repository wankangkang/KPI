package com.formssi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.formssi.bean.KpiStyle;
import com.formssi.dao.KpiStyleDao;
import com.formssi.service.KpiStyleService;

@Service
@CacheConfig(cacheNames="kpiStyle")
public class KpiStyleServiceImpl implements KpiStyleService {

	@Autowired
	KpiStyleDao kpiStyleDao;
	
	@Override
	@CacheEvict(key="#root.targetClass + #kpiStyle.getDepartment()")
	public KpiStyle saveKpiStyle(KpiStyle kpiStyle) {
		
		if (kpiStyleDao.getKpiStyle(kpiStyle.getDepartment()) != null) {
			kpiStyleDao.updateKpiStyle(kpiStyle);
		}else {
			kpiStyleDao.saveKpiStyle(kpiStyle);
		}
		return kpiStyle;
	}

	@Override
	@Cacheable(key="#root.targetClass + #department",sync=true)
	public KpiStyle getKpiStyle(String department) {
		return kpiStyleDao.getKpiStyle(department);
	}

}
