<%@page import="model.dto.ClothesDTO"%>
<%@page import="model.dao.ClothesDAO"%>
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
	int code = Integer.parseInt(request.getParameter("code"));
	ClothesDAO clodao = ClothesDAO.getInstance();
	ClothesDTO clothe = clodao.getClothe(code);
	pageContext.setAttribute("clothe", clothe);
	%>
	<header>
	</header>
	<main>
		<h2>옷 정보 수정하기</h2>
		<form action="service" method="post">
			<input type="hidden" value="clotheUpdate" name="command">
			<input type="hidden" value="${clothe.getCode() }" name="no">
			<table style="height:50vh; width:70%;" border="1">
				<tr style="height:10%;">
					<td>상품 코드</td>
					<td>${clothe.getCode() }</td>
					<td>사이즈</td>
					<td>${clothe.getSize() }</td>
					<td>수량</td>
					<td>${clothe.getCnt() }</td>
				<tr>
				<tr style="height:10%;">
					<td>상품 이름</td>
					<td>${clothe.getName() }</td>
					<td>가격</td>
					<td>${clothe.getPrice() }</td>
				<tr>
				<tr style="height:10%;">
					<td>타입</td>
					<td>${clothe.getType() }</td>
					<td>입기 좋은 온도</td>
					<td>${clothe.getTemperature() }</td>
				<tr>
				<tr style="height:50%;">
					<td>이미지</td>
					<td><img src="${clothe.getImg() }"></td>
				<tr>
			</table>
			<input type="submit" value="수정하기">
		</form>
	</main>
	<footer>
	</footer>
</body>
</html>