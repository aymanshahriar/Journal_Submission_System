package logic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




/**
 * This class contains the submission deadline.
 */

/**
 * CHANGES April 9th, 3:45 AM
 * New Instance Variable: DateFormat formatString puts all dates in the format mm/dd/yyyy for printing
 * New Methods:
 * 		- String printDeadline(): converts the date into a printable string, used for AuthorGUI and such
 * 		- boolean beforeDeadline(): Used to prevent file uploads after the deadline date
 * 		- boolean beforeDeadline(int year, int month, int day): Checks that the new deadline date to be set
 * 																is valid (not in the past, etc)
 * 																- used by the editor
 * Aside:
 * 		- Thanks for the notes Ayman! Deleted the testing at the bottom, hope it is not big issue.
 * @author Vi
 *
 */



public class Deadline implements Serializable {

	// class variable, used for serialization
	private static final long serialVersionUID = 1L;
	
	
	// This is the variable that contains the date of the deadline
	// It is of type calendar
	Calendar deadline = Calendar.getInstance();
	
	DateFormat formatString = new SimpleDateFormat("MM/dd/yyyy");
	
	
	/**
	 * Default constructor
	 */
	public Deadline() {
		
		// set the deadline to the initial deadline
		// order: year, month, day, hour, second, millisecond
		//		**Months are ordered 0 to 11
		deadline.set(2020, 11, 31, 0, 0, 0);
		
	}
	
	
	// Getter method, returns the stored deadline.
	// The returned object will be of type calendar
	public Calendar getDeadline() {
		return this.deadline;
	}
	
	
	// Setter method #1
	public void setDeadline(int year, int month, int day) {
		// The last two zeroes are to set the seconds and milliseconds to 0;
		this.deadline.set(year, month, day, 0, 0, 0);
	}
	
	// Setter method #2, this is used for retrieving info from text file during serialization
	public void setDeadline(Calendar c) {
		// The last two zeroes are to set the seconds and milliseconds to 0;
		this.deadline = c;
	}

	
	/**
	 * Serialization stuff
	 */
	
	//Updates the saved data for the object 
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File("Deadline" + ".txt"));
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
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File("Deadline"+".txt")));
	        Deadline d = (Deadline) inObject.readObject();
	        inObject.close();
	        
	        // retrieve all the stored attributes
	        this.setDeadline(d.getDeadline());
	        
		}catch(Exception e) {
			System.out.println("1: "+e.getMessage());
		}
	}
	
	
	/**
	 * Methods
	 */
	
	/**
	 * converts deadline to printable format
	 * @return string date
	 */
	public String printDeadline() {
		
		Date date = this.deadline.getTime();
		
		return formatString.format(date);
	}
	
	/**
	 * Checks if current date is before deadline
	 * @return false if deadline passed, true otherwise
	 * @throws ParseException
	 */
	public boolean beforeDeadline() throws ParseException {
		
		Calendar calendar = Calendar.getInstance();
		
		Date currentDate = formatString.parse(formatString.format(calendar.getTime()));
		Date dueDate = formatString.parse(formatString.format(deadline.getTime()));
		
		long difference = dueDate.getTime() - currentDate.getTime();
		
		if(difference < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Takes a new date and compares with the previous deadline, checks that new date is is valid
	 * @param year
	 * @param month
	 * @param date
	 * @return true if new date is after the current day, false otherwise
	 * @throws ParseException
	 */
	public boolean beforeDeadline(int year, int month, int date) throws ParseException {
		
		Calendar calendar = Calendar.getInstance();
		
		Date currentDate = formatString.parse(formatString.format(calendar.getTime()));
		calendar.set(year, month-1, date);
		
		Date newDate = formatString.parse(formatString.format(deadline.getTime()));
		long difference = newDate.getTime() - currentDate.getTime();
		if(difference < 0) {
			return false;
		}
		
		return true;
	}
	
}
