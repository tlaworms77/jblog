package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	public void join(UserVo userVo) {
		
		long userNo = userDao.join(userVo);
		boolean result = 1 == blogDao.join(userNo);
		if(result == false) {
			System.out.println("UserService's insert user/Blog Method result : " + result);
			return;
		}
		result = 1 == categoryDao.defaultCategory(userNo);
		if(result == false) {
			System.out.println("UserService's insert default-Category result : " + result);
			return;
		}
		long categoryNo = categoryDao.getInsertLastNo();
		
		result = 1 == postDao.defaultPost(categoryNo);
		
		System.out.println("Join Success! user&blog&category&post <default>");
		
		
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
