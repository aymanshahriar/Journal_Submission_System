package users;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.AvailableAuthors;
import logic.AvailableReviewers;
import main.MainGUI;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * CHANGES April 9rd, 3:53 AM
 * Altered Interface:
 * 		- Removed authorName textfield that was used for assigning status
 * 			- use the author specified in combo box
 * 		- Top left corner: Assign reviewer function related components
 * 		- Top right corner: See recommended and all reviewers
 * 		- Bottom left Corner: assign a new date that author needs to upload by
 * 			-Years currently range from 2020 to 2024, very flexible
 * 		- Bottom right corner: Assigns status
 * 			- now status is a combo box, as I needed specific strings to deal with
 * 			  deadlines
 * 		-Middle right: See reviewers verdict
 * ADDED Button:
 * 		- current Status: getter
 * NOTES:
 * 		- perhaps we should replace the final reviewer name txtfield with a 
 * 		  combo box for convenience
 * 		- apologies for the messed up interface as it appears on different operating systems
 * 		- **Some testing should be done on the deadline functionalities, did not do much
 * 			error handling
 * @author Vi
 *
 */


public class EditorGUI extends Editor {

	/**
	 * Variables of various GUI components
	 */
	public JFrame frame;    // this needs to be public
	private JTextField textFieldReviewer1;
	private JTextField textFieldReviewer2;
	private JTextField textFieldReviewer3;
	private JTextField textFieldReviewerStatusName;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorGUI window = new EditorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Default constructor, not really used for anything
	 */
	public EditorGUI() {
		super();
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public EditorGUI(String name, String email, String username, String password) {
		super(name, email, username, password);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// set up the GUI frame
		frame = new JFrame();
		frame.setTitle("Editor");
		frame.setBounds(100, 100, 635, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// labels
		JLabel lblNameEmail = new JLabel("Editor Information: Name: "+ this.getName() + "   " + "Email:  " + this.getEmail());
		lblNameEmail.setBounds(34, 23, 476, 15);
		frame.getContentPane().add(lblNameEmail);
		
		JLabel lblAuthorName1 = new JLabel("Author Name");
		lblAuthorName1.setBounds(12, 91, 140, 15);
		frame.getContentPane().add(lblAuthorName1);
	    
	    JLabel lblReviewerName = new JLabel("Reviewer Name");
	    lblReviewerName.setBounds(12, 156, 128, 15);
	    frame.getContentPane().add(lblReviewerName);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(253, 304, 86, 25);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblViewWantedReviewers = new JLabel("View Recommended Reviewers");
		lblViewWantedReviewers.setBounds(222, 86, 247, 25);
		frame.getContentPane().add(lblViewWantedReviewers);
	    
		JLabel lblAllReviewers = new JLabel("All Reviewers:");
	    lblAllReviewers.setBounds(445, 91, 130, 15);
	    frame.getContentPane().add(lblAllReviewers);
	    
	    JLabel lblReviewerStatusName = new JLabel("Reviewer Name");
	    lblReviewerStatusName.setBounds(225, 227, 114, 14);
	    frame.getContentPane().add(lblReviewerStatusName);
		
	    // textfield that takes name of reviewer when retrieving the verdict of a reviewer
	    textFieldReviewerStatusName = new JTextField();
	    textFieldReviewerStatusName.setBounds(225, 254, 114, 20);
	    frame.getContentPane().add(textFieldReviewerStatusName);
	    textFieldReviewerStatusName.setColumns(10);
	    
	    
	    
		// Create an array of all available authors. This array will be used in several functionalities		
		AvailableAuthors availAuthors = new AvailableAuthors();
		availAuthors.getUpdate();
		
		ArrayList<String> availableAuthorsList = availAuthors.getAuthorsList();
		
		if(availableAuthorsList.size() == 0) {
			availableAuthorsList.add("No authors currently available");
		}
		
		String[] authorList = new String[availableAuthorsList.size()];
		for(int i = 0; i < availableAuthorsList.size(); i++) {
			authorList[i] = availableAuthorsList.get(i);
		}
	    
		
	    // Create an array of all available reviewers. This array will be used in several functionalities
		AvailableReviewers availReviewers = new AvailableReviewers();
		availReviewers.getUpdate();
			
		ArrayList<String> availableReviewers = availReviewers.getAvailableReviewersList();
			
		if(availableReviewers.size() == 0) {
			availableReviewers.add("No reviewers currently available");
		}
			
		String[] reviewerList = new String[availableReviewers.size()];
		for(int i = 0; i < availableReviewers.size(); i++) {
			reviewerList[i] = availableReviewers.get(i);
		}
	    
	
	    // Implement the "Assign reviewer to author" functionality
		
		// But first, create two JComboBox that will display lists of all authors and reviewers
		JComboBox comboBoxAuthor1 = new JComboBox(authorList);
	    comboBoxAuthor1.setBounds(12, 119, 140, 24);
	    frame.getContentPane().add(comboBoxAuthor1);
	    
	    JComboBox comboBoxReviewer1 = new JComboBox(reviewerList);
	    comboBoxReviewer1.setBounds(12, 179, 140, 24);
	    frame.getContentPane().add(comboBoxReviewer1);
	    	    
		
		JButton btnAssignReviewer = new JButton("Assign Reviewer");
		btnAssignReviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Retrieve names of authors and reviewers specified in the two JComboBox
				String authorName = comboBoxAuthor1.getSelectedItem().toString();
				String reviewerName = comboBoxReviewer1.getSelectedItem().toString();
				
				
				// Check if there are any authors or reviewers in the system.
				// If they are missing, create a pop up saying that there are no authors and/or editors in the system
				if (authorName.equals("No authors currently available") && reviewerName.equals("No reviewers currently available")) {
					JOptionPane.showMessageDialog(null, "Error: There are no Authors or Reviewers registered in the system.");
				
				}else if (authorName.equals("No authors currently available")) {
					JOptionPane.showMessageDialog(null, "Error: There are no Authors registered in the system.");
				
				}else if (reviewerName.equals("No reviewers currently available")) {
					JOptionPane.showMessageDialog(null, "Error: There are no Reviewers registered in the system.");
				}
				
				// Error Message if reviewer is not on file
				else if (!User.fileExists(reviewerName)) {
	      			JOptionPane.showMessageDialog(null, "Error: This reviewer doesn't exist.");
	      		}
				// Error Message if an invalid author is given.
	      		else if(!User.fileExists(authorName)) {
	      			JOptionPane.showMessageDialog(null, "Error: This author doesn't exist.");
	      		}
	      		else {
	      			AuthorGUI aGUI = new AuthorGUI(authorName);
	      			aGUI.getUpdate();
	      			if(!aGUI.getAssignedReviewers().contains(reviewerName)) {
	      				aGUI.addAssignedReviewer(reviewerName);
		      			ReviewerGUI rGUI = new ReviewerGUI(true, false, reviewerName, "default1", "default1", "default1");
			      		// Gets the current accept/reject status.
			      		rGUI.getUpdate();
			      		
			    		rGUI.setAssignedAuthorName(authorName);
		      			JOptionPane.showMessageDialog(null, reviewerName + " assigned to " + authorName + ".");
		      			aGUI.updateFile();
						rGUI.updateFile();
	      			}
	      			else {
	      				JOptionPane.showMessageDialog(null, reviewerName + " is already assigned to " + authorName + "!");
	      				aGUI.updateFile();
	      			}							
	      		}
			}
		});
		btnAssignReviewer.setBounds(12, 214, 172, 41);
		frame.getContentPane().add(btnAssignReviewer);
		
	  
		String[] statusOptions = {"Accept", "Reject", "Major Revision", "Minor Revision"};
	    JComboBox comboBoxStatus = new JComboBox(statusOptions);
	    comboBoxStatus.setBounds(222, 335, 130, 22);
	    frame.getContentPane().add(comboBoxStatus);
		
	    
		// Implements the "Assign status to author" functionality
		JButton btnAssignStatus = new JButton("Assign Status");
		btnAssignStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get name of specified author from textfield
				String authorName = comboBoxAuthor1.getSelectedItem().toString();
				
	      		// message appears when trying to assign status to a non-existent author
				if (!User.fileExists(authorName) || authorName.isEmpty()) {
	      			JOptionPane.showMessageDialog(null, "Error: Could not assign status because the author does not exist.");
	      		}
				
				// If author exists, then the author is retrieved and assigned the specified status
				else {
					// retrieve author  
					AuthorGUI aGUI = new AuthorGUI(authorName);
					aGUI.getUpdate();
				
					// retrieve status
					String status = comboBoxStatus.getSelectedItem().toString();
				
					// set status of author
					aGUI.setStatus(status);
					aGUI.setDeadline(status);
				
					// save new author data
					aGUI.updateFile();
					
					// Confirmation Message
					JOptionPane.showMessageDialog(null, "Status Assigned Successfully.");
				}	
			}
		});
		btnAssignStatus.setBounds(390, 326, 138, 41);
		frame.getContentPane().add(btnAssignStatus);

		
		// Implement the "editor can view suggested reviewers for each author" functionality
	    
		// But first, create a JComboBox, add the list of all authors to it
		JComboBox comboBoxAuthor = new JComboBox(authorList);
		comboBoxAuthor.setBounds(223, 119, 174, 24);
		frame.getContentPane().add(comboBoxAuthor);
		
		JButton btnViewReviewers = new JButton("View");
	    btnViewReviewers.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		// retrieve name of author
				String authorName = comboBoxAuthor.getSelectedItem().toString();
				
				// retrieve author account with the specified name
				AuthorGUI aGUI = new AuthorGUI(authorName);
				aGUI.getUpdate();
				
				// retrieve author's list of nominated reviewers
				ArrayList<String> nominatedReviewers = aGUI.getNominatedReviewers();
				
				// This string will be displayed after we click "view" button
				String message = "";
				
				// If author has not nominated any reviewers, let this be the message to display
				if (nominatedReviewers.size() == 0) {
					message = aGUI.getName() + " has not nominated any reviewers.";
				}
				
				// If author has nominated at least one reviewer, then display this message
				else {
					message = "The reviewers that " + aGUI.getName() + " selected are:";
					for (int i = 0; i < nominatedReviewers.size(); i++) {
						message += "\n" + Integer.toString(i+1) + ") " + nominatedReviewers.get(i);
					}
				}
				
				// Show the message using a pop-up window
				JOptionPane.showMessageDialog(null, message);
	    	}
	    });
	    btnViewReviewers.setBounds(222, 151, 117, 25);
	    frame.getContentPane().add(btnViewReviewers);
	    
	      	      
		// Implements the functionality "editor can view list of all reviewers"
		JComboBox comboBoxAllReviewers = new JComboBox(reviewerList);
		comboBoxAllReviewers.setBounds(439, 119, 166, 24);
		frame.getContentPane().add(comboBoxAllReviewers);
		

	    // Implement the functionality "editor can check the verdict (accept/reject) of each reviewer"
	    JButton btnCheckVerdict = new JButton("Check Reviewer's Verdict");
	    btnCheckVerdict.addMouseListener(new MouseAdapter() {
	    	@Override
	      	public void mouseClicked(MouseEvent e) {
	    		// Get name of specified reviewer from textfield.
	      		String reviewername = textFieldReviewerStatusName.getText();
	      		
	      		ReviewerGUI rGUI = new ReviewerGUI(reviewername);
	      		
	      		// Gets the current accept/reject status.
	      		rGUI.getUpdate();
	      		
	      		// Error Message if an invalid username is given.
	      		if (!User.fileExists(reviewername) || reviewername.isEmpty()) {
	      			JOptionPane.showMessageDialog(null, "Error: This reviewer doesn't exist.");
	      		}
	      		
	      		// Retrieves the status that the Reviewer gave.
	      		else {
	      			JOptionPane.showMessageDialog(null, "The Reviewer's Verdict Is: " + rGUI.getAcceptOrReject() + ".");
	      		}
	      		
	    }});
	    btnCheckVerdict.setBounds(390, 248, 215, 33);
	    frame.getContentPane().add(btnCheckVerdict);
	    
	    
	    //Components to change deadline functionality
	    JLabel lblDeadline = new JLabel("Change first upload deadline:");
	    lblDeadline.setBounds(12, 293, 220, 16);
	    frame.getContentPane().add(lblDeadline);
	    
	    String[] yearList = new String[5];
		for(int i = 0; i < 5; i++) {
			yearList[i] = Integer.toString(2020 + i);
		}
	    JComboBox comboBoxYear = new JComboBox(yearList);
	    comboBoxYear.setBounds(12, 322, 86, 22);
	    frame.getContentPane().add(comboBoxYear);
	    
	    String[] monthList = {"January", "Februrary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    JComboBox comboBoxMonth = new JComboBox(monthList);
	    comboBoxMonth.setBounds(12, 368, 86, 22);
	    frame.getContentPane().add(comboBoxMonth);
	    
	    String[] dayList = new String[31];
	    for(int i = 0; i < 31; i++) {
	    	dayList[i] = Integer.toString(i+1);
	    }
	    
	    JComboBox comboBoxDay = new JComboBox(dayList);
	    comboBoxDay.setBounds(12, 412, 86, 22);
	    frame.getContentPane().add(comboBoxDay);
	    
	    JButton btnDeadline = new JButton("Change");
	    
	    btnDeadline.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		//referes to index to create less hassel
	    		int year = comboBoxYear.getSelectedIndex() + 2020;
	    		int month = comboBoxMonth.getSelectedIndex();
	    		int day = comboBoxDay.getSelectedIndex() + 1;
	    		
				String authorName = comboBoxAuthor.getSelectedItem().toString();
				if (!User.fileExists(authorName) || authorName.isEmpty()) {
	      			JOptionPane.showMessageDialog(null, "Error: Could not set deadline because the author does not exist.");
	      		}else {
				
				AuthorGUI aGUI = new AuthorGUI(authorName);
				aGUI.getUpdate();
				
				try {
					String result = aGUI.setDeadline(year, month, day);
					
					//Can only change the deadline if a status has not been assigned
					if(result.contains("Changed")) {
						JOptionPane.showMessageDialog(null, "Deadline for " + authorName + " has been set to " + aGUI.getDeadline().printDeadline() + ".");
					
					}else if(result.contentEquals("Not changed")) {
						JOptionPane.showMessageDialog(null, "Error: Author status has been set or date is invalid.");
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	    		aGUI.updateFile();
	    		System.out.println(aGUI.getDeadline().printDeadline());
	    	}
	    	}
	    });
	    
	    btnDeadline.setBounds(110, 368, 97, 25);
	    frame.getContentPane().add(btnDeadline);
	    
	    
	    //Lets editor see status of author
	    JButton btnSeeStatus = new JButton("Current Status");
	    btnSeeStatus.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		String authorName = comboBoxAuthor1.getSelectedItem().toString();
				
	      		// message appears when trying to assign status to a non-existent author
				if (!User.fileExists(authorName) || authorName.isEmpty()) {
	      			JOptionPane.showMessageDialog(null, "Error: No Author Selected.");
	      		}
				
				// If author exists, then the author is retrieved and assigned the specified status
				else {
				
					// retrieve author  
					AuthorGUI aGUI = new AuthorGUI(authorName);
					aGUI.getUpdate();
				
					// retrieve status
					String status = comboBoxStatus.getSelectedItem().toString();
	      			JOptionPane.showMessageDialog(null, "Author: " + authorName + " Status: " + aGUI.getStatus());
				}
	    		
	    	}
	    });
	    btnSeeStatus.setBounds(390, 380, 160, 25);
	    frame.getContentPane().add(btnSeeStatus);
	    
	    //Logout and go to log in screen
	    JButton btnLogout = new JButton("Logout");
	    btnLogout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
				MainGUI mainWindow = new MainGUI();
				mainWindow.setVisibility(true);
	    	}
	    });
	    btnLogout.setBounds(488, 17, 117, 29);
	    frame.getContentPane().add(btnLogout);
	      
	}
	
	
	
	// This method is probably not being used anywhere, but still keeping it just in case
	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}
}
