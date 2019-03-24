package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogInWindow extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel error;
	private char[] password;
	private String passwordPassed;

	/**
	 * Create the application.
	 */
	public LogInWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Frame is set up, and the window size is set
		//The default close operation must be exit_on_close, dispose_on_close will close the whole app once one page is killed
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		//Just about everything here is set up by the windowbuilder
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setBounds(196, 30, 46, 20);
		lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		desktopPane.add(lblLogIn);
		
		JLabel lblNewLabel = new JLabel("Username: ");
		lblNewLabel.setBounds(75, 85, 82, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		desktopPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(190, 85, 150, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setBounds(75, 140, 78, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		desktopPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 140, 150, 20);
		desktopPane.add(passwordField);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogIn.setBounds(188, 195, 75, 23);
		desktopPane.add(btnLogIn);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setBounds(100, 60, 250, 20);
		desktopPane.add(error);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(75, 196, 75, 23);
		desktopPane.add(btnCancel);
		
		/*
		 * Here I set up action listeners. This is the same format 
		 * for every action listener
		 */
		btnLogIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLogInActionPerformed(evt);
			}
		});
		
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
	}

	//A method is created for every action listener
	private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {
		/*
		 * I first grab the password as a char array,
		 * and convert it into a string for use by the controller
		 */
		password = passwordField.getPassword();
		passwordPassed = new String();
		
		for(int i = 0; i < password.length; i++) {
			passwordPassed += password[i];
		}
		
		
		try {
			Block223Controller.login(textField.getText(), passwordPassed);
		}
		catch (InvalidInputException e) {
			error.setText(e.getMessage());
			return;
		}
		
		TOUserMode loggedUser = Block223Controller.getUserMode();
		if(loggedUser.getMode() == Mode.Play) {
			frame.dispose();
			new PlayerDashBoardPage();
		}
		else if(loggedUser.getMode() == Mode.Design) {
			frame.dispose();
			new AdminDashBoardPage();
		}
		else {
			error.setText("Error: No User is currently logged in.");
			return;
		} 
	}
	
	//Cancel just closes the page and brings you back to the welcome window
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new WelcomeWindow();
	}
}
