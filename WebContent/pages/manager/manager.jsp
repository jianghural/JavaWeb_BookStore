<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

<!-- 抽取公共内容 静态包含 base标签 css样式 jQuery文件 -->
<%@ include file="/pages/comon/head.jsp" %>
		
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">后台管理系统</span>
					
<!-- 抽取公共内容 静态包含 manager 模块的菜单 -->
<%@ include file="/pages/comon/manager_menu.jsp" %>
	
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>
	
		
<!-- 抽取公共内容 静态包含 页脚 -->
<%@ include file="/pages/comon/footer.jsp" %>
	
</body>
</html>