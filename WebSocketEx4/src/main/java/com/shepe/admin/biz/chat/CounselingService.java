package com.shepe.admin.biz.chat;

import java.util.List;

public interface CounselingService {
	
	void counselingAddAction(CounselingVO vo);
	
	List<CounselingVO> getCounselingList(int consultsq);
	
	CounselingVO admin_counselingone(int consultnum);
	
}
