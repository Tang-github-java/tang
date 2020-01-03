<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>

<title>账户登录</title>
</head>
<body bgcolor="aqua">

<h1 align="center">首页</h1>
<%
		String ise = request.getParameter("ise");
		String msg = "";
	 	if (null != ise && "1".equals(ise)) {
	 		msg="账户名或密码错误";
	 	}
	 %>
 <form  action  = "login" method = "post">
      <table align="center">
         <tr> 
           <td>用户:</td>
           <td> <input type = "text" name = "app" ></td>
         </tr>
         <tr>
           <td>密码:</td>
             <td> <input type = "text" name = "addr""></td>
          </tr>
              <tr>
				<td colspan="2">
					 <input type="checkbox" name="keepLogin" id="keepLoginId" style="margin-left: 60px;"/>
					 	两周内不再重新登录
				</td>
			</tr>
         
           <td> <button>登录</button></td>
    </table>
    <font color="red"><%=msg %></font>    
</form> 
<% 



%>

<% String isLogin = (String)session.getAttribute(session.getId());
	if (!"OK".equals(isLogin)) { 
		//获得cookie判断是否有
Cookie c[] = request.getCookies();
	if (null != c) {
		for(Cookie temp:c){
		}
	}else {
		response.sendRedirect("login.jsp");
		return;
            }
	}
	%>
      
</body>
</html>