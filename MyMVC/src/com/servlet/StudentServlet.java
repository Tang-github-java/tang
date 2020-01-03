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
 * �˻���֤
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

		// ��֤�˻����������Ƿ���ȷ
		boolean isr = studentService.isRight(app, addr);
		if (isr) { // ��ȷ
			HttpSession session = req.getSession();
			session.setAttribute("app", app);// ����session ����ֵ

			List<Student> list = studentService.query();
			req.setAttribute("list", list); // ��Ҫ��ҳ��չʾ��������request����ȥ
			req.getRequestDispatcher("wel.jsp").forward(req, resp);
		} else { // ����
			resp.sendRedirect("login.jsp?ise=1");
		}
	}
}
