<%@page import="model.dao.UserDAO"%>
<%@page import="model.dto.BoardDTO"%>
<%@page import="model.dao.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
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
	BoardDAO boarddao = BoardDAO.getInstance();
	ArrayList<BoardDTO> boards = boarddao.getBoards();
	pageContext.setAttribute("boards", boards);
	%>
	<!-- 
	로그인 한 아이디가 관리자라면 -> 글 삭제, 답변 달기 가능
	관리자 아이디 = "admin" 
	-->
	<c:set var="logId" value="${sessionScope.log }" ></c:set>
	<c:set var="admin" value="<%=UserDAO.ADMINID %>"></c:set>
	<header>
	</header>
	
	<main>
		<h2>Q&A</h2>
		<a href="service?command=goBoardWrite">문의하기</a>
		<table>
			<thead>
				<tr>
					<td style="width:5%">번호</td>
					<td style="width:10%">아이디</td>
					<td style="width:50%">제목</td>
					<td style="width:10%">조회수</td>
					<td style="width:20%">등록일</td>
					<c:if test="${logId eq admin }"><td style="width:5%">삭제</td></c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boards}" varStatus="status">
						<tr>
						
							<td><a href = "service?command=goBoardView&no=${board.getNo() }">${status.count }</a></td>
							<td>${board.getUser_id() }</td>
							<!-- 유저가 비밀로 설정한 글, 답변이 완료된 글 표시 -->
							<c:choose>
								<c:when test="${board.getIs_secret() == BoardDTO.secret }">
									<td><a href = "service?command=goBoardView&no=${board.getNo() }">비밀글입니다.🔒<c:if test="${board.getAnswer() != BoardDTO.noAnswer }"><span style="border:solid 1px blue; margin:5px; color:blue">답변완료</span></c:if></a></td>
								</c:when>
								<c:otherwise>
									<td><a href = "service?command=goBoardView&no=${board.getNo() }">${board.getTitle() }<c:if test="${board.getAnswer() != BoardDTO.noAnswer }"><span style="border:solid 1px blue; margin:5px; color:blue">답변완료</span></c:if></a></td>
								</c:otherwise>
							</c:choose>
							<td>${board.getViews() }</td>
							<td>${board.getRegDate() }</td>
							<c:if test="${logId eq admin }"><td><a href="service?command=goBoardUpdate&dir=<%=BoardDAO.delete%>&no=${board.getNo() }">x</a></td></c:if>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</main>
	
	<footer>
	</footer>

</body>
</html>