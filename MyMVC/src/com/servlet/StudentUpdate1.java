package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entry.Student;
import com.service.StudentService;

/**
 * 根据账户名查出要修改的对象
 * 
 * @author Administrator
 *
 */
@WebServlet("/up")
public class StudentUpdate1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String app = req.getParameter("app");

		StudentService studentService = new StudentService();
		Student student = studentService.get(app);

		req.setAttribute("obj", student);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}
}
