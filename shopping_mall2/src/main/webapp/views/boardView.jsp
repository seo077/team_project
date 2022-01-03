<%@page import="model.dto.CommentsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CommentsDAO"%>
<%@page import="model.dao.UserDAO"%>
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
	
	// 댓글
	CommentsDAO commentdao = CommentsDAO.getInstance();
	ArrayList<CommentsDTO>comments = commentdao.getComments(no);
	pageContext.setAttribute("comments", comments);
	%>
	<!-- 
	로그인 한 아이디가 관리자라면 -> 글 삭제, 답변 달기 가능
	관리자 아이디 = "admin" 
	-->
	
	<header>
	</header>
	<main>
		<h2>글 보기</h2>
		<div class="board_container">
			<table style="height:40vh; width:70%;" border="1">
				<tr style="height:10%">
					<td>아이디</td>
					<td colspan="3">${sessionScope.log }</td>
				</tr>
				<tr style="height:10%">
					<td>등록 일자</td>
					<td>${board.getRegDate() }</td>
					<td>조회수</td>
					<td>${board.getViews() }</td>
				</tr>
		
				<tr style="height:10%">
					<td>제목</td>
					<td colspan="3">${board.getTitle() }</td>
				</tr>
				<tr style="height:70%">
					<td>내용</td>
					<td colspan="3">${board.getContent() }</td>
				</tr>
			</table>
		</div>
		<div class="btn_container">
			<a href="service?command=goBoard">목록</a>
			<!-- 댓글은 관리자와 글을 작성한 사람만 달 수 있도록 함 -->
			<!-- 로그인한 아이디 == 글 작성한 아이디 -->
			<c:if test="${board.getUser_id() eq sessionScope.log}">
				<a href="service?command=goBoardUpdate&dir=<%=BoardDAO.update%>&no=<%=board.getNo()%>">수정</a>
				<a href="service?command=goBoardUpdate&dir=<%=BoardDAO.delete%>&no=<%=board.getNo()%>">삭제</a>
				<form method="post" action="service">
					<input type="hidden" value="comment" name="command">
					<input type="hidden" value="${sessionScope.log }" name="logId">
					<input type="hidden" value="${board.getNo()}" name="no">
					<input type="text" placeholder="댓글을 입력하세요" name="comment">
					<input type="submit" value="작성">
				</form>
			</c:if>
			
			<!-- 로그인한 아이디 == 관리자 아이디 -->
			<c:set var="admin" value="<%=UserDAO.ADMINID %>"></c:set>
			<c:if test="${admin eq sessionScope.log}">
				<form method="post" action="service">
					<input type="hidden" value="comment" name="command">
					<input type="hidden" value="${admin }" name="logId">
					<input type="hidden" value="${board.getNo()}" name="no">
					<input type="text" placeholder="댓글을 입력하세요" name="comment">
					<input type="submit" value="작성">
				</form>
			</c:if>
		</div>
		<div class="comment_container">
			<table  style="height:40vh; width:70%;" border="1">
				<thead>
					<tr>
						<td style="width:20%">아이디</td>
						<td style="width:50%">댓글</td>
						<td colspan ="2" style="width:30%">등록일</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comment" items="${comments}">
						<input type="hidden" value="${comment.getNo() }" id="no">
					<c:choose>
						<c:when test="${admin eq sessionScope.log}">
							<tr style="background-color:yellow">
								<td>${comment.getUser_id() }</td>
								<td class="comment_box">${comment.getComments() }</td>
								<td>${comment.getRegDate() }</td>
								<td><a href="service?command=commentDelete&no=${comment.getNo() }">삭제</a></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${comment.getUser_id() }</td>
								<td class="comment_box">${comment.getComments() }</td>
								<td>${comment.getRegDate() }</td>
								<c:if test="${board.getUser_id() eq sessionScope.log}">
									<td><a href="service?command=commentDelete&no=${comment.getNo() }">삭제</a></td>
								</c:if>
							</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
	<footer>
	</footer>
</body>
</html>