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

@WebServlet("/delete2")
public class StudentDelete2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String app = req.getParameter("app");
		String addr = req.getParameter("addr");
		// 创建业务类对象
		Student student = new Student();

		student.setAddr(addr);
		student.setApp(app);

		StudentService StudentService = new StudentService();

		boolean st = StudentService.isRight(app, addr);
		System.out.println(st);
		if (st) {
			StudentService.delete(app);
			HttpSession session = req.getSession();
			session.setAttribute("app", app);

			List<Student> list = StudentService.query();
			req.setAttribute("list", list);
			req.getRequestDispatcher("wel.jsp").forward(req, resp);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("app", app);

			List<Student> list = StudentService.query();
			req.setAttribute("list", list);
			resp.sendRedirect("delete.jsp?ise=1");
		}
	}
}
