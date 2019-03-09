package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	public int write(PostVo postVo) {
		return sqlSession.insert("post.insert", postVo);
	}

	public int getAmountByCategoryNo(long categoryNo) {
		return sqlSession.selectOne("post.getAmountByCategoryNo", categoryNo);
	}

//	public List<PostVo> getPostList(String id, Optional<Long> categoryNo) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", id);
//		map.put("categoryNo", categoryNo);
//		return sqlSession.selectList("post.getPostList", map);
//	}

	public List<PostVo> getBasicList(String id) {
		return sqlSession.selectList("post.getBasicList", id);
	}

	public Long getNo(Long categoryNo) {
		return sqlSession.selectOne("post.getNoByCategoryNo", categoryNo);
	}

	public List<PostVo> getPostList(Long categoryNo) {
		return sqlSession.selectList("post.getList", categoryNo);
	}

	public PostVo getPost(Long postNo) {
		return sqlSession.selectOne("post.getPost", postNo);
	}

	public int defaultPost(long categoryNo) {
		return sqlSession.insert("post.insertDefault", categoryNo);
	}

	public int getChildCount(long categoryNo) {
		return sqlSession.selectOne("post.getChildCount", categoryNo);
	}
	
}
