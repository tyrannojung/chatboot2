package com.shepe.client.biz.chat;

public class CommonChatDTO {
		
		int chatSQ;
		String fromID;
		String toID;
		String chatContent;
		String chatTime;
		
		public int getChatSQ() {
			return chatSQ;
		}
		public void setChatSQ(int chatSQ) {
			this.chatSQ = chatSQ;
		}
		public String getFromID() {
			return fromID;
		}
		public void setFromID(String fromID) {
			this.fromID = fromID;
		}
		public String getToID() {
			return toID;
		}
		public void setToID(String toID) {
			this.toID = toID;
		}
		public String getChatContent() {
			return chatContent;
		}
		public void setChatContent(String chatContent) {
			this.chatContent = chatContent;
		}
		public String getChatTime() {
			return chatTime;
		}
		public void setChatTime(String chatTime) {
			this.chatTime = chatTime;
		}

	}
