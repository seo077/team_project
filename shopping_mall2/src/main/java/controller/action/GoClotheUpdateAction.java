package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ClothesDAO;

public class GoClotheUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		int dir = Integer.parseInt(request.getParameter("dir"));
		int code = Integer.parseInt(request.getParameter("code"));
		
		ClothesDAO clodao = ClothesDAO.getInstance();
		
		String url = "";
		if(dir == ClothesDAO.update) {
			url = String.format("views/clotheUpdate.jsp?code=%d",code);
			request.getRequestDispatcher(url).forward(request, response);
		}else if(dir == ClothesDAO.delete) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품을 삭제하였습니다.');</script>");
			out.flush();
			clodao.delete(code);
			url = "views/adminPage.jsp";
			request.getRequestDispatcher(url).include(request, response);
		}
	}

}
