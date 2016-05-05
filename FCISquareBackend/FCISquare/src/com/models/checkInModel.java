package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class checkInModel 
{
	private int Id;
	private int userId;
	private int placeId;
	private String description;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	public static checkInModel createCheckIn(int userId,int placeID,String description) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into CheckIn(`CheckInUserID`,`CheckInPlaceID`,`CheckInDescription`) VALUES  (?,?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,userId);
			stmt.setInt(2,placeID);
			stmt.setString(3, description);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				checkInModel checkin = new checkInModel();
				checkin.setId(rs.getInt(1));
				checkin.setUserId(userId);
				checkin.setPlaceId(placeID);
				checkin.setDescription(description);
				return checkin;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}