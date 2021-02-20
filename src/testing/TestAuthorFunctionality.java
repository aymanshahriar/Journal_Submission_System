package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import users.AuthorGUI;

public class TestAuthorFunctionality {

	/**
	 * Tests if all the instance variables were initialized when using the AuthorGUI() constructor
	 */
	@Test
	public void testDefaultConstructor() {
		
		// create an instance of AuthorGUI using default constructor
		AuthorGUI a = new AuthorGUI();
		
		// Check if name is the default name
		String expectedName = "No name";
		String actualName = a.getName();
		assertEquals("Default name of AuthorGUI should be \"No name\"" ,expectedName, actualName);
		
		// Check if email is the default email
		String expectedEmail = "No email";
		String actualEmail = a.getEmail();
		assertEquals("Default email of AuthorGUI should be \"No email\"" ,expectedEmail, actualEmail);
		
		// Check if email is the default username
		String expectedUsername = "No username";
		String actualUsername = a.getUsername();
		assertEquals("Default username of AuthorGUI should be \"No username\"" ,expectedUsername, actualUsername);
		
		// Check if password is the default password
		String expectedPassword = "No password";
		String actualPassword = a.getPassword();
		assertEquals("Default username of AuthorGUI should be \"No password\"" ,expectedPassword, actualPassword);
		
		// Check if default nominatedReviewers is an empty arrayList
		String expectedNominatedReviewers = "[]";
		String actualNominatedReviewers = a.getNominatedReviewers().toString();
		assertEquals("Default nominatedReviewers of AuthorGUI should be an empty arrayList" ,
				expectedNominatedReviewers, actualNominatedReviewers);
			
		// Check if default uploadedFiles is an empty arrayList
		int expectedLength = 0;
		int actualLength = a.getUploadedFiles().size();
		assertEquals("Default uploadedFiles of AuthorGUI should be an empty arrayList" ,
				expectedLength, actualLength);
		
		
		// Check if default assignedReviewers is an empty arrayList
		String expectedAssignedReviewers = "[]";
		String actualAssignedReviewers = a.getAssignedReviewers().toString();
		assertEquals("Default assignedReviewers of AuthorGUI should be an empty arrayList" ,
				expectedAssignedReviewers, actualAssignedReviewers);
		
		
		// Check if status is the default status
		String expectedStatus = "undetermined";
		String actualStatus = a.getStatus();
		assertEquals("Default status of AuthorGUI should be \"undetermined\"" ,expectedStatus, actualStatus);
		
	}
	
	
	/**
	 * Tests if changes to basic account information (name, username, password, email) persists
	 * after you store the info as a text file and retrieve it.
	 */
	@Test
	public void testBasicAccountInfo() {
		// create an instance of AuthorGUI
		AuthorGUI a1 = new AuthorGUI();
		
		// change name, email, username, password of AuthorGUI instance
		a1.setName("ChangedName");
		a1.setEmail("ChangedEmail");
		a1.setUsername("ChangedUsername");
		a1.setPassword("ChangedPassword");
		
		// save this instance as a text file
		a1.updateFile();
		
		// create another instance of AuthorGUI and retrieve the saved instance
		AuthorGUI a2 = new AuthorGUI(a1.getName());
		a2.getUpdate();
		
		// Check if name is the changed name
		String expectedName = "ChangedName";
		String actualName = a2.getName();
		assertEquals("Name of AuthorGUI should be \"ChangedName\"" ,expectedName, actualName);
		
		// Check if email is the changed email
		String expectedEmail = "ChangedEmail";
		String actualEmail = a2.getEmail();
		assertEquals("Email of AuthorGUI should be \"ChangedEmail\"" ,expectedEmail, actualEmail);
		
		// Check if email is the default username
		String expectedUsername = "ChangedUsername";
		String actualUsername = a2.getUsername();
		assertEquals("Default username of AuthorGUI should be \"ChangedUsername\"" ,expectedUsername, actualUsername);
		
		// Check if password is the default password
		String expectedPassword = "ChangedPassword";
		String actualPassword = a2.getPassword();
		assertEquals("Default username of AuthorGUI should be \"ChangedPassword\"" ,expectedPassword, actualPassword);
		
		
	}
	
	
	/**
	 * Tests if changes to attributes nominatedReviewers and assignedReviewers persists 
	 * after you store the account as a text file and retrieve it.
	 */
	@Test
	public void testReviewersList() {
		// create an instance of AuthorGUI
		AuthorGUI a1 = new AuthorGUI();
		
		// Add a nominated reviewer name and assigned reviewer name to AuthorGUI instance
		a1.addAssignedReviewer("Name1");
		a1.nominateReviewer("Name2");
		
		// save this instance as a text file
		a1.updateFile();
		
		// create another instance of AuthorGUI and retrieve the saved instance
		AuthorGUI a2 = new AuthorGUI(a1.getName());
		a2.getUpdate();
		
		// Check if nominatedReviewers contains "Name1"
		String expectedNominatedReviewers = "[Name2]";
		String actualNominatedReviewers = a2.getNominatedReviewers().toString();
		assertEquals("NominatedReviewers of AuthorGUI should be [Name2]" ,
				expectedNominatedReviewers, actualNominatedReviewers);
		
		// Check if assignedReviewers contains "Name2"
		String expectedAssignedReviewers = "[Name1]";
		String actualAssignedReviewers = a2.getAssignedReviewers().toString();
		assertEquals("Default assignedReviewers of AuthorGUI should be an [Name1]" ,
				expectedAssignedReviewers, actualAssignedReviewers);
		
	}

	
	/**
	 * Tests if changes to attribute status and deadline persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void testStatusAndDeadline() {
		// create an instance of AuthorGUI
		AuthorGUI a1 = new AuthorGUI();
		
		// Set the deadline and status of this AuthorGUI instance
		a1.setDeadline("New deadline");
		a1.setStatus("New status");
		
		// save this instance as a text file
		a1.updateFile();
		
		// create another instance of AuthorGUI and retrieve the saved instance
		AuthorGUI a2 = new AuthorGUI(a1.getName());
		a2.getUpdate();
		
		
		// Check if status is the changed status
		String expectedStatus = "New status";
		String actualStatus = a2.getStatus();
		assertEquals("The status of AuthorGUI should be \"New status\"" ,expectedStatus, actualStatus);
			
	}
	
	

}
