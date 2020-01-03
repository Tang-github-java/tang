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

@WebServlet("/delete")
public class StudentDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String app = req.getParameter("app");
		// 创建业务类对象
		StudentService StudentService = new StudentService();

		StudentService.delete(app);

		List<Student> list = StudentService.query();
		req.setAttribute("list", list); // 把要在页面展示的数据用request带过去
		req.getRequestDispatcher("wel.jsp").forward(req, resp);

	}
}
