
<%@page import="model.dto.UserDTO"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <style>
        div {
            display: flex;
        }
    </style>
    <title>마이페이지</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	UserDAO dao = UserDAO.getInstance();
	String log = request.getSession().getAttribute("LOG").toString();
	
	UserDTO user = dao.findId(log);
	%>
    <h1><%=log %>님의 마이페이지</h1><br>
	
	<span>이름: <%=user.getName() %> </span><br>
    <span>아이디: <%=user.getId() %> </span> <br>   
    <div>비밀번호: <div id="pw"><input type="button" value="변경" id="pw" onclick="update_pw(this.id)"></div>
    </div><br>
    <span>성별: </span><br>
    <div>휴대폰: <div id="PN"><input type="button" value="변경" id="PN" onclick="update_PN(this.id)"></div>
    </div><br>
    <div>이메일: <div id="email"><input type="button" value="변경" id="email" onclick="update_email(this.id)"></div>
    </div><br>
    <div>주소: <div id="address"><input type="button" value="변경" id="address" onclick="update_address(this.id)"></div>
    </div><br><br><br>
   <!--  <form action="service" method="post">
        <input type="hidden" name="command" value="main">
        <input type="submit" value="뒤로가기">
    </form> -->
    <input type="submit" value="뒤로가기" onclick="location.href='index.jsp'">
    
   
   	<script>
        function update_pw(id) {
            var div = document.querySelector(`div#\${id}`);
            div.innerHTML = `<form action="service" method="post"><input type="hidden" name="command" value="updatePw"><input type="password" name="present_pw" placeholder="현재 비밀번호"><input type="password" name="pw" placeholder="변경할 비밀번호"><input type="password" name="pw2" placeholder="비밀번호 확인"><input type="button" id="\${id}" value="취소" onclick="restore(this.id)"><input type="submit" value="변경완료"></form>`;
        }

        function update_PN(id) {
            var div = document.querySelector(`div#\${id}`);
            div.innerHTML = `<form action="service" method="post"><input type="hidden" name="command" value="updatePN"><input type="text" name="PN" placeholder="휴대폰번호"><input type="button" id="\${id}" value="취소" onclick="restore(this.id)"><input type="submit" value="변경완료"></form>`;
        }

        function update_email(id) {
            var div = document.querySelector(`div#\${id}`);
            div.innerHTML = `<form action="service" method="post"><input type="hidden" name="command" value="updateEmail"><input type="text" name="email" placeholder="이메일"><select name="sel"><option value="@naver.com" checked>네이버</option><option value="@hanmail.net">다음</option><option value="@gmail.com">구글</option></select><input type="button" id="\${id}" value="취소" onclick="restore(this.id)"><input type="submit" value="변경완료"></form>`;
        }

        function update_address(id) {
            var div = document.querySelector(`div#\${id}`);
            div.innerHTML = `<form action="service" method="post"><input type="hidden" name="command" value="updateAddress"><input type="text" name="address" placeholder="주소"><input type="button" id="\${id}" value="취소" onclick="restore(this.id)"><input type="submit" value="변경완료"></form>`;
        }

        function restore(id) {
            var div = document.getElementById(`\${id}`);
            div.innerHTML = `<div id="\${id}"><input type="button" value="변경" id="\${id}" onclick="update_\${id}(this.id)"></div>`;
        }
    <%
    Object msg = request.getAttribute("msg_update");
    if(msg != null && msg.equals("success")){%>
    	alert("변경성공");
    <%
    } else if(msg != null && msg.equals("fail")){%>
   		 alert("변경실패, 정보를 정확히 입력해주세요");
    <%	
    }
    %>
    </script>
    
</body>
</html>