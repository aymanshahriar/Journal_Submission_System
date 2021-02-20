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
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Register extends JPanel {
	
	/**
	 * Variables of all the textfields
	 */
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	
	/**
	 * Create the panel.
	 */
	public Register(JFrame frame) {
		/**
		 * Using absolute layout
		 */
		setLayout(null);
		
		/**
		 * Labels and textfield declarations:
		 */
		JLabel lblRegisterPage = new JLabel("Register Page");
		lblRegisterPage.setBounds(178, 12, 139, 15);
		add(lblRegisterPage);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 48, 70, 15);
		add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 81, 70, 15);
		add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 108, 90, 15);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 135, 90, 15);
		add(lblPassword);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(120, 46, 114, 19);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(120, 79, 114, 19);
		add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(120, 106, 114, 19);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(120, 133, 114, 19);
		add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		
		
		/**
		 * What happens when the "go back" button is pressed:
		 */
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Once button is pressed, we switch to "Choose" frame
				 */
				Choose panel = new Choose(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnGoBack.setBounds(200, 263, 117, 25);
		add(btnGoBack);
		
		JLabel lblRegisterAs = new JLabel("Register As:");
		lblRegisterAs.setBounds(12, 194, 117, 20);
		add(lblRegisterAs);
		
		
		/**
		 * What happens when "register as author" button is pressed:
		 */
		
		JButton btnAuthorRegister = new JButton("Author");
		btnAuthorRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// retrieve information entered in textfields
				String name = textFieldName.getText();
				String email = textFieldEmail.getText();
				String username = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				
				// First check if all fields are filled.
				if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Error: One or more fields are blank.\nPlease fill in all fields to continue.");
				}
				
				// Then check if the name already exists.
				else if (User.fileExists(name)) {
					JOptionPane.showMessageDialog(frame, "Error: This name already exists.");
				}
				
				/**
				// Then check if password length is at least 8 characters
				else if (password.length() < 8) {
					JOptionPane.showMessageDialog(frame, "Error: Please enter a password with at least 8 characters.");
				}
				*/
	
				else {
					// create new instance of author
					AuthorGUI aGUI = new AuthorGUI(name, email, username, password);
					
					// save this author instance as a text file
					aGUI.updateFile();
					
					// add author to list of all authors
					AvailableAuthors lst = new AvailableAuthors();
					lst.getUpdate();
					lst.addAuthor(aGUI.getName());
					
					// makes sure this new list is saved
					lst.updateFile();
					
					// print out arraylist to see if it works
					ArrayList<String> lstNames = lst.getAuthorsList();
					for (int i = 0; i < lstNames.size(); i++) {
						System.out.println(lstNames.get(i));
					}
									
					// exit registration window, enter the author window
					aGUI.frame.setVisible(true);
					frame.setVisible(false);
					
				}
			}
		});
		btnAuthorRegister.setBounds(120, 192, 117, 25);
		add(btnAuthorRegister);
		
		
		/**
		 * What happens when the "register as Reviewer" button is pressed:
		 */
		
		JButton btnReviewerRegister = new JButton("Reviewer");
		btnReviewerRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// retrieve information entered in textfields
				String name = textFieldName.getText();
				String email = textFieldEmail.getText();
				String username = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				
				// First check if all fields are filled.
				if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Error: One or more fields are blank.\nPlease fill in all fields to continue.");
				}
				
				// Then check if the name already exists.
				else if (User.fileExists(name)) {
					JOptionPane.showMessageDialog(frame, "Error: This name already exists.");
				}
				
				/**
				// Then check if password length is at least 8 characters
				else if (password.length() < 8) {
					JOptionPane.showMessageDialog(frame, "Error: Please enter a password with at least 8 characters.");
				}
				*/
				
				// TODO create instance of RegisterGUI
				else {
					// create new instance of reviewer
					ReviewerGUI rGUI = new ReviewerGUI(true, false, name,email, password, username);
					// Save this reviewer instance as a text file
					rGUI.updateFile();
					
					// add reviewer to list of all reviewers
					AvailableReviewers lst = new AvailableReviewers();
					lst.getUpdate();
					lst.addReviewer(rGUI.getName());
					
					// makes sure this new list is saved
					lst.updateFile();
					
					// print out arraylist to see if it works
					ArrayList<String> lstNames = lst.getAvailableReviewersList();
					for (int i = 0; i < lstNames.size(); i++) {
						System.out.println(lstNames.get(i));
					}
					
					// exit registration window, enter the reviewer window
					rGUI.frame.setVisible(true);
					frame.setVisible(false);
					
				}
			}
		});
		btnReviewerRegister.setBounds(249, 192, 117, 25);
		add(btnReviewerRegister);

		
		
		/**
		 * What happens when the register as Editor button is pressed:
		 */
		
		JButton btnEditorRegister = new JButton("Editor");
		btnEditorRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Retrieve information entered in textfields
				String name = textFieldName.getText();
				String email = textFieldEmail.getText();
				String userName = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				
				
				// First check if all fields are filled.
				if (name.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Error: One or more fields are blank.\nPlease fill in all fields to continue.");
				}
				
				
				// Then check if the name already exists.
				else if (User.fileExists(name)) {
					JOptionPane.showMessageDialog(frame, "Error: This name already exists.");
				}
				
				/**
				// Then check if password length is at least 8 characters
				else if (password.length() < 8) {
					JOptionPane.showMessageDialog(frame, "Error: Please enter a password with at least 8 characters.");
				}
				*/
				
				else {
					// create new instance of Editor
					EditorGUI eGUI = new EditorGUI(name, email, userName, password);
					
					// save this editor instance as a text file
					eGUI.updateFile();
					
					// exit registration window, enter the editor window
					eGUI.frame.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		btnEditorRegister.setBounds(378, 192, 117, 25);
		add(btnEditorRegister);
		
	}
}
