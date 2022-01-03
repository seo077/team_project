
<%@page import="model.dao.CartDAO"%>
<%@page import="model.dto.ClothesDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.ClothesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-A-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		Object log = request.getSession().getAttribute("LOG");
		
	%>
	<h1>MAIN 페이지</h1>
	메뉴!
	
	<form method="post" action="../service">
		<input type="hidden" name="command" value="loginForm">
		<input type ="submit" value ="로그인">
	</form>
	<form method="post" action="../service">
		<input type="hidden" name="command" value="joinForm">
		<input type ="submit" value ="회원가입">
	</form>
	<form method="post" action="../service">
		<input type="hidden" name="command" value="weather">
		<input type ="submit" value ="날씨정보">
	</form>
	<form method="post" action="../service">
    	<input type="hidden" name="command" value="mypageForm">
        <input type="submit" value="마이페이지">
    </form>
	<form method="post" action="../service">
    	<input type="hidden" name="command" value="goBoard">
        <input type="submit" value="게시판">
    </form>
    
   <script>
    	var div = document.querySelector('.content');
    <%
    ClothesDAO cloth = ClothesDAO.getInstance();
    ArrayList<ClothesDTO> products = cloth.getList();
    
    for(ClothesDTO pro : products){%>

    	var newdiv = document.createElement("div");
    	newdiv.id = "pro<%=pro.getCode()%>";
    	newdiv.innerHTML = '<img src="<%=pro.getImg()%>"><h3><%=pro.getName()%></h3><h4><%=pro.getPrice()%></h4><input type="button" value="상세보기" id="pro<%=pro.getCode()%>" onclick="detail(this.id)"><input type="button" value="장바구니담기" id="pro<%=pro.getCode()%>" onclick="cart(this.id)">';
    	div.appendChild(newdiv);
    <%
    }
    %>
    
    function detail(id){
	    var form = document.createElement("form");
    	form.action = "service";
        form.method = "post";
        form.innerHTML = `<input type="hidden" name="command" value="detailForm"><input type="hidden" name="code" value="\${id}">`;

        document.body.append(form);
        form.submit();
    }
    
    function cart(id){
    	var form = document.querySelector('#form');
    	var hidden = document.querySelector('#val');
    	hidden.value = id.charAt(3);
    	console.log(hidden.value);
    	
    	form.submit();
    }
    
    </script>
</body>
</html>