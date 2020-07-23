<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}

int consultNum = (int)(session.getAttribute("consultRoomNum")) + 1;

	String toID = "admin";

%>
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

<div id ="change" style="background-color: #f4f5f7; width: 304px; height: 504px; box-shadow: 0 30px 60px rgba(50, 50, 93, 0.25);overflow-y: auto; box-shadow: 0 30px 60px rgba(50, 50, 93, 0.25);">
	<div class="header-wrapper" style="position: absolute;">
				<header class="header" style="margin-bottom: 0px;">
					<div class="header__header-column">
						<div class="header__header-column">
						<h1 class="header__title">Consult Box</h1>
					</div>
					</div>
					<div class="header__header-column">
						<span class="header__icon"> <i class="fas fa-cog"></i>
						</span>
					</div>
				</header>
			</div>
    <div>
	<main class="friends">
        <ul class="friends__list">
        <li class="chat__timestamp">&nbsp;</li>
        <li class="chat__timestamp">&nbsp;</li>
        <li class="chat__timestamp">&nbsp;</li>
        <li class="chat__timestamp">&nbsp;</li>
        <li class="chat__timestamp">&nbsp;</li>
        
        
        <div>
        <c:forEach var="item" items="${ChatRoomList}"  varStatus="status"> 
			<a href='javascript:void(0);' class="nav__list-link" onclick="button_chatroom('${status.count}')">
				<li class="friends__friend friend friend--lg">
		          <div class="friend__column">
		              <img src="/ex/resources/chatcss/consultbox.png" class="friend__avatar"/>
		            <div class="friend__content">
		              <span class="friend__name">
		              <c:set var="complete" value="${item.chatcomplete}"/>
			            <c:choose>
						    <c:when test="${complete eq '0'}">
						        ${status.count}.<c:out value="상담진행중"/>
						    </c:when>
						    <c:otherwise>
						      	<c:out value="상담완료"/>
						    </c:otherwise>
						</c:choose>
		              </span>
		              <span class="friend__status">
		                	<c:out value="${item.chatsubject}"/>
		              </span>
		            </div>
		          </div>
		          <div class="friend__column">
		              <span style="font-size: 12px;">
		                	<c:out value="${item.chatdate}"/>
		              </span>
		          </div>
		        </li>
	        </a>
		</c:forEach>
        </div>
        
        
        
        
      </ul>
      </main>
     </div>
<nav class="nav" style="display: inline-block; position: absolute;">
      <ul class="nav__list">
        <li class="nav__list-item">
          <a href='javascript:void(0);' class="nav__list-link">
            <i class="fas fa-user"></i
          ></a>
        </li>
        <li class="nav__list-item">
          <a href='javascript:void(0);' class="nav__list-link" onclick="buttonback_click1()">
            <i class="far fa-comment"></i>
          </a>
        </li>
        <li class="nav__list-item">
        <a href='javascript:void(0);' class="nav__list-link" onclick="함수();">
            <i class="fas fa-search"></i>
          </a>
        </li>
        <li class="nav__list-item">
          <a href='javascript:void(0);' class="nav__list-link" onclick="함수();">
            <i class="fas fa-ellipsis-h"></i>
          </a>
        </li>
      </ul>
</nav>

<script>
		function buttonchatList_click() {
			
			var fromID = '<%=userID%>';
	  		var toID = '<%=toID%>';
	  		var chatRoomNum ='<%=consultNum%>';
			
			$.ajax({
		  	    url: "chatList",
		  	  data : {
					fromID : encodeURIComponent(fromID),
					toID : encodeURIComponent(toID),
					chatRoomNum : chatRoomNum
				}
		   }).done(function (fragment) {
		         $("#change").replaceWith(fragment);
		    });
		}
		
		
		function buttonback_click1() {

			$.ajax({
		  	    url: "chatindex",
		  	  	cache: false
		   }).done(function (fragment) {
		         $("#change").replaceWith(fragment);
		    });
		}
		
		function button_chatroom(roomnum) {
			alert(roomnum);
			sessionStorage.removeItem("roomnum");
	  		sessionStorage.setItem("roomnum", roomnum);

			$.ajax({
		  	    url: "chatroomlistpage",
		  	  	cache: false
		   }).done(function (fragment) {
		         $("#change").replaceWith(fragment);
		    });
		}
		
		
</script>



</div>
 
</body>
</html>