package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;

public class BoardWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String logId = (String)session.getAttribute("LOG");
		
		String tempSecret = request.getParameter("secret");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String url = "";
		// 입력 하지 않은 곳이 있으면 이전 페이지로 되돌아 감.
		if(tempSecret == null || title == null || content == null) {
			url = "views/boardWrite.jsp";
			PrintWriter out = response.getWriter();
			out.println("<script>alert('빈칸을 모두 입력해주세요.');</script>");
			out.flush();
			request.getRequestDispatcher(url).include(request, response);
		}else {
			int is_secret = Integer.parseInt(tempSecret);
			Calendar cd = Calendar.getInstance();
			long time = cd.getTimeInMillis();
			Timestamp regDate = new Timestamp(time);
			
			BoardDAO boarddao = BoardDAO.getInstance();
			int result = boarddao.writeBoard(logId, is_secret, title, content, regDate);
			
			if(result == -2) { //데이터 베이스 오류
				PrintWriter out = response.getWriter();
				out.println("<script>alert('글 작성에 실패했습니다.');</script>");
				out.flush();
				url = "views/boardWrite.jsp";
				request.getRequestDispatcher(url).include(request, response);
			}else {
				url = "views/board.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		
	}
}
