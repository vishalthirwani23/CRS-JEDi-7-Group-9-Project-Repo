/**
 * 
 */
package com.flipkart.bean;
import java.util.HashMap;

/**
 * @author User
 *
 */
public class UserDB extends User{
	private HashMap<String,User> db=new HashMap<String,User>();
	
	public UserDB()
	{
		User user1 = new User();
		user1.setEmailID("abc@def.com");
		user1.setMobileNo("9999");
		user1.setPassword("password");
		user1.setRole("Student");
		user1.setUserId("101");
		user1.setUserName("ABCD");
		db.put("101",user1);
	}
	public void putUser(String name,String ID,String password,String mobile,String email,String role)
	{
		User user2 = new User();
		user2.setUserName(name);
		user2.setEmailID(email);
		user2.setMobileNo(mobile);
		user2.setPassword(password);
		user2.setRole(role);
		user2.setUserId(ID);
		
		db.put(ID, user2);
		
	}
	public User getUser(String ID)
	{
		if(db.containsKey(ID)==true)
			return db.get(ID);
		return null;
	}

}
