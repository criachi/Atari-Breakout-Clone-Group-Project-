package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class RegisterWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel error;
	private char[] password;
	private String passwordPassed;
	private JPasswordField passwordField_1;
	private char[] adminPassword;
	private String adminPasswordPassed;

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
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
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register as A Player");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(117, 23, 200, 50);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(88, 70, 100, 25);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(88, 100, 100, 25);
		desktopPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(198, 70, 150, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 100, 150, 20);
		desktopPane.add(passwordField);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(228, 190, 91, 23);
		desktopPane.add(btnSignUp);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setBounds(15, 11, 413, 25);
		desktopPane.add(error);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(88, 190, 91, 23);
		desktopPane.add(btnCancel);
		
		JLabel lblPasswordadmin = new JLabel("Password (Admin):");
		lblPasswordadmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPasswordadmin.setBounds(47, 115, 140, 49);
		desktopPane.add(lblPasswordadmin);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(198, 131, 150, 20);
		desktopPane.add(passwordField_1);
		
		/*
		 * Here I set up action listeners, this is the same format for
		 * every action listener
		 */
		btnSignUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSignUpActionPerformed(evt);
			}
		});
		
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
	}
	
	//Need to create a method for every action listener you want
	private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
		//Due to the password being protected, we need to copy the elements of the char array manually into a string
		password = passwordField.getPassword();
		passwordPassed = new String();
		
		for(int i = 0; i < password.length; i++) {
			passwordPassed += password[i];
		}
		
		adminPassword = passwordField_1.getPassword();
		adminPasswordPassed = new String();
		
		for(int i = 0; i < adminPassword.length; i++) {
			adminPasswordPassed += adminPassword[i];
		}
		
		if(textField.getText().length() == 0 || passwordPassed.length() == 0) {
			error.setText("Error: Username or password cannot be empty.");
			return;
		}
		
		if(passwordPassed.trim().length() == adminPasswordPassed.trim().length()) {
			if(passwordPassed.trim().contentEquals(adminPasswordPassed.trim())) {
				error.setText("Error: Password and Admin Password cannot be the same.");
				return;
			}
		}
		
		try {
			if(adminPasswordPassed.isEmpty()) {
				Block223Controller.register(textField.getText(), passwordPassed, null);
			}
			else {
				Block223Controller.register(textField.getText(), passwordPassed, adminPasswordPassed);
			}
		}
		catch(InvalidInputException e) {
			error.setText(e.getMessage());
			return;
		}
				
		
		frame.dispose();
		new LogInWindow();
	}
	
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new WelcomeWindow();
	}
}
