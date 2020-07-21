package com.shepe.client.soket;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadsocket/{userID}")
public class BroadSocket {

	private static Map<String, Session> clientMap = Collections.synchronizedMap(new HashMap<String, Session>());

	public <K, V> K getKey(Map<K, V> map, V value) {
		 
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
	}
	
// browser에서 웹 소켓으로 접속하면 호출되는 함수
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("userID") String userID) {
		System.out.println("오픈은하나? ?");
		
			clientMap.put(userID, userSession);
			
	// 운영자 Client에 유저가 접속한 것을 알린다.
			Admin.visit(userID);
	}

// browser에서 웹 소켓을 통해 메시지가 오면 호출되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
// Session으로 접속 리스트에서 User 클래스를 탐색
		String key = getKey(clientMap, userSession);
		Admin.sendMessage(key, message);	
	}

// 운영자 client가 유저에게 메시지를 보내는 함수
	public static void sendMessage(String key, String message) {
// key로 접속 리스트에서 User 클래스를 탐색

		Session stream = clientMap.get(key);
// 접속 리스트에 User가 있으면(당연히 있다. 없으면 버그..)
			try {
// 유저 Session으로 socket을 취득한 후 메시지를 전송한다.
				stream.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

// WebSocket이 종료가 되면, 종료 버튼이 없기 때문에 유저 브라우저가 닫히면 발생한다.
	@OnClose
	public void handleClose(Session userSession) {
// Session으로 접속 리스트에서 User 클래스를 탐색
		String key = getKey(clientMap, userSession);

// 운영자 Client에 유저 key로 접속 종료를 알린다.
			Admin.bye(key);
			System.out.println("여기타?");
// 위 유저 접속 리스트에서 유저를 삭제한다.
			clientMap.remove(key);
	}
	
	

// 유저간의 접속 리스트의 키를 취득하려고 할때.
	public static String[] getUserKeys() {
		
		String[] ret = new String[clientMap.size()];
		System.out.println(clientMap.size());
		
		int i=0;
		for (String value : clientMap.keySet()) {
		    ret[i] = value;
		    i++ ;
		}
		System.out.println(Arrays.toString(ret));
// 반환할 String 배열을 선언한다.
		return ret;
	}
}
