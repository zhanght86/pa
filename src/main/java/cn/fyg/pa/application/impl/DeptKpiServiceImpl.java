package cn.fyg.pa.application.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.DeptKpiService;
import cn.fyg.pa.domain.model.companykpi.IdrCompany;
import cn.fyg.pa.domain.model.department.Department;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicator;
import cn.fyg.pa.domain.model.deptindicator.DeptIndicatorRepository;
import cn.fyg.pa.domain.model.deptkpi.DeptKpi;
import cn.fyg.pa.domain.model.deptkpi.DeptKpiFactory;
import cn.fyg.pa.domain.model.deptkpi.DeptKpiRepository;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItem;
import cn.fyg.pa.domain.model.deptkpiitem.DeptKpiItemRepository;
import cn.fyg.pa.domain.shared.Result;

@Service
public class DeptKpiServiceImpl implements DeptKpiService {
	
	@Resource
	DeptKpiRepository deptKpiRepository;
	
	@Resource
	DeptKpiItemRepository deptKpiItemRepository;
	
	@Resource
	DeptIndicatorRepository deptIndicatorRepository;

	@Override
	public DeptKpi getDeptKpiByYearAndDepartment(Long year, Department department) {
		DeptKpi deptKpi = deptKpiRepository.findByYearAndDepartment(year, department);
		if(deptKpi==null){
			return DeptKpiFactory.createDeptKpiByYearAndDepartment(year, department);
		}
		return deptKpi;
	}

	@Override
	@Transactional
	public void saveDeptKpiItems(DeptKpi deptKpi, IdrCompany idrCompany,
			List<DeptKpiItem> deptKpiItems) {
		deptKpiRepository.save(deptKpi);
		List<DeptKpiItem> oldItems=deptKpiItemRepository.findByYearAndDepartmentAndIdrCompanyOrderBySn(deptKpi.getYear(), deptKpi.getDepartment(), idrCompany);
		Set<Long> newItemIds=new HashSet<Long>();
		for (DeptKpiItem deptKpiItem : deptKpiItems) {
			newItemIds.add(deptKpiItem.getId());
		}
		
		for(DeptKpiItem  deptKpiItem: oldItems) {
			if(!newItemIds.contains(deptKpiItem.getId())){
				deptKpiItemRepository.remove(deptKpiItem);
			}
		}
		for (DeptKpiItem deptKpiItem : deptKpiItems) {
			deptKpiItemRepository.save(deptKpiItem);
		}
	}

	@Override
	public Result commitDeptKpi(DeptKpi deptKpi) {
		DeptIndicator deptIndicator = deptIndicatorRepository.findByYearAndDepartment(deptKpi.getYear(), deptKpi.getDepartment());
		return deptKpi.verifySelf(deptIndicator);
	}

}