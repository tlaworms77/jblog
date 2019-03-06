package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	
	public void join(UserVo userVo) {
		
		long userNo = userDao.join(userVo);
		boolean result = 1 == blogDao.join(userNo);
		if(result == false) {
			System.out.println("UserService's insert user/Blog Method result : " + result);
			return;
		}
		System.out.println("Join Success! user&blog <default>");
		
		
	}

	public UserVo getUser(String id, String password) {
		return userDao.getLoginUser(id, password);
	}

	public long getUserNo(String id) {
		return userDao.getUserNo(id);
	}

	public boolean checkId(String id) {
		UserVo userVo = userDao.checkUserId(id);
		return userVo != null;
	}

}
