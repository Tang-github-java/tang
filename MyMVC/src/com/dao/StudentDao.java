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
	 * 得到数据库对象
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
	 * 添加
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
	 * 删除
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
	 * 修改
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
	 * 查询数据
	 */
	public List<Student> query() {
		List<Student> list = new ArrayList<>();
		try {
			Connection conn = getConn();
			Statement st = conn.createStatement();
			String sql = "select * from student ";
			ResultSet rs = st.executeQuery(sql); // 要执行select 语句必须使用 executeQuery 方法 将查询出来的数据放到了ResultSet对象中
			while (rs.next()) { // 这里是在取查询结果 在ResultSet对象中的数据,需要通过while循环才能取出来 ResultSet的对象.next是就向下取一个数据
				// 其实就是一个隐式游标 每向下走一个,如果有数据就会进来,则直接用ResultSet对像.方法(如果是int就.getInt,..)就能得到相应的值
				Student we = new Student();

				we.setApp(rs.getString("app"));
				we.setAddr(rs.getString("addr"));
				list.add(we); // 添加到集合中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据账户名和密码查出对象
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
			PreparedStatement pst = conn.prepareStatement(sql); // PreparedStatement是用来执行sql的
			pst.setString(1, app);
			pst.setString(2, addr);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				student = new Student();// 查到有值,进来装时才new
				student.setApp(rs.getString("app"));
				student.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * 根据账户名查出对象
	 * 
	 * @param acc
	 * @return Account;
	 */
	public Student get(String app) {
		Student student = null;
		try {
			Connection conn = getConn();
			String sql = " select * from student where app = ? ";
			PreparedStatement pst = conn.prepareStatement(sql); // PreparedStatement是用来执行sql的
			pst.setString(1, app);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				student = new Student();// 查到有值,进来装时才new
				student.setApp(rs.getString("app"));
				student.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * 为什么不返回ResultSet 而是要返回ArrayList 返回ResultSet表示数据库的链接不能断,长时间链接到数据库耗费资源
	 * 
	 * 如果关掉数据链接,ResultSet就会断掉,也就无法再取值
	 * 
	 * @param pageNowStr
	 * @return
	 */
	public List<Student> queryAll(String pageNowStr) {
		try {
			Connection conn = getConn();
			Statement st = conn.createStatement();
			ResultSet countRs = st.executeQuery("select * from student");
			// 分页:
			int pageNow = Integer.parseInt(pageNowStr); // 当前是多少页
			int pageSize = 3; // 一页显示多少条记录
			int pageCount = getPageCount();

			// 容错
			if (pageNow == 0) {
				pageNow = 1;
			}
			if (pageNow > pageCount) {
				pageNow = pageCount;
			}
			countRs.close();
			// 查询要显示记录:
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
		int pageCount = 0; // 一共有多少页
		int pageSize = 3; // 一页显示多少条记录
		int rowCount = 0; // 一共有多少条记录
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
