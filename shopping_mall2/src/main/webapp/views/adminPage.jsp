<%@page import="model.dto.ClothesDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.ClothesDAO"%>
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
	<!-- 관리자 아이디로 로그인되어 있을 떄만,
	 	메인 화면에 관리자 페이지 버튼 보이게 -->
	<%
	request.setCharacterEncoding("utf-8");
	ClothesDAO clothesdao = ClothesDAO.getInstance();
	ArrayList<ClothesDTO> clothes = clothesdao.getList();
	pageContext.setAttribute("clothes", clothes);
	%>
 	<header>
 	</header>
 	<main>
 		<h2>상품 관리</h2>
 		<a href="service?command=goClotheWrite">상품 등록하기</a>
		<table>
			<thead>
				<tr>
					<td style="width:5%">상품 코드</td>
					<td style="width:10%">사이즈</td>
					<td style="width:20%">상품 이름</td>
					<td style="width:30%">이미지</td>
					<td style="width:10%">가격</td>
					<td style="width:15%">타입</td>
					<td style="width:10%"></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="clothe" items="${clothes}" varStatus="status">
					<tr>
						<td>${clothe.getCode() }</td>
						<td>${clothe.getSize() }</td>
						<td>${clothe.getName() }</td>
						<td><img src="${clothe.getImg() }"></td>
						<td>${clothe.getPrice() }</td>
						<td>${clothe.getType() }</td>
						<td><a href="service?command=goClotheUpdate?dir=<%=ClothesDAO.update%>&code=${clothe.getCode()}">수정</a>/<a href="service?command=goClotheUpdate?dir=<%=ClothesDAO.delete%>&code=${clothe.getCode()}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
 	</main>
 	<footer>
 	</footer>
</body>
</html>