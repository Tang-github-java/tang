package Cookie;

import java.awt.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.service.StudentService;

public class MyTag extends SimpleTagSupport {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNow = request.getParameter("pageNow");
		StudentService studentService = new StudentService();
		List dataList = (List) studentService.queryAll(pageNow);
		request.setAttribute("dataList", dataList);
		try {
			request.setAttribute("pageCount", studentService.getPageCount() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("mvc/welcome.jsp").forward(request, response);
	}
}
