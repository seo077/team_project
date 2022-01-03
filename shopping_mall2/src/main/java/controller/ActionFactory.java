package controller;

import controller.action.Action;
import controller.action.BoardUpdateAction;
import controller.action.GoBoardUpdateAction;
import controller.action.BoardWriteAction;
import controller.action.ClotheUpdateAction;
import controller.action.GoClotheWriteAction;
import controller.action.CommentAction;
import controller.action.CommentDeleteAction;
import controller.action.DetailFormAction;
import controller.action.GoAdminPageAction;
import controller.action.GoBoardAction;
import controller.action.GoBoardViewAction;
import controller.action.GoBoardWriteAction;
import controller.action.GoClotheUpdateAction;
import controller.action.GoMainAction;
import controller.action.JoinAction;
import controller.action.JoinFormAction;
import controller.action.LoginAction;
import controller.action.LoginFormAction;
import controller.action.MypageFormAction;
import controller.action.WeatherAction;
import controller.update.UpdateAddressAction;
import controller.update.UpdateEmailAction;
import controller.update.UpdatePNAction;
import controller.update.UpdatePwAction;
import controller.action.CartAction;
import controller.action.CartFormAction;


public class ActionFactory {
	private ActionFactory() {};
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("goMain")) action = new GoMainAction();
		else if(command.equals("goBoard")) action = new GoBoardAction();
		else if(command.equals("goBoardWrite")) action = new GoBoardWriteAction();
		else if(command.equals("goBoardView")) action = new GoBoardViewAction();
		else if(command.equals("boardWrite")) action = new BoardWriteAction();
		else if(command.equals("goBoardUpdate")) action = new GoBoardUpdateAction();
		else if(command.equals("boardUpdate")) action = new BoardUpdateAction();
		else if(command.equals("comment")) action = new CommentAction();
		else if(command.equals("commentDelete")) action = new CommentDeleteAction();
		else if(command.equals("goClotheWrite")) action = new GoClotheWriteAction();
		else if(command.equals("goClotheUpdate")) action = new GoClotheUpdateAction();
		else if(command.equals("clotheUpdate")) action = new ClotheUpdateAction();
		else if(command.equals("goAdmin")) action = new GoAdminPageAction();
		
		// 로그인, 회원가입
		else if(command.equals("loginForm")) action = new LoginFormAction();
		else if(command.equals("joinForm")) action = new JoinFormAction();
		else if(command.equals("login")) action = new LoginAction();
		else if(command.equals("join")) action = new JoinAction();
		else if(command.equals("weather")) action = new WeatherAction();
		 
		else if (command.equals("mypageForm")) action = new MypageFormAction();
		// 상세보기
		else if (command.equals("detailForm")) action = new DetailFormAction();
		// 장바구니
		else if (command.equals("cart")) action = new CartAction();
		else if (command.equals("cartForm")) action = new CartFormAction();
		
		// 정보수정
		else if (command.equals("updatePw")) action = new UpdatePwAction();
		else if (command.equals("updatePN")) action = new UpdatePNAction();
		else if (command.equals("updateEmail")) action = new UpdateEmailAction();
		else if (command.equals("updateAddress")) action = new UpdateAddressAction();
				
		return action;
	}
}
