package logic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class contains an arraylist that will contain all the authors in the system
 * So every time an author account is created, the list is updated
 *
 */

public class AvailableAuthors implements Serializable {
	
	// class variable, used for serialization
	private static final long serialVersionUID = 1L;
	
	// This is the list of the names of all available authors
	private ArrayList<String> authorsList = new ArrayList<String>();
	
	
	/**
	 * Method to add an author
	 */
	public void addAuthor(String name) {
		authorsList.add(name);
	}
	
	
	/**
	 * Method to remove an author
	 */
	public void removeAuthor(String name) {
		authorsList.remove(name);
	}
	
	
	/**
	 * Default constructor
	 */
	public AvailableAuthors() {
		
	}


	/**
	 * Getters and Setters for authorsList
	 */

	
	public ArrayList<String> getAuthorsList() {
		return authorsList;
	}

	public void setAuthorsList(ArrayList<String> authorsList) {
		this.authorsList = authorsList;
	}
	
	
	/**
	 * Serialization stuff
	 */
	
	//Updates the saved data for the object 
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File("Available Authors" + ".txt"));
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
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File("Available Authors"+".txt")));
	        AvailableAuthors availableAuthors = (AvailableAuthors) inObject.readObject();
	        inObject.close();
	        
	        // retrieve all the stored attributes
	        
	        this.setAuthorsList(availableAuthors.getAuthorsList());
	        
		}catch(Exception e) {
			System.out.println("1: "+e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
