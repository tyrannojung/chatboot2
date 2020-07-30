package com.shepe.client.division;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shepe.admin.biz.chat.BootService;
import com.shepe.admin.biz.chat.BootVO;

import com.shepe.client.biz.chat.ChatEncoding;
import com.shepe.client.biz.chat.ChatRoomVO;
import com.shepe.client.biz.chat.ChatService;
import com.shepe.client.biz.chat.CommonChatDTO;
import com.shepe.client.join.JoinDAO;
import com.shepe.client.join.JoinVO;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatEncoding chatencoding;
	
	@Autowired
	private JoinDAO joindao;
	
	@Autowired
	private BootService bootservice;
	

	@ResponseBody
	@RequestMapping("/admin_chatroomone")
	public ChatRoomVO admin_chatroomone(@RequestParam String userID) {
		
		return chatService.admin_chatroomone(userID);
	}
	
	@ResponseBody
	@RequestMapping("/admin_chatroomList")
	public List<ChatRoomVO> admin_chatroomList() {
		return chatService.admin_chatroomList();
	}
	
	@ResponseBody
	@RequestMapping("/chatCompleteCheck")
	public int chatCompleteCheck(@RequestParam String userID) {
		return chatService.chatCompleteCheck(userID);
		
	}

	@ResponseBody
	@RequestMapping("/chatAllComplete")
	public void chatAllComplete(@RequestParam String userID) {
		
		chatService.chatAllComplete(userID);
	}
	
	
	@ResponseBody
	@RequestMapping("/selectChatroomnum")
	public int selectChatroomnum(@RequestParam String userID, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int a = chatService.selectChatroomnum(userID);

			session.setAttribute("consultRoomNum", a);

		return a;
	}
		
	@RequestMapping("/index")
	public String index() {
		
		return "redirect:/";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "/client/join/join";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/client/join/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.invalidate();
		return "redirect:/index";
	}
	
	
	@RequestMapping("/userJoinAction")
	public String userJoin(JoinVO vo) {
		joindao.join(vo);
		return "redirect:/index";
	}
	
	@RequestMapping("/userLoginAction")
	public String userLoginAction(JoinVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int check = 0;
		
		check = joindao.login(vo);
		
		if(check == 1) {
			BootVO bo = bootservice.BootContent();
			
			session.setAttribute("BootContent", bo);
			session.setAttribute("userID", vo.getUserID());
			return "redirect:/index";
			
		} else {
			request.setAttribute("usercheck", "idpwno");
			return "/client/join/login";
		}
	}
		
	
	
	@RequestMapping("/chat")
	public String chat(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		int a = chatService.chatListNum(userID);
		session.setAttribute("consultNum", a);

		return "/client/division/chat/chatModule_chat";
	}
	

	
	@RequestMapping("/chatindex")
	public String chatindex() {

		return "/client/division/chat/chatModule_index";
	}
	
	@RequestMapping("/chatlist")
	public String chatlist(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		session.setAttribute("ChatRoomList", chatService.chatroomlist(userID));

		return "/client/division/chat/chatModule_chatbox";
	}
	
	@RequestMapping("/chatroomlistpage")
	public String chatroomlistpage() {

		return "/client/division/chat/chatModule_chatroom";
	}
	
	
	@RequestMapping(value="/chatRoomSetting", produces = "application/text; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public void chatRoomSetting(@RequestParam String fromID, @RequestParam int chatRoomNum, @RequestParam String chatRoomSubject, @RequestParam(value="admincall", required=false) String admincall) throws IOException {
		
		if(admincall != null) {
			
			chatRoomSubject = URLDecoder.decode(chatRoomSubject, "UTF-8");
			fromID = URLDecoder.decode(fromID, "UTF-8");
			chatService.chatRoomSetting(fromID,chatRoomNum,chatRoomSubject,1);
			
		} else {
			chatRoomSubject = URLDecoder.decode(chatRoomSubject, "UTF-8");
			fromID = URLDecoder.decode(fromID, "UTF-8");
			chatService.chatRoomSetting(fromID,chatRoomNum,chatRoomSubject,0);
		}
	}
	
	@RequestMapping(value="/chatList",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public String chatList(@RequestParam String fromID, @RequestParam String toID, @RequestParam String listType, @RequestParam int chatroomnum)  throws IOException {

		return getTen(URLDecoder.decode(fromID,"UTF-8"), URLDecoder.decode(toID, "UTF-8"), chatroomnum); //from id, to id  는 한글로 작성되어있을수도 있기때문에 디코더를 사용해야한다.
	
	}
	
	@ResponseBody
	@RequestMapping(value="/chatSubmit",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public void chatSubmit(@RequestParam String fromID, @RequestParam String toID, @RequestParam String chatContent, @RequestParam int chatRoomNum) throws IOException {
		
		fromID = URLDecoder.decode(fromID, "UTF-8");
		toID = URLDecoder.decode(toID, "UTF-8");
		chatContent = URLDecoder.decode(chatContent, "UTF-8");
		chatService.submit(fromID, toID, chatContent,chatRoomNum);	

	}
	
	@ResponseBody
	@RequestMapping(value="/chatBootSubmit",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public void chatBootSubmit(@RequestParam String fromID, @RequestParam String toID, @RequestParam String chatContent, @RequestParam int chatRoomNum) throws IOException {
		
		fromID = URLDecoder.decode(fromID, "UTF-8");
		toID = URLDecoder.decode(toID, "UTF-8");
		chatContent = URLDecoder.decode(chatContent, "UTF-8");
		chatService.chatBootSubmit(fromID, toID, chatContent,chatRoomNum);	

	}
	
	@RequestMapping(value="/chatUnread",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public String chatUnread(@RequestParam String userID) throws IOException {

		if(userID == null || userID.equals("")) {
			return "0";
		} else {
			userID = URLDecoder.decode(userID, "UTF-8");
			return chatService.getAllUnreadChat(userID) + "";
		}
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@ResponseBody
	public String getTen(String fromID, String toID, int chatroomnum) {// 최근 메세지 10개까지인데 아래 숫자고침으로써 100개로 변경
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		List<CommonChatDTO> chatList = chatService.getChatListByRecent(fromID, toID, 100, chatroomnum);
		if(chatList.size() == 0) return "";
		for(int i = 0; i <chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatencoding.encoding(chatList.get(i).getFromID()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getToID()) + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getChatTime()) + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatRead() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatSQ() + "\"}");
		chatService.readChat(fromID, toID); // 반환직전에 모든 채팅을 다 읽었다고 알려준다. 
		return result.toString();
		
	}
}
