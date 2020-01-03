package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entry.Student;

public class StudentDao {

	/**
	 * �õ����ݿ����
	 * 
	 * @param student
	 */
	private Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/java?useSSL=false&serverTimezone=UTC", "root", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * ���
	 * 
	 * @param student
	 */
	public void add(Student student) {
		Connection conn = getConn();

		try {
			String sql = "insert into  student(app,addr)values(?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, student.getApp());
			st.setString(2, student.getAddr());
			st.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * ɾ��
	 * 
	 * @param student
	 */
	public void delete(String app) {
		Connection conn = getConn();
		try {

			String sql = " delete  from student where app=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, app);
			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �޸�
	 * 
	 * @param student
	 */
	public void update(Student student) {
		Connection conn = getConn();

		try {
			String sql = "update   student set addr=? where app= ?";
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, student.getAddr());
			st.setString(2, student.getApp());
			st.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ����
	 */
	public List<Student> query() {
		List<Student> list = new ArrayList<>();
		try {
			Connection conn = getConn();
			Statement st = conn.createStatement();
			String sql = "select * from student ";
			ResultSet rs = st.executeQuery(sql); // Ҫִ��select ������ʹ�� executeQuery ���� ����ѯ���������ݷŵ���ResultSet������
			while (rs.next()) { // ��������ȡ��ѯ��� ��ResultSet�����е�����,��Ҫͨ��whileѭ������ȡ���� ResultSet�Ķ���.next�Ǿ�����ȡһ������
				// ��ʵ����һ����ʽ�α� ÿ������һ��,��������ݾͻ����,��ֱ����ResultSet����.����(�����int��.getInt,..)���ܵõ���Ӧ��ֵ
				Student we = new Student();

				we.setApp(rs.getString("app"));
				we.setAddr(rs.getString("addr"));
				list.add(we); // ��ӵ�������
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �����˻���������������
	 * 
	 * @param acc
	 * @param pswd
	 * @return Student;
	 */
	public Student getAccountByAccAndPswd(String app, String addr) {
		Student student = null;
		try {
			Connection conn = getConn();
			String sql = " select * from student where app = ? and addr = ? ";
			PreparedStatement pst = conn.prepareStatement(sql); // PreparedStatement������ִ��sql��
			pst.setString(1, app);
			pst.setString(2, addr);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				student = new Student();// �鵽��ֵ,����װʱ��new
				student.setApp(rs.getString("app"));
				student.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * �����˻����������
	 * 
	 * @param acc
	 * @return Account;
	 */
	public Student get(String app) {
		Student student = null;
		try {
			Connection conn = getConn();
			String sql = " select * from student where app = ? ";
			PreparedStatement pst = conn.prepareStatement(sql); // PreparedStatement������ִ��sql��
			pst.setString(1, app);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				student = new Student();// �鵽��ֵ,����װʱ��new
				student.setApp(rs.getString("app"));
				student.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * Ϊʲô������ResultSet ����Ҫ����ArrayList ����ResultSet��ʾ���ݿ�����Ӳ��ܶ�,��ʱ�����ӵ����ݿ�ķ���Դ
	 * 
	 * ����ص���������,ResultSet�ͻ�ϵ�,Ҳ���޷���ȡֵ
	 * 
	 * @param pageNowStr
	 * @return
	 */
	public List<Student> queryAll(String pageNowStr) {
		try {
			Connection conn = getConn();
			Statement st = conn.createStatement();
			ResultSet countRs = st.executeQuery("select * from student");
			// ��ҳ:
			int pageNow = Integer.parseInt(pageNowStr); // ��ǰ�Ƕ���ҳ
			int pageSize = 3; // һҳ��ʾ��������¼
			int pageCount = getPageCount();

			// �ݴ�
			if (pageNow == 0) {
				pageNow = 1;
			}
			if (pageNow > pageCount) {
				pageNow = pageCount;
			}
			countRs.close();
			// ��ѯҪ��ʾ��¼:
			String sql = "select * from student  WHERE id  LIMIT " + (pageNow - 1) * pageSize + "," + pageSize;
			ResultSet rs = st.executeQuery(sql);
			List<Student> dataList = new ArrayList<>();
			while (rs.next()) {
				Student users = new Student();
				users.setApp(rs.getString(1));
				users.setAddr(rs.getString(2));

				dataList.add(users);
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getPageCount() throws Exception {
		Connection conn = getConn();
		Statement st = conn.createStatement();
		ResultSet countRs = st.executeQuery("select * from student ");
		int pageCount = 0; // һ���ж���ҳ
		int pageSize = 3; // һҳ��ʾ��������¼
		int rowCount = 0; // һ���ж�������¼
		if (countRs.next()) {
			rowCount = countRs.getInt(1);
		}
		if (countRs.next()) {
			rowCount = countRs.getInt(1);
		}
		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = rowCount / pageSize + 1;
		}
		return pageCount;
	}

}
