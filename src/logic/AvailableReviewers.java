package logic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains an arraylist that will contain all the reviewers in the system
 * So every time a reviewer account is created, the list is updated
 *
 */


public class AvailableReviewers implements Serializable {

	// class variable, used for serialization
	private static final long serialVersionUID = 1L;
	
	// This is the list of the names of all available reviewers
	private ArrayList<String> reviewersList = new ArrayList<String>();
	
	
	/**
	 * Method to add reviewers
	 */
	public void addReviewer(String name) {
		reviewersList.add(name);
	}
	
	
	/**
	 * Method to remove a reviewer
	 */
	public void removeReviewer(String name) {
		reviewersList.remove(name);
	}

	
	/**
	 * Default constructor
	 */
	public AvailableReviewers() {
		
	}
	
	/**
	 * Getters and setters
	 */
	
	public void setAvailableReviewersList(ArrayList<String> list) {
		this.reviewersList = list;
	}
	public ArrayList<String> getAvailableReviewersList() {
		return this.reviewersList;
	}
	
	
	/**
	 * Serialization stuff
	 */
	
	//Updates the saved data for the object 
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File("Available Reviewers" + ".txt"));
	        ObjectOutputStream outObject = new ObjectOutputStream(out);
	        outObject.writeObject(this);

	        out.close();
	        outObject.close();
		}catch(Exception e) {
			
		}
	}
	
	// Reloads the object's instance variables from a saved file (if it exists)
	public void getUpdate() {
		try {
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File("Available Reviewers"+".txt")));
	        AvailableReviewers availableReviewers = (AvailableReviewers) inObject.readObject();
	        inObject.close();
	        
	        // retrieve all the stored attributes
	        
	        this.setAvailableReviewersList(availableReviewers.getAvailableReviewersList());
	        
		}catch(Exception e) {
			System.out.println("1: "+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
