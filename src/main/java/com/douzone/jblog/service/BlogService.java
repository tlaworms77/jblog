package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	public boolean update(BlogVo blogVo) {
		boolean result = 1 == blogDao.update(blogVo);
		System.out.println("BlogService's update result : " + result);
		return result;
	}

	public BlogVo getBlogInfo(long userNo) {
		return blogDao.getBlogVo(userNo);
	}

	public Map<String, Object> getAll(String id, Long categoryNo, Long postNo) {
		BlogVo blogVo = blogDao.getBlogVo(id);
		List<CategoryVo> categorylist = categoryDao.getListById(id);
		List<PostVo> postlist = postDao.getPostList(categoryNo);
		PostVo postVo = postDao.getPost(postNo);
		
		System.out.println(blogVo);
		System.out.println(categorylist);
		System.out.println(postlist);
		System.out.println(postVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogVo", blogVo);
		map.put("categorylist", categorylist);
		map.put("postlist", postlist);
		map.put("postVo", postVo);
		
		return map;
	}
	
}
