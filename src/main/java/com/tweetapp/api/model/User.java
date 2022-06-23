package com.tweetapp.api.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "users")
public class User {
	@Id
	private String id;
	@Indexed
	private String username;
	private String password;
	private String confirmPassword;
	@Indexed
	private String email;
	private String firstName;
	private String lastName;
	private String contactNumber;



	/**
	 *
	 */
	public User() {
		super();
// TODO Auto-generated constructor stub
	}



	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param contactNumber
	 */
	public User(String id, String username, String password, String confirmPassword, String email, String firstName,
				String lastName, String contactNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
	}




	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
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



	public String getConfirmPassword() {
		return confirmPassword;
	}



	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNumber=" + contactNumber + "]";
	}



}