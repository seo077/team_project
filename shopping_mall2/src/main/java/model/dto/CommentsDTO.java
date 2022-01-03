package model.dto;

import java.sql.Timestamp;

public class CommentsDTO {
	private int no;
	private int board_no;
	private String user_id;
	private String comments;
	private Timestamp regDate;
	
	public CommentsDTO(int no,int board_no, String user_id,String comments,Timestamp regDate) {
		this.no = no;
		this.board_no = board_no;
		this.user_id = user_id;
		this.comments = comments;
		this.regDate = regDate;
	}

	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
