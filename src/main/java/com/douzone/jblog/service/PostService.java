package com.douzone.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;

	public void write(PostVo postVo, long category) {
		postVo.setCategoryNo(category);
		boolean result = 1 == postDao.write(postVo);
		
		if(!result)
			return ;
		System.out.println("PostService's write result : " + result);
	}

	public int getAmountByCategoryNo(long categoryNo) {
		return postDao.getAmountByCategoryNo(categoryNo);
	}

	public PostVo getPostByUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<PostVo> getPostBasicList(String id) {
		return postDao.getBasicList(id);
	}

	public Long getBasicNo(Long categoryNo) {
		return postDao.getNo(categoryNo);
	}

	
}
