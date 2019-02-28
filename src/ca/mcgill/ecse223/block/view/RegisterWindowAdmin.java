package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JButton;
import java.awt.Color;

public class RegisterWindowAdmin {

	private JFrame frame;
	private JPasswordField passwordField;
	private String username;
	private String userPassword;
	private JLabel error;
	private char[] password;
	private String passwordPassed;
	

	/**
	 * Create the application.
	 */
	public RegisterWindowAdmin(String givenUsername, String givenPassword) {
		initialize();
		username = givenUsername;
		userPassword = givenPassword;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register As An Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(144, 31, 180, 50);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(99, 92, 125, 50);
		desktopPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 105, 125, 20);
		desktopPane.add(passwordField);
		
		JButton btnSkip = new JButton("Skip");
		btnSkip.setBounds(99, 190, 91, 23);
		desktopPane.add(btnSkip);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(242, 190, 91, 23);
		desktopPane.add(btnSignUp);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setBounds(186, 11, 125, 20);
		desktopPane.add(error);
		
		btnSignUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSignUpActionPerformed(evt);
			}
		});
		
		btnSkip.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSkipActionPerformed(evt);
			}
		});
	}
	
	private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
		password = passwordField.getPassword();
		passwordPassed = new String();
		
		for(int i = 0; i < password.length; i++) {
			passwordPassed += password[i];
		}
		
		try {
			Block223Controller.register(username, userPassword, passwordPassed);
		}
		catch (InvalidInputException e) {
			error.setText(e.getMessage());
			return;
		}
		
		frame.dispose();
		new LogInWindow();
	}
	
	private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Block223Controller.register(username, userPassword, null);
		}
		catch (InvalidInputException e) {
			error.setText(e.getMessage());
			return;
		}
		
		frame.dispose();
		new LogInWindow();
	}
}
