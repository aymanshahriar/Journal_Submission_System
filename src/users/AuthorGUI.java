// credit to: https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html. This was referenced when making the JFileChooser
// Icons made by <a href="https://www.flaticon.com/authors/kiranshastry" title="Kiranshastry">Kiranshastry</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
package users;
import main.*;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import logic.AvailableReviewers;
import logic.Deadline;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CHANGES April 9th, 3:30 AM
 * Altered the Interface:
 *     - Blank space under label "Selected Reviewers" is supposed to changed dynamically...**REMOVED**
 *     		- Is now a button that shows the reviewers an author has selected, 
 *     		  this uses updated method getNames in Author
 *	   - Added label the see journal deadline
 *		**Deadline is not changing for some reason, still working on it**--FIXED
 *			- If deadline has been set to 01/01/0000, either status rejected or accepted has been assigned
 *			  and displays "No deadline pending"
 *		- Upload Files Functionality:
 *			- Can not upload files if it is passed the deadline
 *
 * @author Vi
 *
 */


public class AuthorGUI extends Author{

	/**
	 * Variables of various labels and components
	 */
	public JFrame frame;  // need to make frame public in order to register/login
	private JLabel statusLabel;
	private JLabel reviewerLabel;
	private JButton btnUploadFiles;
	private JComboBox comboBoxAllReviewers;
	private JLabel statusDisplay;
	private JButton btnViewSubmissions;
	private JLabel lblAccountInfo;
	private JTextField textFieldNewRevName;
	private JTextField textFieldNewRevEmail;
	private JButton btnSelectedRev;

	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorGUI window = new AuthorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// the following javadoc tag tells the design window which constructor to select
	/**
	 * @wbp.parser.constructor
	 */
	public AuthorGUI() {
		super();
		initialize();
	}
	
	
	/**
	 * This constructor is used to create account
	 */
	public AuthorGUI(String name, String email, String username, String password) {
		super(name, email, username, password);
		initialize();
	}
	
	/**
	 * This constructor is used when retrieving an account from a text file
	 */
	public AuthorGUI(String name) {
		this.setName(name);
		initialize();
	}
		
	
	/**
	 * This constructor is used when logging in
	 */
	public AuthorGUI(String name, String email, String username, String password, ArrayList<String> nominatedReviewers, 
		ArrayList<File> uploadedFiles, String status, Deadline deadline,  ArrayList<String> assignedReviewer) {
		super(name, email, username, password);
		this.setNominatedReviewers(nominatedReviewers);
		this.setUploadedFiles(uploadedFiles);
		this.setStatus(status);
		this.setDeadline(deadline);
		this.setAssignedReviewers(assignedReviewer);
	
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Author");
		frame.setBounds(100, 100, 616, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// display account information like Name and Email
		lblAccountInfo = new JLabel("Account Info:    Name: " + this.getName() + "   " + "Email:  " + this.getEmail());
		lblAccountInfo.setBounds(22, 12, 414, 14);
		frame.getContentPane().add(lblAccountInfo);
		
		// Display submission status
		statusLabel = new JLabel("Submission Status: " + super.getStatus());
		statusLabel.setBounds(22, 49, 474, 14);
		
		//Display deadline
		Deadline noDate = new Deadline();
		noDate.setDeadline(0, 0, 1);

		if(this.getDeadline().printDeadline().contentEquals(noDate.printDeadline())) {
			JLabel lblDeadlne = new JLabel("Journal Deadline: No pending deadline");
			lblDeadlne.setBounds(22, 76, 374, 16);
			frame.getContentPane().add(lblDeadlne);
			
		} else {
		JLabel lblDeadlne = new JLabel("Journal Deadline: " + this.getDeadline().printDeadline());
		lblDeadlne.setBounds(22, 76, 374, 16);
		frame.getContentPane().add(lblDeadlne);
		}
		
		
		// Implement upload files functionality
		btnUploadFiles = new JButton("Upload Files");
		btnUploadFiles.setBounds(218, 356, 158, 27);
		btnUploadFiles.setIcon(new ImageIcon(AuthorGUI.class.getResource("/Resources/UploadFiles.png")));
		btnUploadFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(getDeadline().beforeDeadline()) {
					
						//Create a file chooser which only allows PDF files to be uploaded
						JFileChooser chooser = new JFileChooser();
						chooser.setDialogTitle("Please upload a PDF");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(null);
						  if(returnVal == JFileChooser.APPROVE_OPTION) {
							  	AuthorGUI journalGUIObject = new AuthorGUI(getName());
							  	journalGUIObject.getUpdate();
							  	File file = chooser.getSelectedFile();

						    	journalGUIObject.addUploadedFile(file);
						    	System.out.println(journalGUIObject.getUploadedFiles().toString());
								journalGUIObject.updateFile();
						  }
					}
				} catch (HeadlessException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		// Implement View/Download Submissions functionality
		btnViewSubmissions = new JButton("View/Download Submisssions");
		btnViewSubmissions.setBounds(179, 393, 257, 28);
		btnViewSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JournalGUI secondObject = new JournalGUI(getName());
				secondObject.getUpdate();
				System.out.println(secondObject.getUploadedFiles().toString());
				setVisibility(false);
				secondObject.setVisibility(true);
			}
		});
		

		
		// Display List of all available reviewers
		reviewerLabel = new JLabel("List of Reviewers:");
		reviewerLabel.setBounds(12, 147, 181, 16);		
				
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
		comboBoxAllReviewers = new JComboBox(reviewerList);
		comboBoxAllReviewers.setBounds(12, 186, 280, 26);
		
		
		// Enable Author to select reviewer from the list of available reviewers
		JButton btnSelectedReviewer = new JButton("Select Reviewer");
		btnSelectedReviewer.setBounds(145, 143, 158, 25); 
		btnSelectedReviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Apple devices will give errors if the below line is not included apparently
				AuthorGUI forApple = new AuthorGUI(getName());
				forApple.getUpdate();
				
				String reviewerName = comboBoxAllReviewers.getSelectedItem().toString();
				String result = "";
				
				// This ensures that a placeholder message is not being selected as a reviewer.
				if (reviewerName.equals("No reviewers currently available")) {
					JOptionPane.showMessageDialog(null, "You can not select a reviewer since there are none available.");
				}
				
				else {
					int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to select this reviewer?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (input == JOptionPane.YES_OPTION) {
							result = forApple.nominateReviewer(reviewerName);
							}
					
						//Error Messages
						if (result.contentEquals("rejected")) {
							JOptionPane.showMessageDialog(null, "You have exceded the maximum reviewer amount.");
							}
					
						if(result.contentEquals("already selected")) {
							JOptionPane.showMessageDialog(null, "You have already selected this reviewer.");
							}
						}
				
			}
		});
		
		
		//Proponents Used to Nominate Reviewer
		JLabel lblNewRevName = new JLabel("Reviewer Name");
		lblNewRevName.setBounds(383, 193, 149, 16);
		frame.getContentPane().add(lblNewRevName);
		
		JLabel lblNewRevEmail = new JLabel("Reviewer Email:");
		lblNewRevEmail.setBounds(383, 244, 149, 16);
		frame.getContentPane().add(lblNewRevEmail);
		
		textFieldNewRevName = new JTextField();
		textFieldNewRevName.setBounds(383, 209, 149, 22);
		frame.getContentPane().add(textFieldNewRevName);
		textFieldNewRevName.setColumns(10);
		
		textFieldNewRevEmail = new JTextField();
		textFieldNewRevEmail.setBounds(383, 263, 149, 22);
		frame.getContentPane().add(textFieldNewRevEmail);
		textFieldNewRevEmail.setColumns(10);
		
		
		// Enable author to select reviewer by typing the reviewer's name in a text box
		JButton btnNewReviewer = new JButton("Nominate Reviewer");
		btnNewReviewer.setBounds(383, 143, 181, 25);
		btnNewReviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Apple devices will give errors if the below line is not included apparently
				AuthorGUI forApple = new AuthorGUI(getName());
				forApple.getUpdate();
				
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to nominate this reviewer?", "Confirm", JOptionPane.YES_NO_OPTION);
				String result = "";
				if(input == JOptionPane.YES_OPTION) {
					String name = textFieldNewRevName.getText();
					String email = textFieldNewRevEmail.getText();
					
					// Error Message if one entry is not provided
					if(name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
		      			JOptionPane.showMessageDialog(null, "Error: One or more of the entries provided is blank.");

					}else {
		      		
						result = forApple.nominateReviewer(name + ", " + email);
						if(result != null) {
							if(result.contentEquals("rejected")) {
								JOptionPane.showMessageDialog(null, "You have exceded the maximum reviewer amount.");
							}
							if(result.contentEquals("already selected")) {
								JOptionPane.showMessageDialog(null, "You have already selected this reviewer.");
							}
						}
		      		}
				}
				
			}				
		});
		
		
		// Add all the buttons, labels, and other components to the frame
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(statusLabel);
		frame.getContentPane().add(reviewerLabel);
		frame.getContentPane().add(comboBoxAllReviewers);
		frame.getContentPane().add(btnUploadFiles);
		frame.getContentPane().add(btnViewSubmissions);
		frame.getContentPane().add(btnSelectedReviewer);
		frame.getContentPane().add(btnNewReviewer);
		
		//This button pulls up the reviewer's revisions for the author.
		JButton btnFeedback = new JButton("View Reviewer Feedback");
		btnFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AuthorFeedBackGUI feedBack = new AuthorFeedBackGUI(getName());
				feedBack.getUpdate();
				feedBack.setVisibility(true);
				feedBack.updateFile();
				
			}
		});
		btnFeedback.setBounds(12, 305, 225, 23);
		frame.getContentPane().add(btnFeedback);
		
		//Button allows to see selected reviewers
		btnSelectedRev = new JButton("View Selected Reviewers");
		btnSelectedRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuthorGUI aGUI = new AuthorGUI(getName());
				aGUI.getUpdate();
				
      			JOptionPane.showMessageDialog(null, "Selected reviewers: " + aGUI.getNames());
			}
		});
		btnSelectedRev.setBounds(12, 240, 225, 25);
		frame.getContentPane().add(btnSelectedRev); 
		
	    //Logout and go to log in screen
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisibility(false);
				MainGUI mainWindow = new MainGUI();
				mainWindow.setVisibility(true);
				
			}
		});
		btnLogout.setBounds(447, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
		
	}
	
	/**
	 * This function sets the visibility of this GUI frame
	 * @param value If set to True, this frame will become visible. If set to false, frame will become invisible
	 */
	public void setVisibility(boolean value) {
		frame.setVisible(value);
	}
}
