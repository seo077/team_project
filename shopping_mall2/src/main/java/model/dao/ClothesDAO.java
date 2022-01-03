package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.dto.ClothesDTO;
import util.DBManager;

public class ClothesDAO {
	private ClothesDAO () {};
	private static ClothesDAO instance = new ClothesDAO();
	public static ClothesDAO getInstance() {
		return instance;
	}
	
	public static final int delete = 1;
	public static final int update = 0;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 옷 전체 가져오기
	public ArrayList<ClothesDTO>getList(){
		ArrayList<ClothesDTO>clothes = null;
		String sql = "select * from clothes";
		try {
			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			clothes = new ArrayList<ClothesDTO>();
			while(rs.next()) {
				int code = rs.getInt(1);
				String size = rs.getString(2);
				String name = rs.getString(3);
				String img = rs.getString(4);
				int price = rs.getInt(5);
				int cnt = rs.getInt(6);
				int tem = rs.getInt(7);
				
				ClothesDTO clothe = new ClothesDTO(code, size, name, img, price, cnt, tem);
				clothes.add(clothe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clothes;
	}
	
	// 옷 하나 가져오기
	public ClothesDTO getClothe(int code){
		ClothesDTO clothe = null;
		String sql = "select * from clothes where code = ?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String size = rs.getString(2);
				String name = rs.getString(3);
				String img = rs.getString(4);
				int price = rs.getInt(5);
				int cnt = rs.getInt(6);
				int tem = rs.getInt(7);
				
				clothe = new ClothesDTO(code, size, name, img, price, cnt, tem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clothe;
	}
	
	// 옷 등록
	public int writeClothe(String size,String name,String img,int price,int cnt,int tem) {
		String sql= "insert into clothes(size,name,img,price,cnt,temperature,type) values(?,?,?,?);";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, size);
			pstmt.setString(2, name);
			pstmt.setString(3, img);
			pstmt.setInt(4, price);
			pstmt.setInt(5, cnt);
			pstmt.setInt(6, tem);

			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 데이터 베이스 오류
		return -2;
	}
	
	// 옷 삭제
	public int delete(int code) {
		String sql = "delete from clothes where code =?";
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	ArrayList<ClothesDTO> products = null;
	
	// 옷 검색
	public ClothesDTO findPro(String c) {
		int code = Integer.parseInt(c);
		try {
			products = getList();
			
			for(ClothesDTO index : products) {
				if(index.getCode()==code) {
					return index;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("findPro err");
			System.out.println(e);
		}
		return null;
	}

}
