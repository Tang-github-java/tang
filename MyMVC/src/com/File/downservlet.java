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
 * ���д�� 2019-10-10 ���� ͼƬ·�� ��ȡ��ʾ�� jsp ҳ��
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
			response.setHeader("Content-Disposition", "attachment;filename=D:\\tang\\342.png");// ͼƬ·�� д����
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			if (fileLength != 0) {
				InputStream isStream = new FileInputStream(file); // ��ȡͼƬ�ֽ���
				byte[] buf = new byte[4096];// �����ֽ�����������
				ServletOutputStream ser = response.getOutputStream(); // ����ͼƬ����������
				int readLength;
				// ѭ������ֽ���, Ϊ��ʱ��read()���� -1
				while (((readLength = isStream.read(buf)) != -1)) {
					ser.write(buf, 0, readLength);
				}
				isStream.close();// ������ϣ��������
				ser.flush();
				ser.close();
			}
		}
	}
}