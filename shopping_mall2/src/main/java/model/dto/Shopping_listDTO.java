package model.dto;

public class Shopping_listDTO {
	private String user_id;
	private int code;
	private int cnt;
	
	public Shopping_listDTO(String user_id,int code,int cnt) {
		this.user_id = user_id;
		this.code = code;
		this.cnt = cnt;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
