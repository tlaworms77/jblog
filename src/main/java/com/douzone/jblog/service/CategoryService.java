package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	public void add(CategoryVo categoryVo) {
		boolean result = 1 == categoryDao.addCategory(categoryVo);
		if(!result)
			return ;
		System.out.println("CategoryService's add result : " + result);
	}

	public List<CategoryVo> getNames(long no) {
		return categoryDao.getNames(no);
	}

	public CategoryVo getLastInsert() {
		return categoryDao.getLastInsert();
	}

	public List<CategoryVo> getList(String id) {
		return categoryDao.getList(id);
	}

	public boolean deleteRow(long categoryNo) {
		return categoryDao.deleteByNo(categoryNo) == 1;
	}

	public List<CategoryVo> getListById(String id) {
		return categoryDao.getListById(id);
	}

	public Long getBasicNo(String id) {
		return categoryDao.getNoById(id);
	}

	public boolean getRowCount(String id) {
		return (categoryDao.getRowCount(id)<=1) ? false : true;
	}

	public boolean getCategoryIsChild(long categoryNo) {
		return (postDao.getChildCount(categoryNo) > 0) ? true : false;
	}

}
