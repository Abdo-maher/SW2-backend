package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class commentModel {

private int CommentID;
private int userIDComment;
private int checkinID;
private String CommentBody;




public int getCommentID() {
	return CommentID;
}
public void setCommentID(int commentID) {
	CommentID = commentID;
}


public int getUserIDComment() {
	return userIDComment;
}
public void setUserIDComment(int userIDComment) {
	this.userIDComment = userIDComment;
}


public int getCheckinID() {
	return checkinID;
}
public void setCheckinID(int checkinID) {
	this.checkinID = checkinID;
}


public String getCommentBody() {
	return CommentBody;
}
public void setCommentBody(String commentBody) {
	CommentBody = commentBody;
}


public static commentModel addComment(int userIDComment,int checkinID,String CommentBody)//this fun to make user add his comment
{
	try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "Insert into comment(`UserCommentID`,`checkinID`,`CommentText`) VALUES  (?,?,?)";
		// System.out.println(sql);

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1,userIDComment);
		stmt.setInt(2,checkinID);
		stmt.setString(3, CommentBody);
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			commentModel comment = new commentModel();
			comment.setCommentID(rs.getInt(1));
			comment.setUserIDComment(userIDComment);
			comment.setCheckinID(checkinID);
			comment.setCommentBody(CommentBody);
			return comment;
		}
		return null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}


}
