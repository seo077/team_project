package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import model.dto.UserDTO;
import util.DBManager;


public class UserDAO {
	public static final String ADMINID = "admin";

	private UserDAO() {
		
	}

	private static UserDAO instance = new UserDAO();


	public static UserDAO getInstance() {
		return instance;
	}
	
	private ArrayList<UserDTO> users =null;
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public Connection getConnection() {
		try {
			// 드라이버 연동 (jdbc mysql connector(.tor)
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/shopping_mall"; // 데이터베이스 주소
			String id = "root";
			String pw = "gkqrur1!1531a";

			conn = DriverManager.getConnection(url, id, pw);

			if (conn != null) {
				System.out.println("DB연동성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			System.out.println("DB연동실패");
		}
		return conn;
	}
	
	// 데이터 조회
	public ArrayList<UserDTO> getUsers() {
	
		try {
			conn = getConnection();
			
			String sql ="select * from users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			users = new ArrayList<>();
			
			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String gender = rs.getString(4);
				String PN = rs.getString(5);
				String email = rs.getString(6);
				String address = rs.getString(7);
				Timestamp regDate = rs.getTimestamp(8);
				
				users.add(new UserDTO(id,pw,name,gender,PN,email,address,regDate));
				
			}
			
			System.out.println("데이터 불러오기 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터 불러오기 실패");
		}
		
		
		return users;
	}
	
	// 회원 추가user
	public int addUser(UserDTO user) {
		
		// 아이디 중복처리
			try {
				users = getUsers();
				UserDTO newUser = new UserDTO(user.getId(), user.getPw(), user.getName(),user.getGender(), user.getPN(), user.getEmail(), user.getAddress(), new Timestamp(Calendar.getInstance().getTimeInMillis()));
				
				conn = getConnection();
				
				String sql = "insert into users values(?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, newUser.getId());
				pstmt.setString(2, newUser.getPw());
				pstmt.setString(3, newUser.getName());	
				pstmt.setString(4, newUser.getGender());
				pstmt.setString(5, newUser.getPN());
				pstmt.setString(6, newUser.getEmail());
				pstmt.setString(7, newUser.getAddress());
				pstmt.setTimestamp(8, newUser.getRegDate());
				
				pstmt.executeUpdate();
				
				this.users.add(newUser);
				System.out.println("가입성공");
				
				return users.size();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e);
				System.out.println("가입실패");
			}
		
		return -1;
	}

	// 중복검사
	public boolean checkID(String id) {
		users = new ArrayList<UserDTO>();
		users = getUsers();
		
		boolean check = true;
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getId().equals(id)) {
				check = false;
			}
		}
		return check;
	}
	
	// 로그인
	
	public boolean loginCheck(String id, String pw) {
		users = getUsers();
		
		for(UserDTO user : users) {
			if(user.getId().equals(id) && user.getPw().equals(pw)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	// 이름 찾기
	public UserDTO findName(String name) {
		try {
			users = getUsers();

			for (UserDTO index : users) {
				if (index.getName().equals(name)) {
					return index;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("findId err");
		}
		return null;
	}

	// 아이디 찾기
	public UserDTO findId(String id) {
		try {
			users = getUsers();

			for (UserDTO index : users) {
				if (index.getId().equals(id)) {
					return index;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("findId err");
		}
		return null;
	}

	// 아이디 비번으로 찾기
	public UserDTO findId(String id, String pw) {
		try {
			users = getUsers();

			for (UserDTO index : users) {
				if (index.getId().equals(id) && index.getPw().equals(pw)) {
					return index;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("findId err");
		}
		return null;
	}


	public boolean update(String id) {
		
//		for(UserDTO user : users) {
//			System.out.println("[id]:"+user.getId());
//			System.out.println(user);
//			System.out.println(user.getPw());
//			System.out.println(user.getPN());
//			System.out.println(user.getEmail());
//			System.out.println(user.getAddress());
//		}
		
		try {
			conn = DBManager.getConnection();
			for(UserDTO user : users) {
				if(user.getId().equals(id)) {
					//UPDATE `myshopping`.`users` SET `pw` = 'qq', `PN` = 'qq', `address` = 'qq', `email` = 'qq' WHERE (`id` = 'wwww');
					String sql = "update users set pw = ?, PN = ?, address = ?,email = ?  where (id = ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, user.getPw());
					pstmt.setString(2, user.getPN());
					pstmt.setString(3, user.getAddress());
					pstmt.setString(4, user.getEmail());
					pstmt.setString(5, id);
//					System.out.println("[id]:"+user.getId());
//					System.out.println(user);
//					System.out.println(user.getPw());
//					System.out.println(user.getPN());
//					System.out.println(user.getAddress());
//					System.out.println(user.getEmail());
//					System.out.println("================");
				}
			}
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("Update err");
		}
		return false;
	}

}
