<%@page import="model.dto.ClothesDTO"%>
<%@page import="model.dao.ClothesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DETAIL</title>
</head>
<body>
    <h1>DETAIL</h1>
    <%
    ClothesDAO cloth = ClothesDAO.getInstance();
    request.setCharacterEncoding("utf-8");
    String code = request.getAttribute("code").toString();
    
    ClothesDTO pro = cloth.findPro(code);
    %>
    <div class="wrap">
    	<form id="form" action="service" method="post">
    		<input type="hidden" name="command" value="cart">
    		<input type="hidden" name="position" value="detail">
    		<input type="hidden" name="code" value="<%=code%>">
    	</form>
    	<img src="<%=pro.getImg()%>">
    	<h2>이름 : <%=pro.getName()%></h2>
    	<h3>코드 : <%=code%></h3>
    	<h3>사이즈 : <%=pro.getSize()%></h3>
    	<h3>가격 : <%=pro.getPrice()%></h3>
    	<input type="button" value="장바구니 담기" onclick="cart()">
    	<input type="button" value="구매하기">
    	<input type="button" value="뒤로가기" onclick="location.href='main'">
    </div>
    
    <script>
    	// 구매하기도 서비스 이용시 구별하기 위해서 함수 사용
    	function cart(){
    		var form = document.querySelector('#form');
    		form.submit();
    	}
    </script>
</body>
</html>