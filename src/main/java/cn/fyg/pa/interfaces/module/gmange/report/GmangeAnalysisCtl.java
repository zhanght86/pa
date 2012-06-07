package cn.fyg.pa.interfaces.module.gmange.report;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.deptmonthplan.IdrMonthPlanEnum;
import cn.fyg.pa.domain.model.monthchk.MonthChkEnum;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/gmange/{personId}/analysis")
public class GmangeAnalysisCtl {
	
	@Resource
	GmangeAnalysisFacade gmangeAnalysisFacade;
	
	@RequestMapping(value="/idrmonthplan",method=RequestMethod.GET)
	public String analyseIdrMonthPlan(YearAndMonthQueryBean queryBean,Map<String,Object> map){
		AnalysisIdrMonthPlanBean analysisIdrMonthPlanBean=gmangeAnalysisFacade.analyseIdrMonthPlan(queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum",IdrMonthPlanEnum.values());
		map.put("queryBean", queryBean);
		map.put("analysisIdrMonthPlanBean", analysisIdrMonthPlanBean);
		return "gmangeanalysis/idrmonthplan";
	}
	
	@RequestMapping(value="/monthchk",method=RequestMethod.GET)
	public String analyseMonthChk(YearAndMonthQueryBean queryBean,Map<String,Object> map){
		AnalysisMonthChkBean analysisMonthChkBean = gmangeAnalysisFacade.analyseMonthChk(queryBean.getYear(), queryBean.getMonth());
		map.put("dateTool", new DateTool());
		map.put("stateEnum", MonthChkEnum.values());
		map.put("queryBean", queryBean);
		map.put("analysisMonthChkBean", analysisMonthChkBean);
		return "gmangeanalysis/monthchk";
	}
}
