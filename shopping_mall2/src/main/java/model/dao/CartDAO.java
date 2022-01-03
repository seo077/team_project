package model.dao;

import java.util.ArrayList;

import model.dto.ClothesDTO;

public class CartDAO {
	private CartDAO() {
		
	}
	private static CartDAO instance = new CartDAO();
	public static CartDAO getInstance () {
		return instance;
	}
	
	private ArrayList<ClothesDTO> cart = null;
	
	public void cartSet() {
		cart = new ArrayList<ClothesDTO>();
	}
	
	public void put(String code) {
		ClothesDTO pro = ClothesDAO.getInstance().findPro(code);
		cart.add(pro);
	}
	
	public void takeout(String code) {
		ClothesDTO pro = ClothesDAO.getInstance().findPro(code);
		cart.remove(pro);
	}
	
	public void endShop() {
		cart = null;
	}
	
	public ArrayList<ClothesDTO> getCart() {
		return cart;
	}
}