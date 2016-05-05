package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class placesModel {
	private String name;
	private String description;
	private Integer id;
	private Double lat;
	private Double lon;
	private int userid;
	
	
	public int getuser() {
		return userid;
	}

	public void setuser(int userid) {
		this.userid = userid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	
	public static  placesModel CreateNewplace(String name, String description, Double lat,Double lon ) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into `places` (`name`,`description`,`lat`,`long`) VALUES  (?,?,?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setDouble(3, lat);
			stmt.setDouble(4, lon);
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				placesModel Place = new placesModel();
				Place.id = rs.getInt(1);
				Place.name = name;
				Place.description = description;
				Place.lat = lat;
				Place.lon = lon;
				return Place;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static placesModel saveplace(int userId, int placeId) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into savedPlaces (`userId`,`placeId`) VALUES  (?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, userId);
			stmt.setInt(2, placeId);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				placesModel save = new placesModel();
				save.id = rs.getInt(1);
				save.userid = rs.getInt(2);
				return save;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
