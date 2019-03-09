package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	public int addCategory(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert", categoryVo);
	}

	public List<CategoryVo> getNames(long no) {
		return sqlSession.selectList("category.getListByNo", no);
	}

	public CategoryVo getLastInsert() {
		return sqlSession.selectOne("category.getLastInsert");
	}

	public List<CategoryVo> getList(String id) {
		return sqlSession.selectList("category.getList", id);
	}

	public int deleteByNo(long categoryNo) {
		return sqlSession.delete("category.deleteByNo", categoryNo);
	}

	public List<CategoryVo> getListById(String id) {
		return sqlSession.selectList("category.getListById", id);
	}

	public Long getNoById(String id) {
		return sqlSession.selectOne("category.getNo", id);
	}

	public int defaultCategory(long userNo) {
		return sqlSession.insert("category.insertDefault", userNo);
	}

	public long getInsertLastNo() {
		return sqlSession.selectOne("category.getLastNo");
	}

	public int getRowCount(String id) {
		return sqlSession.selectOne("category.getRowCountByNo", id);
	}
}
