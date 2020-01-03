<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>

<title>修改页面</title>
</head>
<body>
<form action ="update" method = "post">
<!-- 是根据账户修改的,相当于ID,所以藏起来传过去 -->
		<input type="hidden" name="app" value="${obj.app}"/> 
		账户: ${obj.app} <br/> <!-- 账户不能修改,只是显示看一看 -->
		新密码: <input type="text" name="addr" value="${obj.addr}" /> <br/>
		<button>保存</button> <br/>

</form>
</body>
</html>