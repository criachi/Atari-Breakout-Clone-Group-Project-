package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayModePage implements Block223PlayModeInterface {

	private JFrame frame;
	private JButton btnPlay;
	private JButton btnTest;
	private JLabel errorMessage;

	/**
	 * Create the application.
	 */
	public PlayModePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JLabel lblCurrentLevel = new JLabel("Current Level");
		lblCurrentLevel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblLives = new JLabel("Lives:");
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backBtnActionPerformed(evt);
			}
		});
		
		JPanel hallOfFamePanel = new JPanel();
		hallOfFamePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new LineBorder(new Color(0, 255, 0), 8)));
		
		JLabel lblHallOfFame = new JLabel("Hall of Fame");
		lblHallOfFame.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton previousEntriesBtn = new JButton("Previous Entries");
		
		JButton followingEntriesBtn = new JButton("Following Entries");
		
		
		btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bntPlayActionPerformed(evt);
			}
		});
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 44));
		
		btnTest = new JButton("TEST");
		btnTest.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bntTestActionPerformed(evt);
			}
		});
		btnTest.setFont(new Font("Tahoma", Font.PLAIN, 44));
		System.out.println(Block223Application.getCurrentPlayableGame());
		
		if(Block223Application.getCurrentPlayableGame() == null) {
			System.out.println("Setting btnplay to invisible");
			btnPlay.setVisible(false);
		}
		else {
			System.out.println("Setting btntest to invisible");
			btnTest.setVisible(false);
		}
		
		errorMessage = new JLabel("");
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(120)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCurrentLevel)
									.addGap(152)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLives)
										.addComponent(lblScore))))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
							.addComponent(lblHallOfFame)
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(325)
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(previousEntriesBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(followingEntriesBtn)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurrentLevel)
								.addComponent(lblLives))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblScore)
							.addGap(136)
							.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnTest, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblHallOfFame)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(previousEntriesBtn)
							.addComponent(followingEntriesBtn)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public String takeInputs() {
		
		return null;
	}
	
	private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new PlayerDashBoardPage();
	}

	
	public void refresh() {
		//PlayedGameLevel.setBlockAssignments();
		//PlayedGameLevel.refreshGame();
	}
	
	private void bntPlayActionPerformed(java.awt.event.ActionEvent evt) {
		btnPlay.setVisible(false);
		System.out.println("Is Visible?"+btnPlay.isVisible());

		try {
			Block223Controller.startGame(PlayModePage.this);
		} catch (InvalidInputException e) {
			btnPlay.setVisible(true);
			errorMessage.setText(e.getMessage());
			return;
		}
	}
	
	private void bntTestActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
}
