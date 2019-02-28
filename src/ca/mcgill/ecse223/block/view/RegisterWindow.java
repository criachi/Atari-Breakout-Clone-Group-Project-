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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register as A Player");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(119, 42, 200, 50);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(88, 113, 100, 25);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(88, 150, 100, 25);
		desktopPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(198, 117, 150, 20);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(198, 154, 150, 20);
		desktopPane.add(passwordField);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(228, 190, 91, 23);
		desktopPane.add(btnSignUp);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setBounds(85, 11, 300, 20);
		desktopPane.add(error);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(88, 190, 91, 23);
		desktopPane.add(btnCancel);
		
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
	
	private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
		if(textField.getText().length() == 0 || passwordField.getPassword().toString().length() == 0) {
			error.setText("Error: Username or password cannot be empty.");
			return;
		}
		
		password = passwordField.getPassword();
		passwordPassed = new String();
		
		for(int i = 0; i < password.length; i++) {
			passwordPassed += password[i];
		}
		
		frame.dispose();
		new RegisterWindowAdmin(textField.getText(), passwordPassed);
	}
	
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new WelcomeWindow();
	}
}
