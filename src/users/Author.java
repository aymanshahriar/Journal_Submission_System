package users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;


import logic.Deadline;

/**
 * CHANGES April 9th, 3:10 AM
 * Changed Instance Variable: String deadline to Deadline deadline
 * Changed Methods:
 * 		- getName(): returns names of nominated reviewers
 * 					 used in AuthorGUI, upon clicking "View Selected Reviewers"
 * 	Added Methods:
 * 		- getDeadline(): returns the deadline
 * 		- setDeadline(int year, int month, int day): is only use when editor wants to change
 * 													 due date of the first upload
 * 		- setDeadline(String status): is called once the editor has assigned a status to the author,
 * 									  is never used on its own
 * 				if major revision, 14 day deadline
 * 				if minor revision, 7 day deadline
 * 				otherwise, no deadline assigned
 * 		**This does not show up on the AuthorGUI** --FIXED
 * 		- setDeadline(Deadline deadline): setter
 * 			
 * @author Vi
 *
 */


public class Author extends User implements Serializable {
	
	/**
	 * Static Variables:
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instance variables:
	 */
	
	// This arrayList is used to store the names of the reviewers that the Author recommends
	private ArrayList<String> nominatedReviewers;
	
	// This ArrayList is used to store the pdf files that the author uploads
	private ArrayList<File> uploadedFiles = new ArrayList<File>();
	
	private ArrayList<String> assignedReviewers = new ArrayList<String>();
	private String status = "undetermined";
	private Deadline deadline = new Deadline();

				
	/**
	 * Constructors:
	 */
	
	// constructor with no parameters
	public Author() {
		super();
		nominatedReviewers = new ArrayList<String>();
		uploadedFiles = new ArrayList<File>();
		assignedReviewers = new ArrayList<String>();
	}
	
	// this constructor is called by AuthorGUI during account creation
	public Author(String name, String email, String username, String password) {
		super(name, email, password, username);
		this.nominatedReviewers = new ArrayList<String>();
		this.uploadedFiles = new ArrayList<File>();
		this.assignedReviewers = new ArrayList<String>();
	}
	

	/**
	 * The two serialization methods:
	 */
	
	//Updates the saved data for the object 
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File(super.getName()+".txt"));
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
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File(super.getName()+".txt")));
	        Author author = (Author) inObject.readObject();
	        inObject.close();
	        
	        // retrieve all the stored attributes
	        this.setName(author.getName());
	        this.setEmail(author.getEmail());
	        this.setUsername(author.getUsername());
	        this.setPassword(author.getPassword());
	        
	        this.setNominatedReviewers(author.getNominatedReviewers());
	        this.setUploadedFiles(author.getUploadedFiles());
	        
	        this.setStatus(author.getStatus());
	        this.setAssignedReviewers(author.getAssignedReviewers());
	        this.setDeadline(author.getDeadline());
	     
	        
		}catch(Exception e) {
			System.out.println("1: "+e.getMessage());
		}
	}
	
	
	/**
	 * Methods:
	 */
	
	/**
	 * Nominate the reviewers
	 * @param newReviewer, the new reviewer string
	 * @return accept or reject in string form
	 */
	public String nominateReviewer(String newReviewer) {
		int size = this.nominatedReviewers.size();
		if (size < 3) {
			for(int i = 0; i <= size-1; i++) {
				if(newReviewer.contentEquals(nominatedReviewers.get(i))) {
					return "already selected";
				}
			}
			this.updateFile();
			nominatedReviewers.add(newReviewer);
			this.updateFile();
			return "accepted";
		}
		else {
			return "rejected";
		}
	}
	
	
	/**
	 * Returns string of names
	 * @return String of all nominated reviewers
	 */
	public String getNames() {
		String names = "";
		for(int i = 0; i < nominatedReviewers.size(); i++) {
			if(i == 2) {
				names = names + nominatedReviewers.get(i).toString();
			}else {
			names = names + nominatedReviewers.get(i).toString() + " || ";
			}
			System.out.println(names);
		}
		return names;
	}
	
	
	
	/**
	 * The getters and setters of the instance variables:
	 */
	
	/**
	 * @return the nominatedReviewers
	 */
	public ArrayList<String> getNominatedReviewers() {
		return nominatedReviewers;
	}
	

	/**
	 * @param nominatedReviewers the nominatedReviewers to set
	 */
	public void setNominatedReviewers(ArrayList<String> nominatedReviewers) {
		this.nominatedReviewers = nominatedReviewers;
	}

	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
		this.updateFile();
	}
	
	/**
	 * @return the deadline
	 */
	public Deadline getDeadline() {
		return deadline;
	}
	
	/**
	 * Sets the deadline to a new and valid date
	 * @param status
	 * @throws ParseException 
	 * @return String stating result of process
	 */
	public String setDeadline(int year, int month, int day) throws ParseException {
		
		if(this.status.contentEquals("undetermined")){
			//The deadline line can only be changed by date before a status is assigned
			if(deadline.beforeDeadline(year, month, day)) {
				deadline.setDeadline(year, month, day);
				return "Changed";
			}
		}
		return "Not changed";
	}
	
	/**
	 * Sets the deadline according to status assigned
	 * @param status
	 */
	public void setDeadline(String status) {
		//Gets current date
		Calendar calendar = Calendar.getInstance();
		if(status.contentEquals("Accept")|status.contentEquals("Reject")) {
			//default date in order to changed label
			deadline.setDeadline(0, 0, 1);
		}else {
			if(status.contentEquals("Major Revision")) {
				//Gives 14 day deadline
				calendar.add(Calendar.DAY_OF_MONTH, 14);
				
			}else if(status.contentEquals("Minor Revision")) {
				//Gives 7 day deadline
				calendar.add(Calendar.DAY_OF_MONTH, 7);
			}
			
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			deadline.setDeadline(year, month, day);
		}
	}
	
	/**
	 * Sets deadline using an existing deadline
	 * @param deadline
	 */
	public void setDeadline(Deadline deadline) {
		this.deadline = deadline;
	}
	
	/**
	 * Returns list of assigned Reviewers
	 * @return assignedReviewers
	 */
	public ArrayList <String> getAssignedReviewers() {
		return assignedReviewers;
	}

	/**
	 * Sets the assigned reviewer list
	 * @param assignedReviewers
	 */
	public void setAssignedReviewers(ArrayList<String> assignedReviewers) {
		this.assignedReviewers = assignedReviewers;
	}
	
	/**
	 * Adds a reviewer to the assigned reviewer list
	 * @param reviewer
	 */
	public void addAssignedReviewer(String reviewer) {
		assignedReviewers.add(reviewer);
	}

	/**
	 * @return the uploaded files to get
	 */
	public ArrayList <File> getUploadedFiles() {
		return uploadedFiles;
	}
	
	
	/**
	 * @param uploadedFiles the uploadedFiles to set
	 */
	public void setUploadedFiles(ArrayList<File> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	
	
	/**
	 * Add the uploaded file
	 * @param uploadedFile, file to upload
	 */
	public void addUploadedFile(File uploadedFile) {
		
		uploadedFiles.add(uploadedFile);
		this.updateFile();
	}
}	
