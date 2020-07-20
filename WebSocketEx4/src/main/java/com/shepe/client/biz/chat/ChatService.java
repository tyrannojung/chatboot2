package com.shepe.client.biz.chat;

import java.util.List;

public interface ChatService {
	
	List<CommonChatDTO> getChatListByID(String fromID, String toID, String chatSQ);
	List<CommonChatDTO> getChatListByRecent(String fromID, String toID, int number);
	List<CommonChatDTO> getBox(String userID);
	void submit(String fromID, String toID, String chatContent);
	void readChat(String fromID, String toID);
	int getAllUnreadChat(String userID);
	int getUnreadChat(String fromID, String toID);

}
