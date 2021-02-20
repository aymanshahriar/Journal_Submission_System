package users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

public class User implements Serializable {
	
	private static final long serialVersionUID = 4L;
	
	private String name;
	private String email;
	private String password;
	private String username;
	
	public User() {
		super();
		this.name = "No name";
		this.email = "No email";
		this.password = "No password";
		this.username = "No username";
	}

	public User(String name, String email, String password, String username) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	
	
	/**
	 * This is a static method that takes a name, and checks if a file with the that name exists
	 * This is used during registration to check whether the name is already taken or not
	 * @param name of user that you want to check
	 * @return boolean saying whether a file with that name exists or not
	 */
	// takes file name as input, returns boolean saying whether that file exists of not
	public static boolean fileExists(String name) {
		try {
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File(name + ".txt")));
	        inObject.close();
	        return true;
		}catch(Exception e) {
			//System.out.println("file does not exist");
			return false;
		}
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

	
	

}
