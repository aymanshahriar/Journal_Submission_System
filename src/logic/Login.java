package logic;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import users.AuthorGUI;
import users.EditorGUI;
import users.ReviewerGUI;
import users.User;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.Container;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	
	
	
	/**
	 * Variables of all the textfields
	 */
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Create the panel.
	 */
	public Login(JFrame frame) {
		/**
		 * Using Absolute layout
		 */
		setLayout(null);
		
		/**
		 * Label and textfield declarations:
		 */
		JLabel lblLogin = new JLabel("Login Page");
		lblLogin.setBounds(175, 41, 114, 15);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(35, 100, 114, 15);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(35, 147, 114, 15);
		add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(233, 98, 114, 19);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(233, 145, 114, 19);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblLoginAs = new JLabel("Login As:");
		lblLoginAs.setBounds(12, 210, 84, 15);
		add(lblLoginAs);
		
		
		/**
		 * What happens when the  "Go back" button is pressed:
		 */
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choose panel = new Choose(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnGoBack.setBounds(192, 263, 117, 25);
		add(btnGoBack);
		
		
		
		/**
		 * What happens when "register as author" button is pressed:
		 */
		JButton btnAuthor = new JButton("Author");
		btnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// retrieve information entered in textfields
				String name = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				// first check if the user file actually exists
				if (!User.fileExists(name)) {
					JOptionPane.showMessageDialog(frame, "Error: User by this name does not exist.");
				}
				// retrieve the editor account with the specified name, check if the password matches
				else {
					AuthorGUI temp = new AuthorGUI(name, "default1", "default1", "default1");
					temp.getUpdate();
					System.out.println(temp.getName() + "  " + temp.getPassword());
					AuthorGUI aGUI = new AuthorGUI(temp.getName(), temp.getEmail(), temp.getUsername(), temp.getPassword(),
							temp.getNominatedReviewers(), temp.getUploadedFiles(), temp.getStatus(), temp.getDeadline(), temp.getAssignedReviewers());
					
					
					//fixed reviewer list error
					temp.updateFile();
					
					String actualPassword = aGUI.getPassword();
					System.out.println(aGUI.getName() + " " + aGUI.getPassword() + " " + aGUI.getEmail());
					if (password.equals(actualPassword)) {
						
						// if password matches, open AuthorGUI instance
						aGUI.frame.setVisible(true);
						frame.setVisible(false);
						
						//JOptionPane.showMessageDialog(frame, "password matches");
					}
					else {
						JOptionPane.showMessageDialog(frame, "Error: Invalid Password.");
						//do not delete this line as it will cause the reviewerList array to be empty
						temp.updateFile();
					}

				}
			}
		});
		btnAuthor.setBounds(97, 205, 117, 25);
		add(btnAuthor);
		
		/**
		 * What happens when "register as reviewer" button is pressed:
		 */
		JButton btnReviewer = new JButton("Reviewer");
		btnReviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// retrieve information entered in textfields
				String name = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				if(name.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid name.");
				}
				else {
					// first check if the user file actually exists
					if (!User.fileExists(name)) {
						JOptionPane.showMessageDialog(frame, "Error: User by this name does not exist.");
					}
					// retrieve the editor account with the specified name, check if the password matches
					else {
						ReviewerGUI temp = new ReviewerGUI(true, false, name, "default1", "default1", "default1");
						temp.getUpdate();
	
						ReviewerGUI rGUI = new ReviewerGUI(temp.getStatus(), temp.isFeedbackProvided(), temp.getName(), temp.getEmail(), temp.getPassword(), temp.getUsername(),
								temp.getMajorRevisions(), temp.getMinorRevisions(), temp.getAcceptOrReject(), temp.getAssignedAuthorName());
						
						
						String actualPassword = rGUI.getPassword();
						System.out.println(rGUI.getName() + " " + rGUI.getPassword() + " " + rGUI.getEmail());
						if (password.equals(actualPassword)) {
							
							// if password matches, open EditorGUI instance
							System.out.println(rGUI.getAcceptOrReject());
							rGUI.frame.setVisible(true);
							frame.setVisible(false);
							
							//JOptionPane.showMessageDialog(frame, "password matches");
						}
						else {
							JOptionPane.showMessageDialog(frame, "Error: Invalid Password.");
							//do not delete this line as it will cause the reviewerList array to be empty
							temp.updateFile();
						}
	
					}
				}
			}
		});
		btnReviewer.setBounds(240, 205, 117, 25);
		add(btnReviewer);
		
		
		/**
		 * What happens when "register editor" button is pressed
		 */
		JButton btnEditor = new JButton("Editor");
		btnEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// retrieve information entered in textfields
				String name = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				// first check if the user file actually exists
				if (!User.fileExists(name)) {
					JOptionPane.showMessageDialog(frame, "Error: User by this name does not exist.");
				}
				// retrieve the editor account with the specified name, check if the password matches
				else {
					EditorGUI temp = new EditorGUI(name, "default1", "default1", "default1");
					temp.getUpdate();
					EditorGUI eGUI = new EditorGUI(temp.getName(), temp.getEmail(), temp.getUsername(), temp.getPassword());
					
					String actualPassword = eGUI.getPassword();
					System.out.println(eGUI.getName() + " " + eGUI.getPassword() + " " + eGUI.getEmail());
					if (password.equals(actualPassword)) {
						
						// if password matches, open EditorGUI instance
						eGUI.frame.setVisible(true);
						frame.setVisible(false);
						
						//JOptionPane.showMessageDialog(frame, "password matches");
					}
					else {
						JOptionPane.showMessageDialog(frame, "Error: Invalid Password.");
						//do not delete this line as it will cause the reviewerList array to be empty
						temp.updateFile();
					}

				}		

				
			}
		});
		btnEditor.setBounds(383, 205, 117, 25);
		add(btnEditor);

	}
}
