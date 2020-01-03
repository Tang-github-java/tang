package com.service;

import java.util.List;

import com.dao.StudentDao;
import com.entry.Student;

/**
 * ѧ�� ҵ����
 * 
 * @author Administrator
 *
 */

public class StudentService {
	/**
	 * new studentDao ����
	 */
	StudentDao studentDao = new StudentDao();

	/**
	 * ���
	 * 
	 * @param student
	 */
	public void add(Student student) {
		studentDao.add(student);
	}

	/**
	 * ɾ��
	 * 
	 * @param student
	 */
	public void delete(String app) {
		studentDao.delete(app);
	}

	/**
	 * �޸�
	 * 
	 * @param student
	 */
	public void update(Student student) {
		studentDao.update(student);
	}

	/**
	 * ��ѯ
	 * 
	 * @return List<Student>
	 */
	public List<Student> query() {

		return studentDao.query();

	}

	/**
	 * ���ҳ
	 * 
	 * @param pageNowStr
	 * @return
	 */
	public List<Student> queryAll(String pageNowStr) {
		return studentDao.queryAll(pageNowStr);

	}

	/**
	 * �����˻���������������
	 * 
	 * @param acc
	 * @param pswd
	 * @return Account;
	 */
	public Student getAccountByAccAndPswd(String app, String addr) {
		return studentDao.getAccountByAccAndPswd(app, addr);
	}

	/**
	 * ��֤�˻����������Ƿ���ȷ
	 * 
	 * @param acc
	 * @param pswd
	 * @return boolean true����ȷ false�Ǵ���
	 */
	public boolean isRight(String app, String addr) {
		Student student = getAccountByAccAndPswd(app, addr);
		if (null != student && app.equals(student.getApp()) && addr.equals(student.getAddr())) {
			return true; // �˻����������ǶԵ� ����true
		}
		return false; // ��ķ���false
	}

	/**
	 * �����˻�����������
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
