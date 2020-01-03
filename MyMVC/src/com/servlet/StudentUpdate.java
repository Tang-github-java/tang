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

/**
 * ÐÞ¸Ä servlet servlet
 * 
 * @author Administrator
 *
 */
@WebServlet("/update")
public class StudentUpdate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String app = req.getParameter("app");
		String addr = req.getParameter("addr");
		Student student = new Student();
		student.setApp(app);
		student.setAddr(addr);

		StudentService studentService = new StudentService();
		studentService.update(student);

		List<Student> list = studentService.query();
		req.setAttribute("list", list);
		req.getRequestDispatcher("wel.jsp").forward(req, resp);
	}
}
