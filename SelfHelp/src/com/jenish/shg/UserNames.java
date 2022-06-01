package com.jenish.shg;

import java.util.Date;

public class UserNames {
	
	int pId;
	String name;
	String gender; 
	String address;
	String email;
	String pass;
	String phNo;
	Date dob;
	Date jod;
	String type;
	
	public UserNames(String pId, String name, String gender, String address, String email,String pass, String phNo, Date dob,
			Date jod, String type) {
		this.pId = Integer.parseInt(pId);
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.pass = pass;
		this.phNo = phNo;
		this.dob = dob;
		this.jod = jod;
		this.type = type;
	}
	

	public UserNames(int id, String name2, String gender2, String address2, String email2, String phNo2, Date dob2,
			Date jod2, String type2) {
		pId = id;
		name = name2;
		gender = gender2;
		address = address2;
		email = email2;
		phNo = phNo2;
		dob = dob2;
		jod = jod2;
		type = type2;
	}


	public int getpId() {
		return pId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getPhNo() {
		return phNo;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public Date getJod() {
		return jod;
	}
	
	public String getType() {
		return type;
	}
	
}
