package com.store.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.store.model.items;
import com.store.util.DBHelper;

public class DAO {
	private Connection conn;

	public ArrayList<items> getAllItems() {
		ArrayList<items> list = new ArrayList<items>();
		String sql = "select * from items";
		try {
			conn = DBHelper.getConnection();
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet re = pstat.executeQuery();
			while (re.next()) {
				items it = new items();
				it.setId(re.getInt("id"));
				it.setName(re.getString("name"));
				it.setCity(re.getString("city"));
				it.setNumber(re.getInt("number"));
				it.setPrice(re.getInt("price"));
				it.setPicture(re.getString("picture"));
				list.add(it);
			}
			return list;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		} finally {
			/*if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}*/
		}
	}
	
	public items item (String id){
		items it = new items();
		String sql = "select * from items where id="+id;
		try {
			conn = DBHelper.getConnection();
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet re = pstat.executeQuery();
			while (re.next()) {
				it.setId(re.getInt("id"));
				it.setName(re.getString("name"));
				it.setNumber(re.getInt("number"));
				it.setPrice(re.getInt("price"));
				it.setPicture(re.getString("picture"));
			}
			return it;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}
	
	public ArrayList<items> items(String[] list) {
		ArrayList<items> items = new ArrayList<items>();

		try {
			conn = DBHelper.getConnection();
			if (list.length > 5) {
				for (int i = list.length - 1; i > list.length - 6; i--) {
					String sql = "select * from items where id=" + list[i];
					PreparedStatement pstat = conn.prepareStatement(sql);
					ResultSet re = pstat.executeQuery();
					while (re.next()) {
						items it = new items();
						it.setId(re.getInt("id"));
						it.setName(re.getString("name"));
						it.setNumber(re.getInt("number"));
						it.setPrice(re.getInt("price"));
						it.setPicture(re.getString("picture"));
						items.add(it);

					}
				}
			} else {
				for (int i = list.length - 1; i >= 0; i--) {
					String sql = "select * from items where id=" + list[i];
					PreparedStatement pstat = conn.prepareStatement(sql);
					ResultSet re = pstat.executeQuery();
					while (re.next()) {
						items it = new items();
						it.setId(re.getInt("id"));
						it.setName(re.getString("name"));
						it.setNumber(re.getInt("number"));
						it.setPrice(re.getInt("price"));
						it.setPicture(re.getString("picture"));
						items.add(it);

					}
				}
			}
			return items;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		} finally {

		}

	}
	
	
	public static void main(String[] args) {
		DAO da = new DAO();
		String[] lista = {"1","2","3","4","5"};
		ArrayList<items> itsa = da.items(lista);
		for(items ita :itsa){
			System.out.println(ita.getName());
		}
	}
	
	
	
}
