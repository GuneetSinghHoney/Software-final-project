package DAO;

import java.util.Date;

public class userBean
{
	
	String FirstName, LastName, Gender, Address1, Address2, Email, Phone, account;
	Date dob;
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "userBean [FirstName=" + FirstName + ", LastName=" + LastName + ", Gender=" + Gender + ", Address1="
				+ Address1 + ", Address2=" + Address2 + ", Email=" + Email + ", Phone=" + Phone + ", account=" + account
				+ ", dob=" + dob + "]";
	}
	public userBean(String firstName, String lastName, String gender, String address1, String address2, String email,
			String phone, String account, Date dob) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Gender = gender;
		Address1 = address1;
		Address2 = address2;
		Email = email;
		Phone = phone;
		this.account = account;
		this.dob = dob;
	}
	public userBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
