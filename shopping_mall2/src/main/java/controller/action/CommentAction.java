package controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dao.CommentsDAO;
import model.dao.UserDAO;

public class CommentAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommentsDAO commentdao = CommentsDAO.getInstance();
		BoardDAO boarddao = BoardDAO.getInstance();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String user_id = request.getParameter("logId");
		String comment = request.getParameter("comment");
		Calendar cd = Calendar.getInstance();
		long time = cd.getTimeInMillis();
		Timestamp regDate = new Timestamp(time);
		
		commentdao.writeComment(no, user_id, comment, regDate);
		
		if(user_id.equals(UserDAO.ADMINID)) {
			// 관리자 -> 게시판에 답변 완료 표시
			boarddao.adminAnswer(no);
		}
		
		String url = String.format("views/boardView.jsp?no=%d",no);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
