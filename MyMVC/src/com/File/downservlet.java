package com.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 娟姐写的 2019-10-10 给定 图片路径 读取显示到 jsp 页面
 * 
 * @author Administrator
 *
 */
@WebServlet("/dow")
public class downservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String fullFile = "D:\\tang\\342.png";
		File file = new File(fullFile);
		if (file.exists()) {
			response.reset();
			response.setContentType("text/html");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename=D:\\tang\\342.png");// 图片路径 写死的
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			if (fileLength != 0) {
				InputStream isStream = new FileInputStream(file); // 获取图片字节流
				byte[] buf = new byte[4096];// 定义字节流缓冲数组
				ServletOutputStream ser = response.getOutputStream(); // 建立图片输出的输出流
				int readLength;
				// 循环输出字节流, 为空时，read()返回 -1
				while (((readLength = isStream.read(buf)) != -1)) {
					ser.write(buf, 0, readLength);
				}
				isStream.close();// 输入完毕，清楚缓冲
				ser.flush();
				ser.close();
			}
		}
	}
}