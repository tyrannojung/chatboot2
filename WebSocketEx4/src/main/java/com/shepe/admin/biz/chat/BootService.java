package com.shepe.admin.biz.chat;

public interface BootService {
	
	void startBoot(String startBootContent);
	void selectCountBoot(String selectNum);
	void qaBoot(String boot_question, String boot_answer, String boot_choice);
	BootVO BootContent();

}
