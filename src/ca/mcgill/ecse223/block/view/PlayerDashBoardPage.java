package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ca.mcgill.ecse223.block.controller.Block223Controller;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerDashBoardPage {

	private JFrame frame;
	private JButton logOutBtn;
	private JLabel lblGamesAvailable;
	private JComboBox gamesAvailableComboBox;
	private JButton startGameBtn;
	private JLabel lblContinueGames;
	private JComboBox continueGamesComboBox;
	private JButton continueGameBtn;

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
		frame.setBounds(100, 100, 641, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Log Out 
		logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutBtnActionPerformed(evt);
			}
		});
		
		lblGamesAvailable = new JLabel("Games Available");
		lblGamesAvailable.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		gamesAvailableComboBox = new JComboBox();
		
		startGameBtn = new JButton("Start Game ");
		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblContinueGames = new JLabel("Continue Games");
		lblContinueGames.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		continueGamesComboBox = new JComboBox();
		
		continueGameBtn = new JButton("Continue Game");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblGamesAvailable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(gamesAvailableComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(continueGameBtn)
										.addComponent(startGameBtn))
									.addGap(260))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(logOutBtn)
									.addGap(37))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(continueGamesComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblContinueGames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGamesAvailable)
						.addComponent(logOutBtn))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(gamesAvailableComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(startGameBtn))
					.addGap(51)
					.addComponent(lblContinueGames)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(continueGamesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(continueGameBtn))
					.addContainerGap(170, Short.MAX_VALUE))
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
