package com.shepe.client.biz.chat.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shepe.client.biz.chat.ChatRoomVO;
import com.shepe.client.biz.chat.CommonChatDTO;

@Repository
public class CommonChatDAO {
		
	@Autowired
	private SqlSessionTemplate mybatis;
			
		public List<CommonChatDTO> getChatListByID(String fromID, String toID, String chatSQ) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("toID", toID);
			map.put("fromIDr", toID);
			map.put("toIDr", fromID);
			map.put("chatSQ", Integer.parseInt(chatSQ));
			
			return mybatis.selectList("CommonChatDAO.getChatListByID", map);
		} 
		
		
		public List<CommonChatDTO> getChatListByRecent(String fromID, String toID, int number, int chatroomnum) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("toID", toID);
			map.put("fromIDr", toID);
			map.put("toIDr", fromID);
			map.put("number", number);
			map.put("toIDrr", fromID);
			map.put("fromIDrr", toID);
			map.put("toIDrrr", toID);
			map.put("fromIDrrr", fromID);
			map.put("chatroomnum", chatroomnum);

			return mybatis.selectList("CommonChatDAO.getChatListByRecent", map);
		} 
		
		
		
		public List<CommonChatDTO> getBox(String userID) { // 메세지함

			List<CommonChatDTO> chatList = null;
			chatList = mybatis.selectList("CommonChatDAO.getBox", userID);
			for(int i = 0; i < chatList.size(); i++) {
				CommonChatDTO x = chatList.get(i);
				for(int j = 0; j < chatList.size(); j++) {
					CommonChatDTO y = chatList.get(j);
					if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())) {
						if(x.getChatSQ() < y.getChatSQ()) {
							chatList.remove(x);
							i--;
							break;
						} else {
							chatList.remove(y);
							j--;
						}
					}
				}
			}
			return chatList; // 리스트 반환
		} 
		
		
		public void submit(String fromID, String toID, String chatContent, int chatRoomNum, int chatreaddata) {

			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("toID", toID);
			map.put("chatContent", chatContent);
			map.put("chatRoomNum", chatRoomNum);
			map.put("chatread", chatreaddata);
			mybatis.insert("CommonChatDAO.submit", map);
		} 
		
		
		public void chatBootSubmit(String fromID, String toID, String chatContent, int chatRoomNum) {

			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("toID", toID);
			map.put("chatContent", chatContent);
			map.put("chatRoomNum", chatRoomNum);
			mybatis.insert("CommonChatDAO.chatBootSubmit", map);
		}
		
		public void readChat(String fromID, String toID) {

			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", toID );
			map.put("toID", fromID);
			mybatis.update("CommonChatDAO.readChat", map);
		}
		
		public int getAllUnreadChat(String userID) { // 읽지 않은 메세지를 가져오는 메소드

			return mybatis.selectOne("CommonChatDAO.getAllUnreadChat", userID);
			
		}
		
		public int getUnreadChat(String fromID, String toID) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("toID", toID);
			
			return mybatis.selectOne("CommonChatDAO.getUnreadChat", map);

		}	
		
		
		public int chatListNum(String userID) {
			return mybatis.selectOne("CommonChatDAO.chatListNum", userID);
		}
		
		public void chatRoomSetting(String fromID, int chatRoomNum, String chatRoomSubject, int admincall) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fromID", fromID);
			map.put("chatRoomNum", chatRoomNum);
			map.put("chatRoomSubject", chatRoomSubject);
			map.put("admincall", admincall);
			
			if(admincall == 1) {
				mybatis.update("CommonChatDAO.chatRoomSetting", map);
			} else {
				mybatis.update("CommonChatDAO.chatRoomSettingboot", map);
			}
			
		}
		
		public List<ChatRoomVO> chatroomlist(String userID) {
			return mybatis.selectList("CommonChatDAO.chatroomlist", userID);
		}
		
		public ChatRoomVO admin_chatroomone(String userID) {
			return mybatis.selectOne("CommonChatDAO.admin_chatroomone", userID);
		}
		
		
		public List<ChatRoomVO> admin_chatroomList() {
			return mybatis.selectList("CommonChatDAO.admin_chatroomList");
		}
		
		
		public void updateOk(int getConsultsq) {
			mybatis.update("CommonChatDAO.updateOk", getConsultsq);
		}
		
		public int chatCompleteCheck(String userID) {
			return mybatis.selectOne("CommonChatDAO.chatCompleteCheck", userID);
		}
		
		public void chatAllComplete(String userID) {
			mybatis.update("CommonChatDAO.chatAllComplete", userID);
		}
		
		public int selectChatroomnum(String userID) {
			return mybatis.selectOne("CommonChatDAO.selectChatroomnum", userID);
		}

		
	}