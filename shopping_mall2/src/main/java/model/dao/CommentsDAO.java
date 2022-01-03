package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.dto.CommentsDTO;
import util.DBManager;

public class CommentsDAO {
	private CommentsDAO(){};
	private static CommentsDAO instance = new CommentsDAO();
	public static CommentsDAO getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 댓글 가져오기
	public ArrayList<CommentsDTO> getComments(int board_no){
		ArrayList<CommentsDTO>comments = null;
		String sql = "select * from comments where board_no = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			comments = new ArrayList<CommentsDTO>();
			while(rs.next()) {
				int no = rs.getInt(1);
				String user_id = rs.getString(3);
				String comment = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				
				CommentsDTO tempComment = new CommentsDTO(no,board_no, user_id, comment, regDate);
				comments.add(tempComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	//댓글 작성하기
	public int writeComment(int board_no,String user_id,String comment,Timestamp regDate) {
		String sql = "insert into comments(board_no,user_id,comments,regDate) values (?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setString(2, user_id);
			pstmt.setString(3, comment);
			pstmt.setTimestamp(4, regDate);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// 댓글 삭제하기
	public int deleteComment(int no) {
		String sql = "delete from comments where = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	// 댓글 수정하기
}
