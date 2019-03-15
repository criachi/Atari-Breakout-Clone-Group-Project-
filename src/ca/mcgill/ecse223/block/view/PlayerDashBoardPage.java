package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ca.mcgill.ecse223.block.controller.Block223Controller;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class PlayerDashBoardPage {

	private JFrame frame;

	private JLabel lblWeAreWorking;
	private JLabel lblCheckBackIn;
	private JButton logOutBtn;

	/**
	 * Create the application.
	 */
	public PlayerDashBoardPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 638, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lblWeAreWorking = new JLabel("We are working on allowing you to play games!");
		lblWeAreWorking.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		lblCheckBackIn = new JLabel("Check back in with us later!");
		lblCheckBackIn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		// Log Out 
		logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutBtnActionPerformed(evt);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(lblWeAreWorking)
					.addGap(96))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(143)
					.addComponent(lblCheckBackIn)
					.addContainerGap(257, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(469, Short.MAX_VALUE)
					.addComponent(logOutBtn)
					.addGap(122))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(logOutBtn)
					.addGap(18)
					.addComponent(lblWeAreWorking)
					.addGap(18)
					.addComponent(lblCheckBackIn)
					.addContainerGap(295, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		Block223Controller.logout();
		frame.dispose();
		new WelcomeWindow();
		//new Block223Page().setVisible(true);
	}
}
