package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	public int join(long userNo) {
		return sqlSession.insert("blog.insert", userNo);
	}

	public int update(BlogVo blogVo) {
		return sqlSession.update("blog.modify", blogVo);
	}

	public BlogVo getBlogVo(String id) {
		return sqlSession.selectOne("blog.getBlogVoById", id);
	}
	
	public BlogVo getBlogVo(long no) {
		return sqlSession.selectOne("blog.getBlogVoByNo", no);
	}
}
