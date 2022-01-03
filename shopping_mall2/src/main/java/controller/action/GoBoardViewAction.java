package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;
import model.dao.UserDAO;
import model.dto.BoardDTO;

public class GoBoardViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String logId = (String)session.getAttribute("LOG");
		
		BoardDAO boarddao = BoardDAO.getInstance();
		BoardDTO board = boarddao.getBoard(no);
		
		String user_id = board.getUser_id();
		int is_secret = board.getIs_secret();
		
		// 조회수 증가
		int views = board.getViews();
		boarddao.upViews(no,views);
		
		// 비밀 글인 경우
		if(is_secret == BoardDTO.secret) {
			if(user_id.equals(logId) || logId.equals(UserDAO.ADMINID)) {
				// 로그인 한 아이디랑 글을 작성한 아이디가 같을 때 or 관리자일 때
				String url = String.format("views/boardView.jsp?no=%d",no);
				request.getRequestDispatcher(url).forward(request, response);
			}else {
				// 로그인 한 아이디랑 글을 작성한 아이디가 다를 때
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('글을 열람할 권한이 없습니다.');</script>");
				out.flush();
				String url = "views/board.jsp";
				request.getRequestDispatcher(url).include(request, response);
				
			}
		}else {
			String url = String.format("views/boardView.jsp?no=%d",no);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
