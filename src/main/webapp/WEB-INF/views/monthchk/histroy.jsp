<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="月度工作任务" scope="request"/> 
<c:set var="pagetitle" value="员工月度工作任务完成历史" scope="request"/> 
<c:set var="pagesize" value="800" scope="request"/> 
<body>
<form action="" method="get">
<div class="headdiv" >
<div class="headleft" >
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;
<input type="submit" value="查询" />
</div>
<div class="headright">
<input type="button" value="<<当前考核" onclick="javascript:window.open('/${ctx}/person/${person.id}/monthchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>
</form>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/monthchktable.jsp"%>
</body>
</html>
