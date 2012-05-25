<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<!doctype html>
<html>
<head>
<title><decorator:title/></title>
<decorator:head />
<link type="text/css" rel="stylesheet" href="/${ctx}/resources/css/mainbar.css" /> 
<script type="text/javascript">
$(function(){
	$('.nav').children("li:has(ul)").hover(
		function(){
			$(this).children("ul").slideDown();
		},
		function(){
			$(this).children("ul").hide();
		}
	);
});
</script>

</head>
<%@ page import="java.util.*" %>
<%
	String loginInfo=(String)session.getAttribute("loginInfo");
	request.setAttribute("loginInfo", loginInfo);
	List menuList=(List)session.getAttribute("menuList");
	request.setAttribute("menuList", menuList);
%>

<body>

<div id="mainbar">
	<div class="top">
		<div class="top_left">
			<div class="main_head">方远房产卓越绩效管理平台</div>
		</div>
		<div class="top_right">
			<div class="main_blank"></div>
			<div class="main_info">${loginInfo}&nbsp;&nbsp;</div>
		</div>
		<div class="clear_div"></div>
	</div>
	
	<div class="second">
		<div class="second_left" >
			<a href="${pagefunc.url}">${pagefunc.name}</a>&gt;<a href="${pagetitle.url}">${pagetitle.name}</a>
		</div>
		<div class="second_right" >
			<!-- 功能菜单  -->
			<ul class="nav" style="float:left">
				<li>
					<div>&nabla;绩效系统功能&nbsp;&nbsp;</div>
					<ul>
					<c:forEach var="menu" items="${menuList}">
						<li><a href="/${ctx}/${menu.url}"><strong>&nbsp;&nbsp;${menu.name}&nbsp;&nbsp;</strong></a></li>
					</c:forEach>
					</ul>
				</li>
				<li>
					<div>&nabla;系统功能&nbsp;&nbsp;</div>
					<ul>
					
						<li><a href="/${ctx}/common/settings/person/${loginRet.personid}/password">&nbsp;&nbsp;修改密码&nbsp;&nbsp;</a></li>
					
						<li><a href="/${ctx}/login">&nbsp;&nbsp;退出系统&nbsp;&nbsp;</a></li>
					
					</ul>
				</li>
			</ul>
		</div>
		<div class="clear_div"></div>
	</div>

</div>





<decorator:body />  
</body>
</html>
