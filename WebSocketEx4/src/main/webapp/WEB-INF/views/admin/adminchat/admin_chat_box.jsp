<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.shepe.client.biz.chat.ChatRoomVO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <title>Lady bug</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/ex/resources/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
  <link rel="stylesheet" href="/ex/resources/chatcss/styles.css" />
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
		<table class="table" style="margin: 0 auto;">
			<thead>
				<tr>
					<th><h4>주고받은 상담 목록</h4></th>
				</tr>
			</thead>
			<div style="overflow-y: auto; width: 100%; max-height: 450px;">
				<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; margin: 0 auto;">
					<thead>
						<tr>
							<td style="width: 50px;">No</td><td style="width: 150px;">분류</td><td style="width: 100px;">ID</td><td style="width: 100px;">날짜</td><td style="width: 50px;">Result</td>
						</tr>
					</thead>
					<tbody id="boxTable">
					
						<tr id="boxTr" onclick="location.href='adminex'" style="cursor:hand">
						</tr>
						
					</tbody>
					
				</table>
			</div>
		</table>
		<div class="template" style="display: none">
		<form>
			<!-- 메시지 텍스트 박스 -->
			<input type="text" class="message"
				onkeydown="if(event.keyCode === 13) return false;">
			<!-- 전송 버튼 -->
			<input value="Send" type="button" class="sendBtn">
		</form>
		<br />
		<!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
		<textarea rows="10" cols="50" class="console" disabled="disabled"></textarea>
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
	
	<script>
	// 서버로 부터 메시지가 오면
	webSocket.onmessage = function(message) {
		// 메시지의 구조는 JSON 형태로 만들었다.
		let node = JSON.parse(message.data);
		// 메시지의 status는 유저의 접속 형태이다.
		// visit은 유저가 접속했을 때 알리는 메시지다.
		if (node.status === "AdminCall") {
			
			var clientUserID = node.key;
			
			$.ajax({
				type : "POST",
				url : "admin_chatroomone",
				data : {
					userID : encodeURIComponent(clientUserID)
				},
				dataType: "JSON",
				success : function(result) {
					alert("성");
			        $.each(result, function(index, value){
			        	
			        	$('#boxTable').append("<tr onclick=" + 
								 "\"location.href='adminex'\""+
								        "style='cursor:hand'><td>" + value.consultsq + "</td>"+
										"<td>" + value.chatsubject + "</td>"+
										"<td><h5>" +
										value.userid +
								        "</h5></td>"+
										"<td>"+value.chatdate+"</td>"+
										"<td>O/X</td>"
						);
			        });
				},
				error: function(request, status, error) {
					alert("오류");
				}
			});


			
			// message는 유저가 메시지를 보낼 때 알려주는 메시지이다.
		} else if (node.status === "message") {
			// key로 해당 div영역을 찾는다.
			let $div = $("[data-key='" + node.key + "']");
			// console영역을 찾는다.
			let log = $div.find(".console").val();
			// 아래에 메시지를 추가한다.
			$div.find(".console").val(
					log + "(user) => " + node.message + "\n");
			
			// bye는 유저가 접속을 끊었을 때 알려주는 메시지이다.
		} else if (node.status === "bye") {
			// 해당 키로 div를 찾아서 dom을 제거한다.
			$("[data-key='" + node.key + "']").remove();
		}
	};
	// 전송 버튼을 클릭하면 발생하는 이벤트
	$(document).on("click", ".sendBtn", function() {
		// div 태그를 찾는다.
		let $div = $(this).closest(".float-left");
		// 메시지 텍스트 박스를 찾아서 값을 취득한다.
		let message = $div.find(".message").val();
		// 유저 key를 취득한다.
		let key = $div.data("key");
		// console영역을 찾는다.
		let log = $div.find(".console").val();
		// 아래에 메시지를 추가한다.
		$div.find(".console").val(log + "(me) => " + message + "\n");
		// 텍스트 박스의 값을 초기화 한다.
		$div.find(".message").val("");
		// 웹소켓으로 메시지를 보낸다.
		webSocket.send(key + "#####" + message);
	});
	
	
	
	
	// 텍스트 박스에서 엔터키를 누르면
	$(document).on(
			"keydown",
			".message",
			function() {
				// keyCode 13은 엔터이다.
				if (event.keyCode === 13) {
					// 버튼을 클릭하는 트리거를 발생한다.
					$(this).closest(".float-left").find(".sendBtn")
							.trigger("click");
					// form에 의해 자동 submit을 막는다.
					return false;
				}
				return true;
			});

	</script>

</body>
</html>