package com.shepe.admin.biz.chat.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shepe.admin.biz.chat.CounselingVO;

@Repository
public class CounselingDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void counselingAddAction(CounselingVO vo) {

		mybatis.insert("Counseling.counselingAdd", vo);
	} 

}
