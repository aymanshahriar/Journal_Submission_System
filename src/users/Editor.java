package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Editor extends User implements Serializable {
		
	private static final long serialVersionUID = 4L;
	
	/**
	// assign three reviewers to the author
	public void assignReviewer(Author author, Reviewer reviewer1, Reviewer reviewer2,  Reviewer reviewer3) {
			
	}
		
	//fetch the recommended reviewers of the author, will probably return arraylist
	public void fetchReviewers(Author author) {
			
	}
		
	public void acceptSuggestions() {
			
	}
	*/
	
	public Editor() {
		super();
	}
	
	public Editor(String name, String email, String username, String password) {
		super(name, email, username, password);
	}
	
	// stores Editor instance in text file
	public void updateFile() {
		try {
			FileOutputStream out = new FileOutputStream(new File(super.getName() + ".txt"));
	        ObjectOutputStream outObject = new ObjectOutputStream(out);
	        outObject.writeObject(this);

	        out.close();
	        outObject.close();
		}catch(Exception e) {
			
		}
	}
	

	/**
	 * Retrieve editor instance from text file
	 * @param name: the name of the editor account that you want
	 * @return the account with the specified name is returned
	 * For example: Editor AymanEditorAccount = Editor.getUpdate("Ayman");
	 */
	public void getUpdate() {
		try {
			ObjectInputStream inObject = new ObjectInputStream(new FileInputStream(new File(getName() + ".txt")));
	        Editor editor = (Editor) inObject.readObject();
	        inObject.close();
	        
	        this.setName(editor.getName());
	        this.setEmail(editor.getEmail());
	        this.setUsername(editor.getUsername());
	        this.setPassword(editor.getPassword());
	        
	        
		}catch(Exception e) {
			System.out.println("editor does not exist");
			e.printStackTrace();
			
		}
		
	}

	
	/**
	 * This method has been moved to User class
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
	*/
	
	
	//test
	public static void main(String[] args) {
		/**
		Editor e = new Editor("name2", "email", "password123", "username");
		e.updateFile();
		System.out.println(fileExists("name1"));
		System.out.println(fileExists("name2"));
		
		Editor n = Editor.getUpdate("name2");
		System.out.println(n.getPassword());
		*/
	}
	
	
	
	
	
	
	
	
	
	
}
