package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class followers {

	
	private Integer  id1;
	private Integer  id2;
	
	public Integer getId1() {
		return id1;
	}
	public Integer getId2() {
		return id2;
	}
	public void setId1(Integer id) {
		this.id1 = id;
	}

	public void setId2(Integer id) {
		this.id2 = id;
	}
	
	public static followers addNewrec(int id1, int id2) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into followers (`id1`,`id2`) VALUES  (?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				followers user = new followers();
				user.id1 = rs.getInt(1);
				user.id2 = rs.getInt(2);
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static followers removeRec(int id1, int id2) {
		try {
			Connection conn = DBConnection.getActiveConnection();
		//	String sql = "delete from followers (`id1`,`id2`) where  (?,?)";
			String sql = "delete from followers where id1=? and id2=?";

			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
		//	ResultSet rs = stmt.getGeneratedKeys();
		/*	if (rs.next()) {
				followers user = new followers();
				user.id1 = rs.getInt(1);
				user.id2 = rs.getInt(2);
				return user;
			}*/
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static followers showList(int id) {
		try {
			Connection conn = DBConnection.getActiveConnection();
		//	String sql = "delete from followers (`id1`,`id2`) where  (?,?)";
			String sql = "select * from followers where id1= ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
		//	stmt.setInt(1, id);
			stmt.executeQuery(sql);
			//ResultSet rs = stmt.getGeneratedKeys();
		/*	if (rs.next()) {
				followers user = new followers();
				user.id2 = rs.getInt(2);
				return user;
			}*/
			//return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public static followers addNewrec(int id1, int id2) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into followers (`id1`,`id2`) VALUES  (?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();					
			// return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

	
	/*
	public static UserModel following(int id1,int id2) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into followers (`id1`,`id2`) VALUES  (?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();		
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

}
