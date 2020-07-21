<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<div class="header__back-btn" onclick="buttonback_click()">
							<i class="fas fa-arrow-left"></i>
						</div>
					</div>
					<div class="header__header-column">
						<h1 class="header__title">Lady bug</h1>
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
        <li class="friends__friend friend friend--lg">
          <div class="friend__column">
            <a href="chat.html">
              <img src="/ex/resources/chatcss/consultbox.png" class="friend__avatar"/></a>
            <div class="friend__content">
              <span class="friend__name"> 상담종료 </span>
              <span class="friend__status">
                	결제/환불
              </span>
            </div>
          </div>
          <div class="friend__column">
              <span style="font-size: 12px;">
                	2017.01.01
              </span>
          </div>
        </li>
        </div>
        
      </ul>
      </main>
     </div>
</div>
<nav class="nav" style="display: inline-block; position: absolute;">
      <ul class="nav__list">
        <li class="nav__list-item">
          <a href='javascript:void(0);' class="nav__list-link" onclick="buttonchatList_click()">
            <i class="fas fa-user"></i
          ></a>
        </li>
        <li class="nav__list-item">
          <a href='javascript:void(0);' class="nav__list-link" onclick="buttonback_click()">
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
 
</body>
</html>