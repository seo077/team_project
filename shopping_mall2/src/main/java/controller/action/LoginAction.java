package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");


		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		UserDAO dao = UserDAO.getInstance();

		String url = "";
		if(dao.loginCheck(id, pw)){
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 완료');</script>");
			out.flush();
			
			request.getSession().setAttribute("LOG", id);
			
			url = "index.jsp";
			
		}else{
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 혹은 비밀번호 오류입니다.');</script>");
			out.flush();
			
			url = "views/login.jsp";

		}

		request.getRequestDispatcher(url).include(request, response);
	}

}
