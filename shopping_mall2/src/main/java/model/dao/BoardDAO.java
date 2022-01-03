package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.dto.BoardDTO;
import util.DBManager;

public class BoardDAO {
	private BoardDAO() {};
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public static final int delete = 1;
	public static final int update = 0;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 게시판 글 전체 가져오기
	public ArrayList<BoardDTO>getBoards(){
		ArrayList<BoardDTO>boards = null;
		String sql = "select * from board";
		try {
			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			boards = new ArrayList<BoardDTO>();
			while(rs.next()) {
				int no = rs.getInt(1);
				int is_secreat = rs.getInt(2);
				String user_id = rs.getString(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				int views = rs.getInt(6);
				Timestamp regDate = rs.getTimestamp(7);
				int answer = rs.getInt(8);
				
				BoardDTO board = new BoardDTO(no, is_secreat, user_id, title, content, views, regDate,answer);
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards;
	}
	
	// 게시판 글 하나 가져오기
	public BoardDTO getBoard(int no){
		BoardDTO board = null;
		String sql = "select * from board where no = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int is_secreat = rs.getInt(2);
				String user_id = rs.getString(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
				int views = rs.getInt(6);
				Timestamp regDate = rs.getTimestamp(7);
				int answer = rs.getInt(8);
				
				board = new BoardDTO(no, is_secreat, user_id, title, content, views, regDate,answer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	// 게시판에 글 등록
	public int writeBoard(String id,int is_secret,String title,String content, Timestamp regDate) {
		String sql= "insert into board(is_secret,user_id,title,content,regDate) values(?,?,?,?,?);";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, is_secret);
			pstmt.setString(2,id);
			pstmt.setString(3,title);
			pstmt.setString(4,content);
			pstmt.setTimestamp(5, regDate);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 데이터 베이스 오류
		return -2;
	}

	// 조회수 증가
	public int upViews(int no,int views) {
		String sql = "update board set views = ? where no = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, views+1);
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// 글 삭제
	public int delete(int no) {
		String sql = "delete from board where no =?";
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

	// 글 수정
	public int updateBoard(int no, int is_secret, String title, String content, Timestamp regDate) {
		String sql = "update board set is_secret = ? , title = ? , content = ? , regDate = ?   where no = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, is_secret);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setTimestamp(4, regDate);
			pstmt.setInt(5, no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	// 관리자 답변 완료
	public int adminAnswer(int no) {
		String sql = "update board set answer = ? where no = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardDTO.yesAnswer );
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
}
