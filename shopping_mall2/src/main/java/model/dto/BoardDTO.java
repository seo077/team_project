package model.dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	public static final int secret = 1;
	public static final int open = 0;
	
	public static final int noAnswer = 0;
	public static final int yesAnswer = 1;
	
	private int no;
	private int is_secret;
	private String user_id;
	private String title;
	private String content;
	private int views;
	private Timestamp regDate;
	private int answer;
	
	public BoardDTO(int no,int is_secret,String user_id,String title,String content,int views,Timestamp regDate,int answer) {
		this.no = no;
		this.is_secret = is_secret;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.views = views;
		this.regDate = regDate;
		this.answer = answer;
	}
	
	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getIs_secret() {
		return is_secret;
	}

	public void setIs_secret(int is_secret) {
		this.is_secret = is_secret;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
