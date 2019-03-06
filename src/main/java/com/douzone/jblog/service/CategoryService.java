package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

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

	public List<CategoryVo> getList() {
		return categoryDao.getList();
	}

	public boolean deleteRow(long no) {
		return categoryDao.deleteByNo(no) == 1;
	}

	public List<CategoryVo> getListById(String id) {
		return categoryDao.getListById(id);
	}

	public Long getBasicNo(String id) {
		return categoryDao.getNoById(id);
	}

}
