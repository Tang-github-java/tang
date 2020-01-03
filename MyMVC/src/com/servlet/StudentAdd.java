package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entry.Student;
import com.service.StudentService;

@WebServlet("/add")
public class StudentAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);// µ÷ÓÃdopost

	}

	/**
	 * ×¢²á
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String app = req.getParameter("app");
		String addr = req.getParameter("addr");

		Student student = new Student();

		student.setApp(app);
		student.setAddr(addr);
		StudentService studentService = new StudentService();
		studentService.add(student);

		List<Student> list = studentService.query();
		req.setAttribute("list", list);
		req.getRequestDispatcher("wel.jsp").forward(req, resp);
	}
}
