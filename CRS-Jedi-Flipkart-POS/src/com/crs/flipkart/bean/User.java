package com.crs.flipkart.bean;

public class User {
    private String userName;
    private String userId;
    private String role;
    private String password;
    private String contactNumber;
    private Integer joiningYear;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(Integer joiningYear) {
        this.joiningYear = joiningYear;
    }

    public User() {

    }

    public User(String userName, String userId, String role, String password, String contactNumber, Integer joiningYear) {
        this.userName = userName;
        this.userId = userId;
        this.role = role;
        this.password = password;
        this.contactNumber = contactNumber;
        this.joiningYear = joiningYear;
    }
}
