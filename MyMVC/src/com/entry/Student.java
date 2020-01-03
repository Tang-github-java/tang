package com.entry;

/**
 * 学生实体类
 * 
 * @author Administrator
 *
 */
public class Student {
	private String app;
	private String addr;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Student [app=" + app + ", addr=" + addr + "]";
	}

}
