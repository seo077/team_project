package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.dto.UserDTO;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String id = request.getParameter("userid");
		String pw = request.getParameter("userpw");
		String REPW = request.getParameter("REPW");
		String gender = request.getParameter("usergender");
		String PN = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String detail = request.getParameter("detail");
		String ex = request.getParameter("ex");

		// String postName = postcode+" "+address+" "+detail+" "+ex;
		String postName = request.getParameter("postcode")+" "+request.getParameter("address")+" "+request.getParameter("detail")+" "+request.getParameter("ex");

		UserDAO dao = UserDAO.getInstance();

		UserDTO user = new UserDTO(id,pw,name,gender,PN,email,postName);

		String url = "";
		if(!pw.equals(REPW)){
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 일치 확인 후 진행해주세요.');</script>");
			out.flush();
			
			url = "views/join.jsp";
		}else{
			if (dao.addUser(user) == -1) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('중복된 아이디는 가입이 불가능합니다.');</script>");
				out.flush();
				url = "views/join.jsp";
				
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원가입이 완료되었습니다.');</script>");
				out.flush();
				url = "index.jsp";
			}
		}
		
		request.getRequestDispatcher(url).include(request, response);

	}

}
