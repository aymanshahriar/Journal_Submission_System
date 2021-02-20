package users;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthorFeedBackGUI extends Author {

	//Declare instance variables
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnViewFeedback;
	private JList<Reviewer> lstReviewers;
	private ArrayList<ReviewerGUI> reviewerList = new ArrayList<ReviewerGUI> ();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorFeedBackGUI window = new AuthorFeedBackGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuthorFeedBackGUI() {
		super();
		initialize();
	}
	
	// the following javadoc tag tells the design window which constructor to select
		/**
		 * @wbp.parser.constructor
		 */
	
	/**
	 * This constructor is used when retrieving an account from a text file
	 */
	public AuthorFeedBackGUI(String name) {
		this.setName(name);
		initialize();
	}
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initComponents();
		addEvents();
		
	}
	/**
	 * add the events of the frame
	 */
	private void addEvents() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Open the AuthorGUI window with all the relevant information
				AuthorGUI goBack = new AuthorGUI(getName(), getEmail(), getUsername(), getPassword(),getNominatedReviewers(), getUploadedFiles(), getStatus(), getDeadline(), getAssignedReviewers());
				goBack.getUpdate();
				goBack.setVisibility(true);
			}
		});
		
		
		btnViewFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Name: " + getName());
				System.out.println("before object: " + getAssignedReviewers().toString());
				try {
					String reviewername = getAssignedReviewers().get(getLstReviewers().getSelectedIndex());
					ReviewerGUI rGUI = new ReviewerGUI(reviewername);
					rGUI.getUpdate();
					
					// Error Message if a non-existent username is given.
		      		if (!User.fileExists(reviewername) || reviewername.isEmpty()) {
		      			JOptionPane.showMessageDialog(null, "Error: This reviewer doesn't exist.");
		      		}
		      		
		      		else {
		      			
		      			// Gets the Revisions from the User.
		      			ArrayList major = rGUI.getMajorRevisions();
		      			ArrayList minor = rGUI.getMinorRevisions();
		      			
		      			// No Revisions
		      			if (major.isEmpty() && minor.isEmpty()) {
		      				JOptionPane.showMessageDialog(null, "There are no revisions suggested by this user.");
		      			}
		      			
		      			// Minor Revisions Only 
		      			else if (major.isEmpty()) {
		      				JOptionPane.showMessageDialog(null, "There are no Major Revisions suggested by this user.\nMinor Revisions: " + minor.toString().replaceAll("(^\\[|\\]$)", ""));
		      			}
		      			
		      			// Major Revisions Only
		      			else if (minor.isEmpty()) {
		      				JOptionPane.showMessageDialog(null, "There are no Minor Revisions suggested by this user.\nMajor Revisions: " + major.toString().replaceAll("(^\\[|\\]$)", ""));
		      			}
		      			
		      			// Both, the .replaceAll() method removes the ArrayList brackets.
		      			else {
		      				JOptionPane.showMessageDialog(null, "Major Revisions: " + major.toString().replaceAll("(^\\[|\\]$)", "") + "\nMinor Revisions: " + minor.toString().replaceAll("(^\\[|\\]$)", ""));
		      			}
		      		}
				}catch(IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No reviewer selected.\nPlease choose a reviewer.");
				}
			}	
			
		});
	}

	/**
	 * Initialize the components of the frame
	 */
	private void initComponents() {
		AuthorGUI aGUI = new AuthorGUI (getName());
		aGUI.getUpdate();
		aGUI.updateFile();
		
		makeReviewerObjects();
		
	
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 222);
		frame.getContentPane().add(scrollPane);
		
		setLstReviewers(getReviewerList());
		scrollPane.setViewportView(getLstReviewers());
		
		btnViewFeedback = new JButton("View Feedback");
		
		btnViewFeedback.setBounds(151, 240, 155, 29);
		frame.getContentPane().add(btnViewFeedback);
		
	


		
	}

	/**
	 * This function sets the visibility of this GUI frame
	 * @param value If set to True, this frame will become visible. If set to false, frame will become invisible
	 */
	public void setVisibility(boolean value) {
		frame.setVisible(value);
	}
	
	/**
	 * Set the list for the model
	 * @param uploadedFiles, the arrayList which holds all the files uploaded by the Author
	 */
	public void setLstReviewers(ArrayList<ReviewerGUI> arrayList) {
		lstReviewers = new JList(arrayList.toArray());
	}

	/**
	 * 
	 * @return lstReviewers, the Jlist storing the reviewers
	 */
	public JList<Reviewer> getLstReviewers() {
		return lstReviewers;
	}
	
	/**
	 * Turn the string array of reviewers that we have into reviewer objects to utilize their toString method
	 */
	public void makeReviewerObjects() {
		// Make an author object and update it so that it has all neccessary information
		AuthorGUI aGUI = new AuthorGUI(getName());
		aGUI.getUpdate();
		// Convert string ArrayList into object ArrayList
		for (int i = 0; i < aGUI.getAssignedReviewers().size(); i ++) {
			ReviewerGUI reviewer = new ReviewerGUI(aGUI.getAssignedReviewers().get(i));
			reviewer.getUpdate();
			reviewerList.add(reviewer);
			// Always update when done even when thought to be unnecessary as it will cause serialization issues otherwise
			reviewer.updateFile();
		}
		// Update authorGUI as we called getUpdate() earlier
		aGUI.updateFile();
	}

	/**
	 * 
	 * @return reviewerList, the list of reviewers to be shown in the frame
	 */
	public ArrayList<ReviewerGUI> getReviewerList() {
		return reviewerList;
	}

	/**
	 * 
	 * @param reviewerList, the list of reviewers to be shown in the frame
	 */
	public void setReviewerList(ArrayList<ReviewerGUI> reviewerList) {
		this.reviewerList = reviewerList;
	}


}
