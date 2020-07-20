<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index">Home <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
		<c:choose>
		    <c:when test="${not empty userID}">
		        <ul class="navbar-nav mr-auto">
		    		<li class="nav-item">
		        		<a class="nav-link" href="logout">Logout</a>
		      		</li>
		    	</ul>
		    </c:when>
		    <c:otherwise>
				<ul class="navbar-nav mr-auto">
		    		<li class="nav-item">
		        		<a class="nav-link" href="join">Join</a>
		      		</li>
		      		<li class="nav-item">				
		        		<a class="nav-link" href="login">Login</a>
		      		</li>
		    	</ul>
    		</c:otherwise>
		</c:choose>
    </form>
  </div>
</nav>
</body>
</html>