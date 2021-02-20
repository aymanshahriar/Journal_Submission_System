package main;


import java.awt.EventQueue;

/**
 * Changes April 3rd, 1:48 PM
 * - Added new parameter deadline to constructors where needed
 * 
 * @Vi
 */

import javax.swing.JFrame;
import logic.Choose;

public class MainGUI {

	public JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
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
	public MainGUI() {
		frame = new JFrame();
		frame.setTitle("SENG 300 Group Project");
		frame.setBounds(100, 100, 535, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Choose panel = new Choose(frame);
		frame.setContentPane(panel);
		frame.revalidate();
	}
	
	/**
	 * This function sets the visibility of this GUI frame
	 * @param value If set to True, this frame will become visible. If set to false, frame will become invisible
	 */
	public void setVisibility(boolean value) {
		frame.setVisible(value);
	}

}
