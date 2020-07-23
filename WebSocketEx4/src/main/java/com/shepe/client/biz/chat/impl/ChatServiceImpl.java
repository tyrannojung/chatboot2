package com.shepe.client.biz.chat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shepe.client.biz.chat.ChatRoomVO;
import com.shepe.client.biz.chat.ChatService;
import com.shepe.client.biz.chat.CommonChatDTO;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private CommonChatDAO commonChatDAO;
	
	public List<CommonChatDTO> getChatListByID(String fromID, String toID, String chatSQ) {
		return commonChatDAO.getChatListByID(fromID, toID, chatSQ);
	}
	public List<CommonChatDTO> getChatListByRecent(String fromID, String toID, int number, int chatroomnum) {
		return commonChatDAO.getChatListByRecent(fromID, toID, number, chatroomnum);
		
	}
	public List<CommonChatDTO> getBox(String userID) {
		return commonChatDAO.getBox(userID);
	}
	public void submit(String fromID, String toID, String chatContent, int chatRoomNum) {
		commonChatDAO.submit(fromID, toID, chatContent, chatRoomNum);
	}
	public void readChat(String fromID, String toID) {
		commonChatDAO.readChat(fromID, toID);
	}
	public int getAllUnreadChat(String userID) {
		return commonChatDAO.getAllUnreadChat(userID);
	}
	public int getUnreadChat(String fromID, String toID) {
		return commonChatDAO.getUnreadChat(fromID, toID);
	}
	
	public int chatListNum(String userID) {
		return commonChatDAO.chatListNum(userID);
	}
	
	public void chatRoomSetting(String fromID, int chatRoomNum, String chatRoomSubject) {
		commonChatDAO.chatRoomSetting(fromID,chatRoomNum,chatRoomSubject);
	}
	
	public List<ChatRoomVO> chatroomlist(String userID) {
		return commonChatDAO.chatroomlist(userID);
	}

}
