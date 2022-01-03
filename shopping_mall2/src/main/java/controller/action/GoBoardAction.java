package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoBoardAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object session = request.getSession().getAttribute("LOG");
		String url = "";
		if(session == null) {
			// 로그인 하지 않은 사람 -> 로그인 페이지로
			url = "views/login.jsp";
			//response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용해주세요');</script>");
			out.flush();
		}else {
			// 로그인 한 사암 -> board페이지
			url = "views/board.jsp";
		}
		
		request.getRequestDispatcher(url).include(request, response);
	}
}
