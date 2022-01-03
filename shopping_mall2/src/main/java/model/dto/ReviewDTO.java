package model.dto;

import java.sql.Timestamp;

public class ReviewDTO {
	private int code;
	private String user_id;
	private String content;
	private Timestamp regDate;
	
	public ReviewDTO(int code,String user_id,String content,Timestamp regDate) {
		this.code = code;
		this.user_id = user_id;
		this.content = content;
		this.regDate = regDate;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
