package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entry.Student;
import com.service.StudentService;

@WebServlet("/delete3")
public class StudentDelete3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String app = req.getParameter("app");
		// ����ҵ�������
		StudentService StudentService = new StudentService();
		Student student = StudentService.get(app);

		req.setAttribute("obj", student); // ��Ҫ��ҳ��չʾ��������request����ȥ
		req.getRequestDispatcher("delete.jsp").forward(req, resp);

	}
}
