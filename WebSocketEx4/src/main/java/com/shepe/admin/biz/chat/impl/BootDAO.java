package com.shepe.admin.biz.chat.impl;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shepe.admin.biz.chat.BootVO;

@Repository
public class BootDAO {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void startBoot(String startBootContent) {

		mybatis.update("Boot.startBoot", startBootContent);
	}
	
	public void selectCountBoot(String selectNum) {
		System.out.println(selectNum);
		int Num = Integer.parseInt(selectNum);

		mybatis.update("Boot.selectCountBoot", Num);
	}	

	public void qaBoot(String boot_question, String boot_answer, String boot_choice) {

		int bootchoice = Integer.parseInt(boot_choice);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("q", boot_question);
		map.put("a", boot_answer);

		switch (bootchoice) {
		
		case 0: 
			System.out.println("0입니다.");
			mybatis.update("Boot.qaBoot0", map);
			break;
		case 1: 
			System.out.println("1입니다.");
			mybatis.update("Boot.qaBoot1", map);
			break;
		case 2: 
			System.out.println("2입니다.");
			mybatis.update("Boot.qaBoot2", map);
			break;
		case 3: 
			System.out.println("3입니다.");
			mybatis.update("Boot.qaBoot3", map);
			break;
		}
	}
	
	public BootVO BootContent() {

		return mybatis.selectOne("Boot.BootContent");
	}
	
	
}
