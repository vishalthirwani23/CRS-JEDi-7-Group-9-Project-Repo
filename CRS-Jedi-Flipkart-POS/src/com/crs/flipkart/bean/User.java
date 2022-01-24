
package com.crs.flipkart.bean;

public class User {
    private String userName;
    // user id for professor starts with 1 and for student userid starts with 2.
    private String userId;
    private String password;
    private String mobileNo;
    private String emailID;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

    public User() {

    }

    public User(String userName, String userId,  String password, String mobileNo, String emailID ) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.mobileNo = mobileNo;
        this.emailID = emailID;

    }
}

