package users;
//learned copying files for download from: https://stackoverflow.com/questions/16891792/how-to-read-pdf-file-and-write-it-to-outputstream

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.MainGUI;

import java.awt.event.ActionListener;

public class ReviewerGUI extends Reviewer{

	// set to public
	public JFrame frame;
	
	public void viewDocument() {
		System.out.println(getAssignedAuthorName());
		AuthorGUI aGUI = new AuthorGUI(getAssignedAuthorName());
		aGUI.getUpdate();
		System.out.println(aGUI.getUploadedFiles().toString());
		aGUI.updateFile();
		try {
			
			// Stores a reference to the absolute path of file name
			String fileName = aGUI.getUploadedFiles().get(aGUI.getUploadedFiles().size()-1).getAbsolutePath();

			// Opens the file on desktop
			Desktop.getDesktop().open(new java.io.File(fileName));
			
			
			//Normally "-1" will be thrown if the user does not select one of the items in the Jlist which is out of bounds
			}catch(ArrayIndexOutOfBoundsException e1) {
				if(aGUI.getName().equalsIgnoreCase("no one")) {
					
					// Message appears if there is no authors assigned to reviewer.
					JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");

				}
				else {
					
					// Message appears if no submission has been given by author.
					JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}						
		}

	
	
	public void download() {
		AuthorGUI aGUI = new AuthorGUI(getAssignedAuthorName());
		aGUI.getUpdate();
		try {
			
			//Store the file name
			File originalFile = new File (aGUI.getUploadedFiles().get(aGUI.getUploadedFiles().size()-1).getAbsolutePath());
		
			//declare both a buffered reader and a print writer
			BufferedReader br;
			PrintWriter fw;
			
			String line;
			
			// Open up the JFileChooser so the user can only select from PDF files
			JFileChooser filechoice = new JFileChooser();
			filechoice.setDialogTitle("Download Window");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");

			filechoice.setFileFilter(filter);
			int recieve = filechoice.showSaveDialog(null);
			if (recieve == JFileChooser.APPROVE_OPTION) {
			
				// Copy the file into output stream
				OutputStream copyFile = new FileOutputStream (filechoice.getSelectedFile().getAbsolutePath() + ".pdf");
			
				// Create a byte array for storage
				byte []buffer = new byte [8192];
				
				
				// Actually copy the information from the original file
				InputStream is = new FileInputStream(originalFile);
				
				int c = 0;

		        while ((c = is.read(buffer, 0, buffer.length)) > 0) {
		            copyFile.write(buffer, 0, c);
		            copyFile.flush();
		        }
		        
		        // Make sure to close after opening
		        copyFile.close();
		        is.close();
		        aGUI.updateFile();
			}
			
		}
		
		// Normally "-1" will be thrown if the user does not select one of the items in the Jlist which is out of bounds
		catch(ArrayIndexOutOfBoundsException e1) {
			
			// Message appears if there is no authors assigned to reviewer.
			if(aGUI.getName().equalsIgnoreCase("no one")) {
				JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");

			}
			
			// Message appears if no submission has been given by author.
			else {
				JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
			}
		}
		
		// Catch any IO exceptions
		catch (IOException e2) {
			e2.printStackTrace();
		}
		
			
	}

	
	/**
	 * Main method 
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewerGUI window = new ReviewerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	/**
	 * Create the application.
	 * @wbp.parser.constructor 
	 */
	public ReviewerGUI() {
		super();
		getUpdate();
		initialize();
	}
	
	// currently not using this constructor for registering
	public ReviewerGUI(boolean status, boolean feedbackProvided, String name, String email, String password, String username) {
		super(status,feedbackProvided, name, email, password, username);
		initialize();
		
	}
	
	// currently using this constructor for logging in
	public ReviewerGUI(boolean status, boolean feedbackProvided, String name, String email, String password, String username, 
			ArrayList<String> majorRevisions, ArrayList<String> minorRevisions, String acceptOrReject, String assignedAuthorName) {
		super(status,feedbackProvided, name, email, password, username);
		this.setMajorRevisions(majorRevisions);
		this.setMinorRevisions(minorRevisions);
		this.setAcceptOrReject(acceptOrReject);
		this.setAssignedAuthorName(assignedAuthorName);
		initialize();
		
	}
	
	public ReviewerGUI(String name) {
		super();
		this.setName(name);
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("Reviewer");
		frame.setBounds(100, 100, 579, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Information");
		lblNewLabel.setBounds(10, 11, 432, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: " + this.getName() + "   " + "Email: " + this.getEmail());
		lblNewLabel_1.setBounds(10, 34, 410, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("View Document");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Mac needs this line or there will be errors
				ReviewerGUI reviewer = new ReviewerGUI(getName());
				reviewer.getUpdate();
				reviewer.viewDocument();
				reviewer.updateFile();
			}
		});
		btnNewButton.setBounds(88, 229, 223, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel acceptStatus = new JLabel(getAcceptOrReject());
		acceptStatus.setHorizontalAlignment(SwingConstants.LEFT);
		acceptStatus.setBounds(350, 345, 199, 16);
		frame.getContentPane().add(acceptStatus);
		
		JLabel lblAcceptanceStatus = new JLabel("Acceptance Status:");
		lblAcceptanceStatus.setBounds(349, 331, 143, 15);
		frame.getContentPane().add(lblAcceptanceStatus);
		
		JButton btnNewButton_1 = new JButton("Accept");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Author aGUI = new AuthorGUI(getAssignedAuthorName());
				aGUI.getUpdate();
				//Mac needs this line or there will be errors
				if(aGUI.getUploadedFiles().size() > 0) {
					ReviewerGUI reviewer = new ReviewerGUI(getName());
					reviewer.getUpdate();
					reviewer.accept();
					reviewer.updateFile();
					acceptStatus.setText(reviewer.getAcceptOrReject());
					reviewer.updateFile();

				}
				
				// Message appears if there is no authors assigned to reviewer.
				else if(aGUI.getName().equalsIgnoreCase("no one")){
					JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
				}
				
				// Message appears if no submission has been given by author.
				else {
					JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
				}
				aGUI.updateFile();
				
				
			}
		});
		btnNewButton_1.setBounds(347, 373, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Reject");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Author aGUI = new AuthorGUI(getAssignedAuthorName());
				aGUI.getUpdate();
				//Mac needs this line or there will be errors
				if(aGUI.getUploadedFiles().size() > 0) {
					ReviewerGUI reviewer = new ReviewerGUI(getName());
					reviewer.getUpdate();
					reviewer.reject();
					reviewer.updateFile();
					acceptStatus.setText(reviewer.getAcceptOrReject());
					reviewer.updateFile();

				}
				
				// Message appears if there is no authors assigned to reviewer.
				else if(aGUI.getName().equalsIgnoreCase("no one")){
					JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
				}
				
				// Message appears if no submission has been given by author.
				else {
					JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
				}
				aGUI.updateFile();
				
			}
		});
		btnNewButton_1_1.setBounds(452, 373, 97, 25);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Mac needs this line or there will be errors
				ReviewerGUI reviewer = new ReviewerGUI(getName());
				reviewer.getUpdate();
				reviewer.download();
				reviewer.updateFile();
			}
		});
		btnDownload.setBounds(347, 409, 202, 23);
		frame.getContentPane().add(btnDownload);
		
		JButton btnMinorRevision = new JButton("Suggest Minor Revision");
		btnMinorRevision.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Author aGUI = new AuthorGUI(getAssignedAuthorName());
				aGUI.getUpdate();
				//Mac needs this line or there will be errors
				if(aGUI.getUploadedFiles().size() > 0) {
					ReviewerGUI reviewer = new ReviewerGUI(getName());
					reviewer.getUpdate();
					reviewer.minorRevision();
					reviewer.updateFile();
				}
				
				// Message appears if there is no authors assigned to reviewer.
				else if(aGUI.getName().equalsIgnoreCase("no one")){
					JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");
				}
				
				// Message appears if no submission has been given by author.
				else {
					JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
				}
				aGUI.updateFile();
			}
		});
		btnMinorRevision.setBounds(88, 299, 223, 23);
		frame.getContentPane().add(btnMinorRevision);
		
		JButton btnMajorRevision = new JButton("Suggest Major Revision");
		btnMajorRevision.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Author aGUI = new AuthorGUI(getAssignedAuthorName());
				aGUI.getUpdate();
				//Mac needs this line or there will be errors
				if(aGUI.getUploadedFiles().size() > 0) {
					ReviewerGUI reviewer = new ReviewerGUI(getName());
					reviewer.getUpdate();
					reviewer.majorRevision();
					reviewer.updateFile();
				}
				
				// Message appears if there is no authors assigned to reviewer.
				else if(aGUI.getName().equalsIgnoreCase("no one")){
					JOptionPane.showMessageDialog(null, "Sorry, looks like no authors have been assigned to you.");

				}
				
				// Message appears if no submission has been given by author.
				else {
					JOptionPane.showMessageDialog(null, "Sorry, looks like there are no submissions right now.");
				}
				aGUI.updateFile();
			}
		});
		btnMajorRevision.setBounds(88, 265, 223, 23);
		frame.getContentPane().add(btnMajorRevision);
		
		
		// This displays the assigned author name
		JLabel lblAssignedReviewerName = new JLabel("Assigned Author Name: " + this.getAssignedAuthorName());
		lblAssignedReviewerName.setBounds(12, 89, 299, 15);
		frame.getContentPane().add(lblAssignedReviewerName);
		
	    //Logout and go to log in screen
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainGUI mainWindow = new MainGUI();
				mainWindow.setVisibility(true);
			}
		});
		btnLogout.setBounds(432, 6, 117, 29);
		frame.getContentPane().add(btnLogout);
	}
	
	/**
	 * overriding the toString() method  
	 */
	public String toString(){
		ReviewerGUI rGUI = new ReviewerGUI(getName());
		rGUI.getUpdate();
		if(rGUI.isFeedbackProvided()) {
			rGUI.updateFile();
			return "Feedback Available";  
		}
		else {
			rGUI.updateFile();
			return "No Feedback";
		}
	}  
}
