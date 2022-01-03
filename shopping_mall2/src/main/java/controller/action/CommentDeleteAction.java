package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentsDAO;

public class CommentDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int no = Integer.parseInt(request.getParameter("no"));
		
		CommentsDAO commentdao = CommentsDAO.getInstance();
		int result = commentdao.deleteComment(no);
		
		String url = String.format("views/boardView.jsp?no=%d",no);
		if(result == -2) {
			// 데이터베이스 오류
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 실패');</script>");
			out.flush();
			request.getRequestDispatcher(url).include(request, response);
		}else {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
