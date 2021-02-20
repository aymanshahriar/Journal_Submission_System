package users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import logic.AvailableReviewers;
public class Reviewer extends User implements Serializable{
	
	/**
	 * Changes made to this class:
	 * 1) I have the attribute assignedAuthorName that contains the name of the author that the reviewer is assigned to
	 *    So if you want to access the account of this author, use the getUpdate() method from Author class
	 *    	 
	 */
	
	//Declare instance variables
	private String assignedAuthorName;    // the name of the author that this reviewer is assigned to
	private ArrayList<String> majorRevisions = new ArrayList<String>();
	private ArrayList<String> minorRevisions = new ArrayList<String>();
	private String acceptOrReject = "Undecided";
	private boolean status;
	private boolean feedbackProvided;
	
	public Reviewer() {
		super();
		this.status = true;
		setName("DONALD TRUMP");
		setAssignedAuthorName("No one");
	}
	

	public Reviewer(boolean status, boolean feedbackProvided, String name, String email, String password, String username) {
		super(name, email, password, username);
		this.status = status;
		this.feedbackProvided = feedbackProvided;
		this.setAssignedAuthorName("No one");
	}
	
	/**
	 * Updates the saved data for the object
	 */
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File(getName() + ".txt"));
	        ObjectOutputStream outObject = new ObjectOutputStream(out);
	        outObject.writeObject(this);

	        out.close();
	        outObject.close();
		}catch(Exception e) {
			
		}
	}
	
	
	/**
	 * Reloads the object's instance variables from a saved file (if it exists)
	 */
	public void getUpdate() {
		try {
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File(getName() + ".txt")));
	        Reviewer reviewer = (Reviewer) inObject.readObject();
	        inObject.close();
	        
	        this.setMajorRevisions(reviewer.getMajorRevisions());
	        this.setMinorRevisions(reviewer.getMinorRevisions());
	        this.setName(reviewer.getName());
	        this.setStatus(reviewer.getStatus());
	        this.setAcceptOrReject(reviewer.getAcceptOrReject());
	        this.setAssignedAuthorName(reviewer.getAssignedAuthorName());
	        this.setUsername(reviewer.getUsername());
	        this.setPassword(reviewer.getPassword());
	        this.setEmail(reviewer.getEmail());
	        this.setFeedbackProvided(reviewer.isFeedbackProvided());
		}catch(Exception e) {
	
		}
	}
	
	
	public void accept() {
		getUpdate();
		if(!getAssignedAuthorName().equalsIgnoreCase("No one")) {
			int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to accept?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(input == JOptionPane.YES_OPTION) {
				setAcceptOrReject("Accept");
			}
			updateFile();
		}
		else {
			JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
		}
	}
	
	public void reject() {
		getUpdate();
		if(!getAssignedAuthorName().equalsIgnoreCase("No one")) {
			int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to reject?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(input == JOptionPane.YES_OPTION) {
				setAcceptOrReject("Reject");
			}
			updateFile();
		}
		else {
			JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
		}
	}
	
	
	/**
	 * add major Revisions to file
	 */
	public void majorRevision(){
		getUpdate();
		if(!getAssignedAuthorName().equalsIgnoreCase("No one")){
			String revision = "a";
			File file = new File(getName() + "_major_Revisions.txt");
			revision = JOptionPane.showInputDialog("Enter the major revision:");
			
			if(revision == null) {
				revision = "";
				}
			
			// If textfield input is blank.
			if(!revision.equals("")) {
				majorRevisions.add(revision);
				setFeedbackProvided(true);
				JOptionPane.showMessageDialog(null, "Revision stored successfully.");
				}
			
			updateFile();
		}
		else {
			JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
		}
	}
	
	/**
	 * add minor Revisions to file
	 */
	public void minorRevision() {
		getUpdate();
		if(!getAssignedAuthorName().equalsIgnoreCase("No one")){
			String revision = "a";
			File file = new File(getName() + "_minor_Revisions.txt");
			revision = JOptionPane.showInputDialog("Enter the minor revision:");
			
			// If textfield input is blank.
			if(revision == null) {
				revision = "";
				}
				
			if(!revision.equals("")) {
				minorRevisions.add(revision);
				setFeedbackProvided(true);
				JOptionPane.showMessageDialog(null, "Revision stored successfully.");
				}
			
			updateFile();
		}
		else {
			JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
		}
	}
	
	
	/**
	 * @return the majorRevisions
	 */
	public ArrayList<String> getMajorRevisions() {
		return majorRevisions;
	}

	/**
	 * @param majorRevisions the majorRevisions to set
	 */
	public void setMajorRevisions(ArrayList<String> majorRevisions) {
		this.majorRevisions = majorRevisions;
	}

	/**
	 * @return the minorRevisions
	 */
	public ArrayList<String> getMinorRevisions() {
		return minorRevisions;
	}

	/**
	 * @param minorRevisions the minorRevisions to set
	 */
	public void setMinorRevisions(ArrayList<String> minorRevisions) {
		this.minorRevisions = minorRevisions;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		// if status is set to unavailable, remove name of this reviewer from AvailableReviewers
		if ((this.status == true) && (status == false)) {
			AvailableReviewers lst = new AvailableReviewers();
			lst.getUpdate();
			lst.removeReviewer(this.getName());
			
			// makes sure this new list is saved
			lst.updateFile();
		}
		this.status = status;
	}
	
	/**
	 * @param status the minorRevisions to set
	 */
	public boolean getStatus() {
		return status;
	}

	
	/**
	 * @return the assignedAuthorName
	 */
	public String getAssignedAuthorName() {
		return assignedAuthorName;
	}

	/**
	 * @param assignedAuthorName the assignedAuthorName to set
	 */
	public void setAssignedAuthorName(String assignedAuthorName) {
		this.assignedAuthorName = assignedAuthorName;
	}
	
	/**
	 * @return the acceptOrReject
	 */
	public String getAcceptOrReject() {
		return acceptOrReject;
	}


	/**
	 * @param acceptOrReject the acceptOrReject to set
	 */
	public void setAcceptOrReject(String acceptOrReject) {
		this.acceptOrReject = acceptOrReject;
	}

	/**
	 * 
	 * @return isFeedbackProvided, true when major or minor revision is given, false otherwise
	 */
	public boolean isFeedbackProvided() {
		return feedbackProvided;
	}

	/**
	 * 
	 * @param feedbackProvided, true when major or minor revision is given, false otherwise
	 */
	public void setFeedbackProvided(boolean feedbackProvided) {
		this.feedbackProvided = feedbackProvided;
	}
	
}
