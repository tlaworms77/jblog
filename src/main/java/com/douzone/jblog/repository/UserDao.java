package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public long join(UserVo userVo) {
		sqlSession.insert("user.insert", userVo);
		return sqlSession.selectOne("user.getLastNo");
	}

	public UserVo getLoginUser(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne("user.getByIdAndPassword", map);
	}

	public long getUserNo(String id) {
		return sqlSession.selectOne("user.getUserNo", id);
	}

	public UserVo checkUserId(String id) {
		return sqlSession.selectOne("user.checkById", id);
	}

}
