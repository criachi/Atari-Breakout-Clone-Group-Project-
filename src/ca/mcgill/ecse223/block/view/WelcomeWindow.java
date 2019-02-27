package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class WelcomeWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	

	/**
	 * Create the application.
	 */
	public WelcomeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblWelcomeToBlock = new JLabel("Welcome to Block223! Please choose an option below to continue!");
		lblWelcomeToBlock.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblWelcomeToBlock.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToBlock.setBackground(Color.BLACK);
		lblWelcomeToBlock.setBounds(78, 46, 459, 100);
		desktopPane.add(lblWelcomeToBlock);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(141, 157, 120, 30);
		desktopPane.add(btnSignUp);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(369, 157, 120, 30);
		desktopPane.add(btnLogIn);
		
		btnLogIn.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLogInActionPerformed(evt);
				
			}
			
		});
	}
	
	private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new Block223Page().setVisible(true);
		
	}
}
