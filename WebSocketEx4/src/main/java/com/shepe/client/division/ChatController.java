package com.shepe.client.division;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shepe.admin.biz.chat.BootService;
import com.shepe.admin.biz.chat.BootVO;
import com.shepe.client.biz.chat.ChatEncoding;
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
	
	
	
	@RequestMapping("/admin_index")
	public String adminIndex() {
		return "/admin/adminchat/admin_index";
	}
	
	@RequestMapping("/messageBox")
	public String messageBox() {
		return "/admin/adminchat/admin_chat_box";
	}
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		BootVO vo = bootservice.BootContent();
		
		session.setAttribute("BootContent", vo);
		System.out.println(vo.toString());
		
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
			session.setAttribute("userID", vo.getUserID());
			return "redirect:/index";
			
		} else {
			request.setAttribute("usercheck", "idpwno");
			return "/client/join/login";
		}
		
	}
	
	@RequestMapping("/messageBoot")
	public String messageBoot() {
		return "/admin/adminchat/messageBoot";
	}
	
	@RequestMapping("/startBoot")
	public void startBoot(@RequestParam String startBootContent) throws IOException {
		startBootContent = URLDecoder.decode(startBootContent, "UTF-8");
		bootservice.startBoot(startBootContent);
	}
	
	@RequestMapping("/selectCountBoot")
	public void selectCountBoot(@RequestParam String selectNum) throws IOException {
		selectNum = URLDecoder.decode(selectNum, "UTF-8");
		bootservice.selectCountBoot(selectNum);
	}
		
	
	@RequestMapping(value="/qaBoot",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public void qaBoot(@RequestParam String boot_question, @RequestParam String boot_answer, @RequestParam String boot_choice) throws IOException {
		
		boot_question = chatencoding.encoding(boot_question);
		boot_answer = chatencoding.encoding(boot_answer);
		boot_choice = chatencoding.encoding(boot_choice);
		
		System.out.println(boot_question + "," + boot_answer + "," + boot_choice);
		bootservice.qaBoot(boot_question, boot_answer, boot_choice);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/chatex")
	public String chatex() {
		return "/client/division/chat/chat";
	}

	@RequestMapping("/adminex")
	public String adminex() {
		return "/admin/adminchat/admin";
	}

	
	@RequestMapping("/chat")
	public String chat() {
		
		return "/client/division/chat/chatModule_chat";
	}
	
	@RequestMapping("/chatindex")
	public String chatindex() {

		return "/client/division/chat/chatModule_index";
	}
	
	@RequestMapping(value="/chatList",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public String chatList(@RequestParam String fromID, @RequestParam String toID, @RequestParam String listType)  throws IOException {

		if (listType.equals("ten")) return getTen(URLDecoder.decode(fromID,"UTF-8"), URLDecoder.decode(toID, "UTF-8")); //from id, to id  는 한글로 작성되어있을수도 있기때문에 디코더를 사용해야한다.
		else {
			try {
				return getID(URLDecoder.decode(fromID,"UTF-8"), URLDecoder.decode(toID, "UTF-8"), listType);
				
			} catch (Exception e) {
				return "";
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/chatSubmit",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	public void chatSubmit(@RequestParam String fromID, @RequestParam String toID, @RequestParam String chatContent) throws IOException {
		System.out.println("여기도안타나?");
		fromID = URLDecoder.decode(fromID, "UTF-8");
		toID = URLDecoder.decode(toID, "UTF-8");
		chatContent = URLDecoder.decode(chatContent, "UTF-8");
		chatService.submit(fromID, toID, chatContent);	

	}
	
	@RequestMapping(value="/chatUnread",produces = "application/text; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public String chatUnread(@RequestParam String userID) throws IOException {

		
		if(userID == null || userID.equals("")) {
			return "0";
		} else {
			userID = URLDecoder.decode(userID, "UTF-8");
			return chatService.getAllUnreadChat(userID) + "";
//			response.getWriter().write(chatDAO.getAllUnreadChat(userID) + ""); //  특정한 사용자가 현재까지 읽지않은 메세지의 개수를 클라이언트한테 출력해준다.
		}
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@ResponseBody
	public String getTen(String fromID, String toID) {// 최근 메세지 10개까지인데 아래 숫자고침으로써 100개로 변경
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		List<CommonChatDTO> chatList = chatService.getChatListByRecent(fromID, toID, 100);
		if(chatList.size() == 0) return "";
		for(int i = 0; i <chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatencoding.encoding(chatList.get(i).getFromID()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getToID()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getChatContent()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getChatTime()) + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatSQ() + "\"}");
		System.out.println("1."+result.toString());
		chatService.readChat(fromID, toID); // 반환직전에 모든 채팅을 다 읽었다고 알려준다. 
		return result.toString();
	}
	
	@ResponseBody
	public String getID(String fromID, String toID, String chatSQ) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		List<CommonChatDTO> chatList = chatService.getChatListByID(fromID, toID, chatSQ);
		if(chatList.size() == 0) return "";
		for(int i = 0; i <chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatencoding.encoding(chatList.get(i).getFromID()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getToID()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getChatContent()) + "\"},");
			result.append("{\"value\": \"" + chatencoding.encoding(chatList.get(i).getChatTime()) + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getChatSQ() + "\"}");
		System.out.println("2."+result.toString());
		chatService.readChat(fromID, toID); // 반환직전에 모든 채팅을 다 읽었다고 알려준다. 
		return result.toString();
	}
}
