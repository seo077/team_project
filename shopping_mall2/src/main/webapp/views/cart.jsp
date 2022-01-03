<%@page import="model.dao.CartDAO"%>
<%@page import="model.dto.ClothesDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    	.col{
    		display: flex;
    	}
    </style>
    <title>CART</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String log = request.getSession().getAttribute("LOG").toString();
	CartDAO dao = CartDAO.getInstance();
	%>
    <h1><%=log %>님의 장바구니</h1>
    <div id="wrap">
    	
    </div>
    
    <script>
    var wrap = document.querySelector('#wrap');
    <%
    for(ClothesDTO index : dao.getCart()){%>
   		 var div = document.createElement('div');
    	div.id = "<%=index.getCode()%>";
    	div.className= "col";
    		div.innerHTML=`<div>
    	        <input type="checkbox" name="same" id="">
    	        <p><%=index.getName()%></p>
    	        <input type="button" value="X" onclick="del()">
    	    </div>`;
		wrap.append(div);
		
    
    <%
    }
    %>
    </script>
</body>
</html>