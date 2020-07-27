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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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
          <h4>상담내역</h4>
        </div>
        <div class="srch_bar">
          <div class="stylish-input-group">
            <span class="input-group-addon">
            	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#counseling_history"><i class="fa fa-plus-square-o"></i></button>
            </span> 
          </div>
        </div>
      </div>
      
      
      
      <!-- 모달 -->
      
      
       		<div class="modal" id="counseling_history">
       			<form method="post" id="history" name="counseling">
			    <div class="modal-dialog">
			      <div class="modal-content">
			        <div class="modal-header">
			          <h4 class="modal-title">상담이력작성하기</h4>
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			        </div>
			        
			        <!-- Modal body -->
			        <div class="modal-body">
			          
			          <label for="sel1">상담주제:</label>
			          <select class="form-control" id="sel1" name="h_subject">
			            <c:forEach var="i" begin="0" end="${BootContentt.butNum - 1}"> 
			            		<c:set var="fname1" value="q${i}" />
								<option id="a${i}" value="${BootContentt[fname1]}">${BootContentt[fname1]}</option>
						</c:forEach>
			          </select>
			          <div class="form-group">
			            <label for="comment">상담이력:</label>
			            	<textarea class="form-control" rows="5" id="comment" name="h_content"></textarea>
			         </div>
			         
			         <input type="hidden" name="h_userid" value="${chatroomVO.userid}">
			         <input type="hidden" name="h_adminid" value="admin">
			         <input type="hidden" name="consultsq" value="${chatroomVO.consultsq}">
			         
			         
			         <div class="form-check custom-control-inline">
				      <label class="form-check-label" for="radio1">
				        <input type="radio" class="form-check-input" id="radio1" name="h_ok" value="0" checked>상담진행중
				      </label>
				    </div>
				    <div class="form-check custom-control-inline">
				      <label class="form-check-label" for="radio2">
				        <input type="radio" class="form-check-input" id="radio2" name="h_ok" value="1">상담완료
				      </label>
    				</div>
			        </div>
			        
			        <div class="modal-footer">
			          <button type="submit" class="btn btn-primary" onclick="submitcheck();">작성</button>
			          <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			        </div>
			      </div>
			    </div>
			    </form>
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
      <div id="msg_historyid" class="msg_history">
      	<c:forEach var="history" items="${getChatroomList}">
	      	
	      	<c:choose>
			    <c:when test="${history.fromID ne 'admin'}">
				    <div class="incoming_msg">
						<div class="incoming_msg_img">
							<img src="/ex/resources/chatcss/king.PNG" alt="sunil">
						</div>
						<div class="received_msg">
							<div class="received_withd_msg">
								<p>
									${history.chatContent}
								</p>
								<span class="time_date">
									${history.fromID}
								</span>
							</div>
						</div>
					</div>
			    </c:when>
			    <c:otherwise>
			      	<div class="outgoing_msg">
						<div class="sent_msg">
							<p>
								${history.chatContent}
							</p>
							<span class="time_date">
								${history.fromID}
							</span>
						</div>
					</div>
			    </c:otherwise>
			</c:choose>
		</c:forEach>
      </div>
      
      
      <div class="type_msg">
        <div class="input_msg_write">
          <input  id="chatContent" type="text" class="write_msg" placeholder="Type a message" onkeydown="return enter()" />
          <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true" onclick="sendMessage()"></i></button>
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

	if (node.status === "message") {
		
		$('#msg_historyid').append(
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
		$('#msg_historyid').scrollTop($('#msg_historyid')[0].scrollHeight);
	
	} else if (node.status === "bye") {

	}
};



function sendMessage() {
	// 텍스트 박스의 객체를 가져옴
	let message = document.getElementById("chatContent");
	let key = '${chatroomVO.userid}';
	$('#msg_historyid').append(
			'<div class="outgoing_msg">' +
			'<div class="sent_msg"><p>' +
			message.value +
			'</p>' +
			'<span class="time_date">admin</span>' +
			'</div>' +
			'</div>'
				);
	$('#msg_historyid').scrollTop($('#msg_historyid')[0].scrollHeight);
		webSocket.send(key + "#####" + message.value);
		message.value = ""; // 텍스트 박스 초기화
	}


	// 텍스트 박스에서 엔터를 누르면
	function enter() {
		// keyCode 13은 엔터이다.
		if (event.keyCode === 13) {
			sendMessage();
			// form에 의해 자동 submit을 막는다.
			return false;
		}
		return true;
	}
	
	$(document).ready(function() {
		$('#msg_historyid').scrollTop($('#msg_historyid')[0].scrollHeight);
	});
	
</script>
<script>
$(window).on("beforeunload", function(){

	let message = "Adminlogout입니다.";
	
	let key = '${chatroomVO.userid}';
	
	webSocket.send(key + "#####" + message);
});

$(document).keydown(function (e) {
    
    if (e.which === 116) {
        if (typeof event == "object") {
            event.keyCode = 0;
        }
        return false;
    } else if (e.which === 82 && e.ctrlKey) {
        return false;
    }
}); 


function submitcheck(){
	var formData = $('#history').serialize();
	
        $.ajax({
            cache : false,
            url : "counseling_history",
            type : 'POST', 
            data : formData
        });
        return false;
}


</script>


</body>
</html>