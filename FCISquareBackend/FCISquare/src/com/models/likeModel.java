package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class likeModel {
private int likeID;
private int checkinID;
private int userID;


public int getLikeID() {
	return likeID;
}
public void setLikeID(int likeID) {
	this.likeID = likeID;
}


public int getCheckinID() {
	return checkinID;
}
public void setCheckinID(int checkinID) {
	this.checkinID = checkinID;
}


public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}





public static likeModel addlike(int userIDlike,int checkinID) {
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "INSERT INTO `likes` (`UserLikeID`,`CheckinID` )VALUES (?, ?)";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1,userIDlike);
		stmt.setInt(2,checkinID);
		stmt.executeUpdate();
			likeModel like = new likeModel();
			like.setUserID(userIDlike);
			like.setCheckinID(checkinID);
			return like;
		
			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
