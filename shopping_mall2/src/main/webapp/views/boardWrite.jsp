<%@page import="model.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	%>
	<header>
	</header>
	<main>
		<h2>문의하기</h2>
		<form action="service" method="post">
			<input type="hidden" value="boardWrite" name="command">
			<table style="height:50vh; width:70%;" border="1">
				<tr style="height:10%">
					<td>아이디</td>
					<td colspan="2">${sessionScope.LOG }</td>
				</tr>
				<tr style="height:10%">
					<td colspan="3">
					<input type="radio" name="secret" value="<%=BoardDTO.open%>"> 공개하기
					<input type="radio" name="secret" value="<%=BoardDTO.secret%>"> 나만보기
					</td>
				</tr>
				<tr style="height:10%">
					<td>제목</td>
					<td colspan="2"><input  style="height:95%; width:99%;" type="text" placeholder="제목을 입력하세요" name="title"></td>
				</tr>
				<tr style="height:70%">
					<td>내용</td>
					<td colspan="2"><textarea style="height:95%; width:99%;" placeholder="내용을 입력하세요" name="content"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="작성하기">
		</form>
	</main>
	<footer>
	</footer>
</body>
</html>