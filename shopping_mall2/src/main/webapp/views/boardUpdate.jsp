<%@page import="model.dto.BoardDTO"%>
<%@page import="model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	BoardDAO boarddao = BoardDAO.getInstance();
	BoardDTO board = boarddao.getBoard(no);
	pageContext.setAttribute("board", board);
	%>
	<header>
	</header>
	<main>
		<h2>글 수정하기</h2>
		<form action="service" method="post">
			<input type="hidden" value="boardUpdate" name="command">
			<input type="hidden" value="${board.getNo() }" name="no">
			<table style="height:50vh; width:70%;" border="1">
				<tr style="height:10%">
					<td>아이디</td>
					<td colspan="2">${sessionScope.log }</td>
				</tr>
				<tr style="height:10%">
					<td colspan="3">
					<c:choose>
						<c:when test="${board.getIs_secret() ==  BoardDTO.secret}">
							<input type="radio" name="secret" value="<%=BoardDTO.open%>"> 공개하기
							<input type="radio" name="secret" value="<%=BoardDTO.secret%>" checked> 나만보기
						</c:when>
						<c:otherwise>
							<input type="radio" name="secret" value="<%=BoardDTO.open%>" checked> 공개하기
							<input type="radio" name="secret" value="<%=BoardDTO.secret%>"> 나만보기
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr style="height:10%">
					<td>제목</td>
					<td colspan="2"><input  style="height:95%; width:99%;" type="text" value="${board.getTitle()}" name="title"></td>
				</tr>
				<tr style="height:70%">
					<td>내용</td>
					<td colspan="2"><textarea style="height:95%; width:99%;" name="content">${board.getContent() }</textarea></td>
				</tr>
			</table>
			<input type="submit" value="수정하기">
		</form>
	</main>
	<footer>
	</footer>
</body>
</html>