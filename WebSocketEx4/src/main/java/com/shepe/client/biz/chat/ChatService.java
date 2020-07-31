package com.shepe.client.biz.chat;

import java.util.List;

public interface ChatService {
	
	List<CommonChatDTO> getChatListByID(String fromID, String toID, String chatSQ);
	List<CommonChatDTO> getChatListByRecent(String fromID, String toID, int number, int chatroomnum);
	List<CommonChatDTO> getBox(String userID);
	void submit(String fromID, String toID, String chatContent, int chatRoomNum, int chatreaddata);
	void readChat(String fromID, String toID);
	int getAllUnreadChat(String userID);
	int getUnreadChat(String fromID, String toID);
	int chatListNum(String userID);
	void chatRoomSetting(String fromID, int chatRoomNum, String chatRoomSubject, int admincall);
	List<ChatRoomVO> chatroomlist(String userID);
	ChatRoomVO admin_chatroomone(String userID);
	List<ChatRoomVO> admin_chatroomList();
	
	void updateOk(int getConsultsq);
	
	int chatCompleteCheck(String userID);
	
	void chatAllComplete(String userID);
	
	int selectChatroomnum(String userID);
	
	void chatBootSubmit(String fromID, String toID, String chatContent, int chatRoomNum);
}
