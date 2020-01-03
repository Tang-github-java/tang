<%@ page language="java" contentType="text/html; charset=utf-8"%>


<%@page import=" com.entry.Student,java.util.*"%>

<html>
<head>

<title> 欢迎登录 </title>
</head>
<body>

        
    
	<h1 align="center">   欢迎     <%= session.getAttribute("app")%>   </h1>
	
	
	<%
		
	       List<Student> list = (List<Student>)request.getAttribute("list");
		String pageCount = (String)request.getAttribute("pageCount");
	%>
	
	
	
	
	
   <table  align="center" border = "1" ;>
         <tr>
			 <th >账户</th>
			  <th>信息</th>	
				<th>修改操作</th>
				<th>删除操作</th>
		</tr>
       <% for(Student student:list) {%>
			<tr>
				<td><%=student.getApp() %></td>
				<td><%=student.getAddr() %></td>
				 <td><a href="up?app=<%=student.getApp() %>">修改</a></td> 
				 <td><a href="delete3?app=<%=student.getApp() %>">删除</a></td> 
			    
		<% }%>
		</tr>
		</table>
              <h1 align="center"><a href="add.jsp">注册</a></h1>
              
      
      
      
      
<form action="upload" method="POST" enctype="multipart/form-data">
		名字<input type="text" name="name"/> <br />
		性别<input type="text" name="sex"/> <br />
		头像<input type="file" name="fileOther"> <br />
		<input type="submit" value="提交"/> <br />			
	 </form>

	 <form action="download">
        <input type="submit" value="下载"><!--下载按钮 -->
     </form>
</body>
</html>