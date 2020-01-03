<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>

<title>删除 </title>
</head>
<body>
<%
		String ise = request.getParameter("ise");
		String msg = "";
	 	if (null != ise && "1".equals(ise)) {
	 		msg="账户名或密码错误";
	 	}
	 %>
    <form action="delete2" method="post">
		<!-- 是根据账户修改的,相当于ID,所以藏起来传过去 -->
		账户:  <input type="text" name="app"  value="${obj.app}"/> <br/>
		输入密码: <input type="text" name="addr" /> <br/>
		
		<button >确认删除</button> <br/>
		<h1><td> <font color="red"><%=msg %></font></td><h1>
	</form>



</body>
</html>