package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import users.ReviewerGUI;

import java.util.ArrayList;

public class TestReviewerFunctionality {

	/**
	 * tests if all the instance variables were initialized when using the ReviewerGUI() constructor
	 */
	@Test
	public void testDefaultConstructor() {
		
		// create an instance of ReviewerGUI using default constructor
		ReviewerGUI r = new ReviewerGUI();
		
		// Check if name is the default name
		String expectedName = "No name";
		String actualName = r.getName();
		assertEquals("Default name of ReviewerGUI should be \"No name\"" ,expectedName, actualName);
		
		// Check if email is the default email
		String expectedEmail = "No email";
		String actualEmail = r.getEmail();
		assertEquals("Default email of ReviewerGUI should be \"No email\"" ,expectedEmail, actualEmail);
		
		// Check if email is the default username
		String expectedUsername = "No username";
		String actualUsername = r.getUsername();
		assertEquals("Default username of ReviewerGUI should be \"No username\"" ,expectedUsername, actualUsername);
		
		// Check if password is the default password
		String expectedPassword = "No password";
		String actualPassword = r.getPassword();
		assertEquals("Default username of ReviewerGUI should be \"No password\"" ,expectedPassword, actualPassword);
		
		// Check if assignedAuthorName is the default "No one"
		String expectedAssignedAuthorName = "No one";
		String actualAssignedAuthorName = r.getAssignedAuthorName();
		assertEquals("Default assignedAuthorName of ReviewerGUI should be \"No name\"" ,
				expectedAssignedAuthorName, actualAssignedAuthorName);
		
		// Check if status is set to default true
		assertTrue("status should be set to true", r.getStatus());
		
		// Check if feedbackProvided is set to default false
		assertFalse("feedbackProvided should be set to false", r.isFeedbackProvided());
		
		// Check if acceptOrReject is set to default "undecided"
		String expectedAcceptOrReject = "Undecided";
		String actualAcceptOrReject = r.getAcceptOrReject();
		assertEquals("Default acceptOrReject of ReviewerGUI should be \"Undecided\"" ,
				expectedAcceptOrReject, actualAcceptOrReject);
		
		// Check if default majorRevisions is an empty arrayList
		String expectedMajorRevisions = "[]";
		String actualMajorRevisions = r.getMajorRevisions().toString();
		assertEquals("Default nominatedReviewers of AuthorGUI should be an empty arrayList" ,
				expectedMajorRevisions, actualMajorRevisions);
		
		// Check if default minorRevisions is an empty arrayList
		String expectedMinorRevisions = "[]";
		String actualMinorRevisions = r.getMinorRevisions().toString();
		assertEquals("Default nominatedReviewers of AuthorGUI should be an empty arrayList" ,
				expectedMinorRevisions, actualMinorRevisions);
	}

	
	
	/**
	 * Tests if changes to basic account information (name, username, password, email) persists
	 * after you store the info as a text file and retrieve it.
	 */
	@Test
	public void testBasicAccountInfo() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 1");
		
		// change name, email, username, password of AuthorGUI instance
		r1.setName("ChangedName");
		r1.setEmail("ChangedEmail");
		r1.setUsername("ChangedUsername");
		r1.setPassword("ChangedPassword");
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if name is the changed name
		String expectedName = "ChangedName";
		String actualName = r2.getName();
		assertEquals("Name of ReviewerGUI should be \"ChangedName\"" ,expectedName, actualName);
		
		// Check if email is the changed email
		String expectedEmail = "ChangedEmail";
		String actualEmail = r2.getEmail();
		assertEquals("Email of ReviewerGUI should be \"ChangedEmail\"" ,expectedEmail, actualEmail);
		
		// Check if email is the default username
		String expectedUsername = "ChangedUsername";
		String actualUsername = r2.getUsername();
		assertEquals("Default username of ReviewerGUI should be \"ChangedUsername\"" ,expectedUsername, actualUsername);
		
		// Check if password is the default password
		String expectedPassword = "ChangedPassword";
		String actualPassword = r2.getPassword();
		assertEquals("Default username of ReviewerGUI should be \"ChangedPassword\"" ,expectedPassword, actualPassword);
		
	}
	
	
	
	/**
	 * Tests if changes to attribute assignedAuthorName persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void testAssignedAuthorName() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 2");
		
		// change assignedAuthorName
		r1.setAssignedAuthorName("Author Name");
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if assignedAuthorName is the changed name
		String expectedName = "Author Name";
		String actualName = r2.getAssignedAuthorName();
		assertEquals("assignedAuthorName should be \"Author Name\"" ,expectedName, actualName);
		
		
	}
	
	
	
	/**
	 * Tests if changes to attributes majorRevisions and minorRevisions persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void restRevisions() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 3");
		
		// add a revision to majorRevision
		ArrayList<String> majorRevision = new ArrayList<String>();
		majorRevision.add("majorRevision1");
		r1.setMajorRevisions(majorRevision);
		
		// add a revision to majorRevision
		ArrayList<String> minorRevision = new ArrayList<String>();
		minorRevision.add("minorRevision1");
		r1.setMinorRevisions(minorRevision);
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if majorRevisions contains "majorRevision1"
		String expectedMajorRevisions = "[majorRevision1]";
		String actualMajorRevisions = r2.getMajorRevisions().toString();
		assertEquals("NominatedReviewers of AuthorGUI should be [majorRevision1]" ,
				expectedMajorRevisions, actualMajorRevisions);
		
		// Check if assignedReviewers contains "minorRevision1"
		String expectedMinorRevisions = "[minorRevision1]";
		String actualMinorRevisions = r2.getMinorRevisions().toString();
		assertEquals("Default assignedReviewers of AuthorGUI should be an [Name1]" ,
				expectedMinorRevisions, actualMinorRevisions);
			
	}
	
	
	
	/**
	 * Tests if changes to attribute status persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void testStatus() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 4");
		
		// change status of ReviewerGUI instance to false
		r1.setStatus(false);
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if status is set to false
		assertFalse("status should be set to false", r2.getStatus());
		
	} 
	
	
	
	/**
	 * Tests if changes to attribute acceptOrReject persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void testAcceptOrReject() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 5");
		
		// change acceptOrReject of AuthorGUI instance to "Decided"
		r1.setAcceptOrReject("Accept");
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if acceptOrReject is set to "undecided"
		String expectedAcceptOrReject = "Accept";
		String actualAcceptOrReject = r2.getAcceptOrReject();
		assertEquals("Default acceptOrReject of ReviewerGUI should be \"Accept\"" ,
				expectedAcceptOrReject, actualAcceptOrReject);
	}

	
	
	/**
	 * Tests if changes to attribute feedbackProvided persists after 
	 * you store the account as a text file and retrieve it.
	 */
	@Test
	public void testFeedbackProvided() {
		// create an instance of ReviewerGUI
		ReviewerGUI r1 = new ReviewerGUI("Test Name 6");
		
		// change feedBackProvided of AuthorGUI instance
		r1.setFeedbackProvided(true);
		
		// save this instance as a text file
		r1.updateFile();
		
		// create another instance of ReviewerGUI and retrieve the saved instance
		ReviewerGUI r2 = new ReviewerGUI(r1.getName());
		r2.getUpdate();
		
		// Check if feedbackProvided is set to true
		assertTrue("feedbackProvided should be set to true", r2.isFeedbackProvided());
	}
	
	
	
	
	
}
