package com.shepe.client.join;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void join(JoinVO vo) {

		mybatis.update("Join.join", vo);
	}
	
	public int login(JoinVO vo) {

		return mybatis.selectOne("Join.login", vo);
	}
}
