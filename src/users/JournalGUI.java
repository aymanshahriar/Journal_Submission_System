package users;
//learned copying files for download from: https://stackoverflow.com/questions/16891792/how-to-read-pdf-file-and-write-it-to-outputstream

import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JournalGUI extends Author{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private int arrayListSize;
	private JList<File> lstUploads;
	
	

	private JButton btnOpen;
	private JButton btnDownload;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalGUI window = new JournalGUI();
					window.setVisibility(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	
	public JournalGUI() {
		super();
		initialize();
	}
	
	/**
	 * Constructor with just name as the parameter
	 */
	public JournalGUI(String name) {
		super();
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
	 * Add relevant action events
	 */
	private void addEvents() {
	
		// This will play as soon as the JournalGUI window is closed
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				// Open the AuthorGUI window with all the relevant information
				AuthorGUI goBack = new AuthorGUI(getName(), getEmail(), getUsername(), getPassword(),getNominatedReviewers(), getUploadedFiles(), getStatus(), getDeadline(), getAssignedReviewers());
				goBack.getUpdate();
				goBack.setVisibility(true);
			}
		});
		
	
		
		
		// When the open button is clicked
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				// Stores a reference to the absolute path of file name
				String fileName = getUploadedFiles().get(getLstUploads().getSelectedIndex()).getAbsolutePath();

				// Opens the file on desktop
				Desktop.getDesktop().open(new java.io.File(fileName));
				
				
				//Normally "-1" will be thrown if the user does not select one of the items in the Jlist which is out of bounds
				}catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No File Selected.\nPlease select a file.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}
		});
		
		// When the download button is clicked
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//Store the file name
					File originalFile = new File (getUploadedFiles().get(getLstUploads().getSelectedIndex()).getAbsolutePath());
				
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
						
							// Copy the file into output strema
							OutputStream copyFile = new FileOutputStream (filechoice.getSelectedFile().getAbsolutePath() + ".pdf");
						
							// Create a byter array for storage
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
					}
					
				}
				
				// Normally "-1" will be thrown if the user does not select one of the items in the Jlist which is out of bounds
				catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No File Selected.\nPlease select a file.");
				}
				
				// Catch any IO exceptions
				catch (IOException e2) {
					e2.printStackTrace();
				}
				
					
			}
				
			
		
			
		});
		
		
		

		
	}
	

	/**
	 * Initialize relevant components
	 */
	private void initComponents() {
		getUpdate();


	
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		
		//The bottom can be enabled to close application on exit
	
		//getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 219);
				
		btnOpen = new JButton("Open");
		btnOpen.setBounds(75, 242, 103, 23);
		frame.getContentPane().setLayout(null);
		
		
		
		setLstUploads(getUploadedFiles());
		
		updateFile();		

		scrollPane.setViewportView(getLstUploads());
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(btnOpen);
		
		btnDownload = new JButton("Download");
		
		btnDownload.setBounds(280, 242, 117, 23);
		frame.getContentPane().add(btnDownload);
		
	}
	
	
	/**
	 * Return the list for the model
	 * @return lstUploads, the upload list for the model
	 */
	public JList<File> getLstUploads() {
		return lstUploads;
	}

	/**
	 * Set the list for the model
	 * @param uploadedFiles, the arrayList which holds all the files uploaded by the Author
	 */
	public void setLstUploads(ArrayList <File> uploadedFiles) {
		lstUploads = new JList(uploadedFiles.toArray());
	}

	/**
	 * return the GUI frame
	 * @return frame, the frame of the GUI
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Set the GUI frame
	 * @param frame, the frame of the GUI
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Set the window visible or invisible
	 * @param value, true or false
	 */
	public void setVisibility(boolean value) {
		frame.setVisible(value);
	}
	
	
}
