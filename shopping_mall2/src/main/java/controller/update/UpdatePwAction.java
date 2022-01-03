package controller.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class UpdatePwAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO dao = UserDAO.getInstance();
		
		// 값 받아오기
		String id = request.getSession().getAttribute("LOG").toString();
		UserDTO user = dao.findId(id);
		
		String present_pw = request.getParameter("present_pw");
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		
		if(user.getPw().equals(present_pw) && pw.equals(pw2)) {
			request.setAttribute("msg_update", "success");
			
			user.setPw(pw);
			dao.update(id);
			
			String url="views/mypage.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}else {
			request.setAttribute("msg_update", "fail");
			String url="views/mypage.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
