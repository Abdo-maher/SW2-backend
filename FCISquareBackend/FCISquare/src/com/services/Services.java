package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.UserModel;
import com.models.checkInModel;
import com.models.commentModel;
import com.models.followers;
import com.models.likeModel;
import com.models.placesModel;


@Path("/")
public class Services {

	
	  @GET
	 @Path("/signup")
	  @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	  Response.ok(new Viewable("/Signup.jsp")).build(); }
	 

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject  json = new JSONObject();
		//JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id), Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
	
	@POST
	@Path("/follow")
	@Produces(MediaType.TEXT_PLAIN)
	public String following(@FormParam("id1") int id1,
			@FormParam("id2") int id2) {
			followers follwer = followers.addNewrec(id1, id2);
			JSONObject  json = new JSONObject();
			//JSONObject json = new JSONObject();
			json.put(id1, "This is follower ID");
			
			return json.toJSONString();
	}

	
	@POST
	@Path("/unfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollowing(@FormParam("id1") int id1,
			@FormParam("id2") int id2) {
			followers follwer = followers.removeRec(id1, id2);
			JSONObject  json = new JSONObject();
			//JSONObject json = new JSONObject();
			json.put(id1,"Not follwing Him");
			
			return json.toJSONString();
	}
	
	@POST
	@Path("/getFollowersList")
	@Produces(MediaType.TEXT_PLAIN)

	public String getFollowersList(@FormParam("userID") int userID) {

		List<UserModel> retUsers = new ArrayList<UserModel>();
		retUsers = followers.followerList(userID);
		org.json.simple.JSONArray retArry = new org.json.simple.JSONArray();
		int i=1 ;
		String  str ="";
		for (UserModel user : retUsers) {
			JSONObject object = new JSONObject();
			
  			object.put("name", user.getName());
			object.put("email", user.getEmail());
			str+="Foloower#"+(i++)+":- {name="+user.getName()+"   ,  "+"email="+user.getEmail()+"}<br>";
    	retArry.add(object);
		}
 		return str;
	}
	

	@POST
	@Path("/currentLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String currentlocation(@FormParam("id") int id ) {
		UserModel user = UserModel.currentlocation(id);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	
	
	@POST
	@Path("/createPlaces")
	@Produces(MediaType.TEXT_PLAIN)
	public String createPlaces(@FormParam("name") String name , @FormParam("description") String description , @FormParam("lat") double lat, @FormParam("long") double lon) {
		placesModel Places = placesModel.CreateNewplace(name, description, lat, lon);
			JSONObject  json = new JSONObject();
			//JSONObject json = new JSONObject();
			json.put("id",Places.getId() );
			json.put("name",Places.getName());   
			json.put("description",Places.getDescription());
			json.put("lat",Places.getLat());
			json.put("long",Places.getLon());
			
			return json.toJSONString();
	}
	
	@POST
	@Path("/savePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String savePlace(@FormParam("placeId") int placeId,@FormParam("userId") int userid) {
			placesModel save = placesModel.saveplace(userid, placeId);
			JSONObject  json = new JSONObject();
			//JSONObject json = new JSONObject();
			json.put(userid, "NOw you save this place");
			
			return json.toJSONString();
	}
	
	@POST
	@Path("/CreateCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCheckIn(@FormParam("userId") int userId,
			 @FormParam("placeID") int placeID,@FormParam("description") String description) {
		checkInModel check = checkInModel.createCheckIn(userId, placeID, description);
		JSONObject json = new JSONObject();
		json.put("userId", check.getUserId());
		json.put("placeID", check.getPlaceId());
		json.put("description", check.getDescription());
		return json.toJSONString();
	}

	
	@POST
	@Path("/Comment")
	@Produces(MediaType.TEXT_PLAIN)
	public String addComment(@FormParam("userId") int userId,
			 @FormParam("checkinID") int checkinID,@FormParam("CommentText") String commentBody) {
		commentModel comm = commentModel.addComment(userId, checkinID, commentBody);
		JSONObject json = new JSONObject();
		json.put("userID", comm.getUserIDComment());
		json.put("checkinID", comm.getCheckinID());
		json.put("commentBody", comm.getCommentBody());
		return json.toJSONString();
	}
	
	@POST
	@Path("/addlike")
	@Produces(MediaType.TEXT_PLAIN)
	public String addlike(@FormParam("userId") int userId,
			 @FormParam("checkinID") int checkinID) {
		likeModel like = likeModel.addlike(userId, checkinID);
		JSONObject json = new JSONObject();
		json.put("userID", like.getUserID());
		json.put("checkinID", like.getCheckinID());
		return json.toJSONString();
	}
 

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
	
}
