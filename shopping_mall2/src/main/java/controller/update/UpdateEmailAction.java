package controller.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class UpdateEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO dao = UserDAO.getInstance();

		// 값 받아오기
		String id = request.getSession().getAttribute("LOG").toString();
		UserDTO user = dao.findId(id);

		String email = request.getParameter("email");
		String sel = request.getParameter("sel");
		
		user.setEmail(email+sel);
		if (dao.update(id)) {
			request.setAttribute("msg_update", "success");
			String url = "views/mypage.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			System.out.println("이메일수정 실패");
		}
	}
}
