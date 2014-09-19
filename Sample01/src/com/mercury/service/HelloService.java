package com.mercury.service;

import com.mercury.beans.User;
import com.mercury.beans.UserInfo;
import com.mercury.dao.HelloDao;
import com.mercury.dao.impl.HelloDaoImpl;

public class HelloService {
	private HelloDao hd;
	
	public HelloService() {
		if (hd==null) {
			hd = new HelloDaoImpl();
		}
	}
	
	public UserInfo process(User user) {
		hd.save(user);
		UserInfo userInfo = new UserInfo();
		userInfo.setMessage("Hello " + user.getName() + ", welcome to JavaEE!");
		userInfo.setUsers(hd.queryAll());
		return userInfo;
	}
	
}
