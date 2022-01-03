package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tmp = request.getParameter("code");
//		System.out.println("[detailformaction] "+tmp); // pro1
		String code = tmp.substring(3, tmp.length()); 
//		System.out.println(code); // 1

		request.setAttribute("code", code);
		String url = "detailForm";
		request.getRequestDispatcher(url).forward(request, response);
	}

}
