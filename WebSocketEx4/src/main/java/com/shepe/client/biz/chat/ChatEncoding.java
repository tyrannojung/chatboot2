package com.shepe.client.biz.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatEncoding {
	
	public String encoding(String content) {
		String str = content;
		try {
			str = str.replaceAll("<", "&lt");
			str = str.replaceAll(">", "&gt");
			str = str.replaceAll("\"", "&#34");
			str = str.replaceAll("\'", "&#039");
			str = str.replaceAll("\r\n", "<br>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
