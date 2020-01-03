package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entry.Student;
import com.service.StudentService;

/**
 * 账户认证
 * 
 * @author Administrator
 *
 */
@WebServlet("/login")
public class StudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String app = req.getParameter("app");
		String addr = req.getParameter("addr");
		StudentService studentService = new StudentService();

		// 验证账户名和密码是否正确
		boolean isr = studentService.isRight(app, addr);
		if (isr) { // 正确
			HttpSession session = req.getSession();
			session.setAttribute("app", app);// 创建session 并赋值

			List<Student> list = studentService.query();
			req.setAttribute("list", list); // 把要在页面展示的数据用request带过去
			req.getRequestDispatcher("wel.jsp").forward(req, resp);
		} else { // 错误
			resp.sendRedirect("login.jsp?ise=1");
		}
	}
}
