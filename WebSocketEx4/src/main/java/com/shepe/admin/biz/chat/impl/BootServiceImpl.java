package com.shepe.admin.biz.chat.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shepe.admin.biz.chat.BootService;
import com.shepe.admin.biz.chat.BootVO;

@Service
public class BootServiceImpl implements BootService {
	
	@Autowired
	private BootDAO bootdao;
	
	public void startBoot(String startBootContent) {
		bootdao.startBoot(startBootContent);
		
	}
	
	public void selectCountBoot(String selectNum) {
		bootdao.selectCountBoot(selectNum);
		
	}
	
	public void qaBoot(String boot_question, String boot_answer, String boot_choice) {
		bootdao.qaBoot(boot_question, boot_answer, boot_choice);
	}
	
	public BootVO BootContent() {
		return bootdao.BootContent();
	}
}
