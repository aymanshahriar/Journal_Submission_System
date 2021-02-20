package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import users.EditorGUI;

public class TestEditorFunctionality {

	/**
	 * tests if all the instance variables were initialized when using the EditorGUI() constructor
	 */
	@Test
	public void testDefaultConstructor() {
		
		// create an instance of EditorGUI using default constructor
		EditorGUI r = new EditorGUI();
		
		// Check if name is the default name
		String expectedName = "No name";
		String actualName = r.getName();
		assertEquals("Default name of EditorGUI should be \"No name\"" ,expectedName, actualName);
		
		// Check if email is the default email
		String expectedEmail = "No email";
		String actualEmail = r.getEmail();
		assertEquals("Default email of EditorGUI should be \"No email\"" ,expectedEmail, actualEmail);
		
		// Check if email is the default username
		String expectedUsername = "No username";
		String actualUsername = r.getUsername();
		assertEquals("Default username of EditorGUI should be \"No username\"" ,expectedUsername, actualUsername);
		
		// Check if password is the default password
		String expectedPassword = "No password";
		String actualPassword = r.getPassword();
		assertEquals("Default username of EditorGUI should be \"No password\"" ,expectedPassword, actualPassword);
		
	}	
	
	
		/**
		 * Tests if changes to account information (name, username, password, email) persists
		 * after you store the info as a text file and retrieve it.
		 */
		@Test
		public void testBasicAccountInfo() {
			// create an instance of AuthorGUI
			EditorGUI e1 = new EditorGUI();
			
			// change name, email, username, password of AuthorGUI instance
			e1.setName("ChangedName");
			e1.setEmail("ChangedEmail");
			e1.setUsername("ChangedUsername");
			e1.setPassword("ChangedPassword");
			
			// save this instance as a text file
			e1.updateFile();
			
			// create another instance of EditorGUI and retrieve this saved instance
			EditorGUI e2 = new EditorGUI();
			e2.setName(e1.getName());
			e2.getUpdate();
			
			// Check if name is the changed name
			String expectedName = "ChangedName";
			String actualName = e2.getName();
			assertEquals("Name of EditorGUI should be \"ChangedName\"" ,expectedName, actualName);
			
			// Check if email is the changed email
			String expectedEmail = "ChangedEmail";
			String actualEmail = e2.getEmail();
			assertEquals("Email of EditorGUI should be \"ChangedEmail\"" ,expectedEmail, actualEmail);
			
			// Check if email is the default username
			String expectedUsername = "ChangedUsername";
			String actualUsername = e2.getUsername();
			assertEquals("Default username of EditorGUI should be \"ChangedUsername\"" ,expectedUsername, actualUsername);
			
			// Check if password is the default password
			String expectedPassword = "ChangedPassword";
			String actualPassword = e2.getPassword();
			assertEquals("Default username of EditorGUI should be \"ChangedPassword\"" ,expectedPassword, actualPassword);
			
			
		}
		
		


}
