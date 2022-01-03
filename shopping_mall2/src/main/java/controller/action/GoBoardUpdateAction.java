package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;

public class GoBoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		int dir = Integer.parseInt(request.getParameter("dir"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO boarddao = BoardDAO.getInstance();
		
		String url = "";
		if(dir == BoardDAO.update) {
			url = String.format("views/boardUpdate.jsp?no=%d",no);
			request.getRequestDispatcher(url).forward(request, response);
		}else if(dir == BoardDAO.delete) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 삭제하였습니다.');</script>");
			out.flush();
			boarddao.delete(no);
			url = "views/board.jsp";
			request.getRequestDispatcher(url).include(request, response);
		}
	}

}
