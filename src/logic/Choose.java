package logic;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Choose extends JPanel {

	/**
	 * Create the panel.
	 */
	public Choose(JFrame frame) {
		setLayout(null);
		
		JLabel lblJournalSubmissionSystem = new JLabel("Journal Submission System");
		lblJournalSubmissionSystem.setBounds(123, 28, 219, 42);
		add(lblJournalSubmissionSystem);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login panel = new Login(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnLogin.setBounds(58, 144, 117, 25);
		add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register panel = new Register(frame);
				frame.setContentPane(panel);
				frame.revalidate();
			}
		});
		btnRegister.setBounds(256, 144, 117, 25);
		add(btnRegister);
		
	}

}
