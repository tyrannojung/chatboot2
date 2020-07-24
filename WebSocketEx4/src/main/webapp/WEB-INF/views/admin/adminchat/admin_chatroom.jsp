<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>Lady bug</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/ex/resources/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="/ex/resources/chatcss/NewFile.css" />
</head>

<title>Insert title here</title>
</head>

<body>
<div class="jumbotron text-center" style="margin-bottom:0">
  <h1>Lady bug</h1>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="admin_index">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="messageBox">Message Box</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="messageBoot">messageBoot</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>

<div class="container">
<h3 class=" text-center">Messaging</h3>
<div class="messaging">
  <div class="inbox_msg">
    <div class="inbox_people">
      <div class="headind_srch">
        <div class="recent_heading">
          <h4>답변 모듈</h4>
        </div>
        <div class="srch_bar">
          <div class="stylish-input-group">
            <span class="input-group-addon">
            <button type="button"> <i class="fa fa-plus-square-o" aria-hidden="true"></i> </button>
            </span> </div>
        </div>
      </div>
      
      
      <div class="inbox_chat">
        <div class="chat_list active_chat">
          <div class="chat_people">
            <div class="chat_ib">
              <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
              <p>Test, which is a new approach to have all solutions 
                astrology under one roof.</p>
            </div>
          </div>
        </div>
        <div class="chat_list">
          <div class="chat_people">
            <div class="chat_ib">
              <h5>Sunil Rajput <span class="chat_date">Dec 25</span></h5>
              <p>Test, which is a new approach to have all solutions 
                astrology under one roof.</p>
            </div>
          </div>
        </div>





      </div>
    </div>
    <div class="mesgs">
      <div id="aaaa455" class="msg_history">
      	

 
      </div>
      
      
      <div class="type_msg">
        <div class="input_msg_write">
          <input  id="chatContent" type="text" class="write_msg" placeholder="Type a message" onkeydown="return enter()" />
          <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
        </div>
      </div>
      
      
    </div>
  </div>
</div>
</div>

<script>
var webSocket = new WebSocket("ws://localhost:8090/ex/admin");
		// 운영자에서의 open, close, error는 의미가 없어서 형태만 선언
		webSocket.onopen = function(message) {
		};
		webSocket.onclose = function(message) {
		};
		webSocket.onerror = function(message) {
		};
</script>

<script type="text/javascript">

webSocket.onmessage = function(message) {
	
	
	let node = JSON.parse(message.data);
	
	var fromID = 'admin';
	var toID = node.key;

	if (node.status === "visit") {

	} else if (node.status === "message") {
		
		$('#aaaa455').append(
  				'<div class="incoming_msg">' + 
  				'<div class="incoming_msg_img"> <img src="/ex/resources/chatcss/king.PNG" alt="sunil"></div>'+
  				'<div class="received_msg">' +
  				'<div class="received_withd_msg"><p>' +
  				node.message +
  				'</p>' +
  				'<span class="time_date">'+
  				toID +
  				'</span>' +
  				'</div>' +
  				'</div>' +
  				'</div>'
  				);
	
	} else if (node.status === "bye") {

	}
	

		


};


function addChat(chatName, chatContent, chatTime) {
	$('#msg_history').append(
			'<div class="incoming_msg">' + 
			'<div class="incoming_msg_img"> <img src="/ex/resources/chatcss/king.PNG" alt="sunil"></div>'+
			'<div class="received_msg">' +
			'<div class="received_withd_msg"><p>' +
			chatContent +
			'</p>' +
			'<span class="time_date">'+
			chatName +
			'</span>' +
			'</div>' +
			'</div>' +
			'</div>'	
			);
}  	

function addAdminChat(chatName, chatContent, chatTime) {
	$('#msg_history').append(
			'<div class="outgoing_msg">' +
			'<div class="sent_msg">' +
			chatContent +
			'</p>' +
			'<span class="time_date">'+
			chatName +
			'</span>' +
			'</div>' +
			'</div>'		
	);
		
} 




function sendMessage() {
	// 텍스트 박스의 객체를 가져옴
	let message = document.getElementById("chatContent");

	$('#chatList').append(
			'<div class="outgoing_msg">' +
			'<div class="sent_msg">' +
			message.value +
			'</p>' +
			'<span class="time_date">나</span>' +
			'</div>' +
			'</div>'
				);
	
	message.value = ""; // 텍스트 박스 초기화
	
	webSocket.send(key + "#####" + message);
		
	}


	// 텍스트 박스에서 엔터를 누르면
	function enter() {
		// keyCode 13은 엔터이다.
		if (event.keyCode === 13) {
			// 서버로 메시지 전송
			sendMessage();
			// form에 의해 자동 submit을 막는다.
			return false;
		}
		return true;
	}
	
	

</script>





</body>
</html>