package com.service;

import java.util.List;

import com.dao.StudentDao;
import com.entry.Student;

/**
 * 学生 业务类
 * 
 * @author Administrator
 *
 */

public class StudentService {
	/**
	 * new studentDao 对象
	 */
	StudentDao studentDao = new StudentDao();

	/**
	 * 添加
	 * 
	 * @param student
	 */
	public void add(Student student) {
		studentDao.add(student);
	}

	/**
	 * 删除
	 * 
	 * @param student
	 */
	public void delete(String app) {
		studentDao.delete(app);
	}

	/**
	 * 修改
	 * 
	 * @param student
	 */
	public void update(Student student) {
		studentDao.update(student);
	}

	/**
	 * 查询
	 * 
	 * @return List<Student>
	 */
	public List<Student> query() {

		return studentDao.query();

	}

	/**
	 * 查分页
	 * 
	 * @param pageNowStr
	 * @return
	 */
	public List<Student> queryAll(String pageNowStr) {
		return studentDao.queryAll(pageNowStr);

	}

	/**
	 * 根据账户名和密码查出对象
	 * 
	 * @param acc
	 * @param pswd
	 * @return Account;
	 */
	public Student getAccountByAccAndPswd(String app, String addr) {
		return studentDao.getAccountByAccAndPswd(app, addr);
	}

	/**
	 * 验证账户名和密码是否正确
	 * 
	 * @param acc
	 * @param pswd
	 * @return boolean true是正确 false是错误
	 */
	public boolean isRight(String app, String addr) {
		Student student = getAccountByAccAndPswd(app, addr);
		if (null != student && app.equals(student.getApp()) && addr.equals(student.getAddr())) {
			return true; // 账户名和密码是对的 返回true
		}
		return false; // 错的返回false
	}

	/**
	 * 根据账户名码查出对象
	 * 
	 * @param app
	 * @return Student;
	 */
	public Student get(String app) {
		return studentDao.get(app);
	}

	public int getPageCount() {
		try {
			return studentDao.getPageCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
