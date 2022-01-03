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
			<table style="height:50vh; width:70%;" border="1">
				<tr style="height:10%;">
					<td>상품 코드</td>
					<td><input type="text" value="${clothe.getCode() }"></td>
					<td>사이즈</td>
					<td><input type="text" value="${clothe.getSize() }"></td>
					<td>수량</td>
					<td><input type="text" value="${clothe.getCnt() }"></td>
				<tr>
				<tr style="height:10%;">
					<td>상품 이름</td>
					<td><input type="text" value="${clothe.getName() }"></td>
					<td>가격</td>
					<td><input type="text" value="${clothe.getPrice() }"></td>
				<tr>
				<tr style="height:10%;">
					<td>타입</td>
					<td><input type="text" value="${clothe.getType() }"></td>
					<td>입기 좋은 온도</td>
					<td><input type="text" value="${clothe.getTemperature() }"></td>
				<tr>
				<tr style="height:50%;">
					<td>이미지</td>
					<td>
						<img src="${clothe.getImg() }">
						<input type="file" >
					</td>
				<tr>
			</table>
		<a href="service?command=goAdmin">목록</a>
	</main>
	<footer>
	</footer>
</body>
</html>