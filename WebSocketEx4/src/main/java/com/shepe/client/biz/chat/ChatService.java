package com.shepe.client.biz.chat;

import java.util.List;

public interface ChatService {
	
	List<CommonChatDTO> getChatListByID(String fromID, String toID, String chatSQ);
	List<CommonChatDTO> getChatListByRecent(String fromID, String toID, int number);
	List<CommonChatDTO> getBox(String userID);
	void submit(String fromID, String toID, String chatContent, int chatRoomNum);
	void readChat(String fromID, String toID);
	int getAllUnreadChat(String userID);
	int getUnreadChat(String fromID, String toID);
	int chatListNum(String userID);
	void chatRoomSetting(String fromID, int chatRoomNum, String chatRoomSubject);
	List<ChatRoomVO> chatroomlist(String userID);
}
