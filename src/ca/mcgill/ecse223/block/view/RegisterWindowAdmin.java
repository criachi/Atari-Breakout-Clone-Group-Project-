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
import javax.swing.JButton;

public class RegisterWindowAdmin {

	private JFrame frame;
	private JPasswordField passwordField;


	/**
	 * Create the application.
	 */
	public RegisterWindowAdmin() {
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
		
		JLabel lblNewLabel = new JLabel("Sign Up As Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(144, 31, 150, 50);
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
		
		btnSignUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSignUpActionPerformed(evt);
			}
		});
	}
	
	private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new LogInWindow();
	}
}
