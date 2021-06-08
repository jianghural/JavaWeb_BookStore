<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AL书城会员登录页面</title>

<!-- 抽取公共内容 静态包含 base标签 css样式 jQuery文件 -->
<%@ include file="/pages/comon/head.jsp" %>
		
<script type="text/javascript">
	// 页面加载完成之后
	$(function(){
		
		$("#sub_btn").click(function () {
			// 用户名和密码不能为空
			var usernameText = $.trim($("#username").val());
			var passwordText = $.trim($("#password").val());
			if ((usernameText == null || usernameText == "") || (passwordText == null || passwordText == "")) {
				return false;
			}
			
			$(".errorMsg").text("");
		});
		
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>AL书城会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!-- 请输入用户名和密码 -->
								<span class="errorMsg">
									<%-- <%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg") %> --%>
									<!-- el表达式 -->
									${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg }
								</span>
							</div>
							<div class="form" >
								<form action="UserServlet" method="POST">
									<input type="hidden" name="action" value="login" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" 
									autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" 
									autocomplete="off" tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			
<!-- 抽取公共内容 静态包含 页脚 -->
<%@ include file="/pages/comon/footer.jsp" %>
	
</body>
</html>