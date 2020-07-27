package com.shepe.admin.biz.chat.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shepe.admin.biz.chat.CounselingService;
import com.shepe.admin.biz.chat.CounselingVO;

@Service
public class CounselingImpl implements CounselingService {
	
	@Autowired
	private CounselingDAO counselingdao;
	
	public void counselingAddAction(CounselingVO vo) {
		
		counselingdao.counselingAddAction(vo);
	}

}
