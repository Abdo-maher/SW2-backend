package com.models;

public class TEST {

	@TEST
	public void saveplace(){
		userServices u = new uesrServices();
		
		String result=u.saveplace("2","6");
		JSONObject json = new 	JSONObject();
		json.put("status",1);
		assertEquals(json.toJSONString(),result);
	}
	
	@TEST
	public void CreateNewplace(){
		userServices p =new uesrServices();
	
	String res=p.CreateNewplace("grand","cafe","5","9");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
}
	

	@TEST
	public void likeCheckIn(){
		userServices k =new uesrServices();
	
	String res=k.likeCheckIn("2234","44");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
}
	
	@TEST
	public void updateUserPosition(){
		userServices r =new uesrServices();
	
	String res=r.updateUserPosition("22","44","88");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
	}
	
	

	@TEST
	public void addComment(){
		userServices d =new uesrServices();
	
	String res=d.updateUserPosition("22","44","salamy");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
	
}
	@TEST
	public void addNewfollowers(){
		userServices F =new uesrServices();
	
	String res=F.createCheckIn("22","44");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
	
	
}
	
	
	@TEST
	public void removefollowers(){
		userServices v =new uesrServices();
	
	String res=v.createCheckIn("22","44");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
	
	}
	
	@TEST
	public void showList(){
		userServices d =new uesrServices();
	
	String res=d.createCheckIn("22");
	JSONObject json = new 	JSONObject();
	json.put("status",1);
	assertEquals(json.toJSONString(),res);
	
	}
	
}
