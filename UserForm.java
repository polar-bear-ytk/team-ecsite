package jp.co.internous.eagle.model.form;

import java.io.Serializable;

public class UserForm implements Serializable {
	private static final long serialVersionUID = 2342287806637235780L;
	
	private String userName;
	
	private String password;
	
	private String familyName;
	
	private String firstName;
	
	private String familyNameKana;
	
	private String firstNameKana;
	
	private Byte gender;
	
	private String newPassword;
	
	private String newPasswordConfirm;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName=familyName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getFamilyNameKana() {
		return familyNameKana;
	}
	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana=familyNameKana;
	}
	
	public String getFirstNameKana() {
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana=firstNameKana;
	}
	
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender=gender;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
}