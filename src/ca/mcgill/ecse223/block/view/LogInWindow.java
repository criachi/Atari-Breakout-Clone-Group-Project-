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

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogInWindow extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel error;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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
		
		btnLogIn.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLogInActionPerformed(evt);
			}
			
		});
	}

	private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Block223Controller.login(textField.getText(), passwordField.getPassword().toString());
		}
		catch (InvalidInputException e) {
			error.setText(e.getMessage());
			return;
		}
		
		frame.dispose();
		new Block223Page().setVisible(true);
	}
}
