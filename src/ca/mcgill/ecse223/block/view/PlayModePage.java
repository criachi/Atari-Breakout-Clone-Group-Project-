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
	private JButton playBtn;
	private JButton testBtn;
	private JLabel errorMessage;
	
	Block223Listener gameListener;

	/**
	 * Create the application.
	 */
	public PlayModePage() {
		System.out.println("hehe");
		initialize();
		// THIS IS WRONG MY COMMENT
		// if u hve a current playable game set alrdy, then it will display the info of where u left off 
		// if u dont, it will display empty play area
		refresh();
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
		
		
		playBtn = new JButton("PLAY");
		playBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playBtnActionPerformed(evt);
			}
		});
		playBtn.setFont(new Font("Tahoma", Font.PLAIN, 44));
		
		testBtn = new JButton("TEST");
		testBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testBtnActionPerformed(evt);
			}
		});
		testBtn.setFont(new Font("Tahoma", Font.PLAIN, 44));
		System.out.println(Block223Application.getCurrentPlayableGame());
		
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
								.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
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
							.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
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
		
		if(Block223Application.getCurrentPlayableGame() == null) {
			hallOfFamePanel.setVisible(false);
			lblHallOfFame.setVisible(false);
			previousEntriesBtn.setVisible(false);
			followingEntriesBtn.setVisible(false);
			playBtn.setVisible(false);
		}
		else {
			testBtn.setVisible(false);
		}
		System.out.println("Setup complete.");
	}
	
	private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		if(Block223Application.getCurrentPlayableGame().getPlayer() == null) {
			new AdminDashBoardPage();
			return;
		}
		new PlayerDashBoardPage();
	}

	
	public void refresh() {
		// we shld have a conditional: if the currentPlayableGame is set, then refresh blockassignments and ball and paddle and set them obv 
		// if not, then just leave it empty (for test game) 
		System.out.println("UI");
		//PlayedGameLevel.setBlockAssignments();
		//PlayedGameLevel.refreshGame();
	}
	
	public void gameOver() {
		//reset paddle & ball
		//clear block assignments
		//show caio message
	}
	
	public void deletePage() {
		frame.dispose();
	}
	
	private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {
		playBtn.setVisible(false);
		//System.out.println("Is Visible?"+playBtn.isVisible());
		
		
		// initiating a thread to start listening to keyboard inputs
		gameListener = new Block223Listener();
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				// in the actual game, add keyListener to the game window
				frame.addKeyListener(gameListener);
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		// to be on the safe side use join to start executing thread t1 before executing the next thread
		try {
			t1.join();
		} catch (InterruptedException e1) {
			
		}
		//initiating a thread to start the game loop 
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				try {
					Block223Controller.startGame(PlayModePage.this);
					playBtn.setVisible(true);
				} catch (InvalidInputException e) {
				}
			}
		};
		//old code before doing move paddle; not bad 
		/*try {
			Block223Controller.startGame(PlayModePage.this);
		} catch (InvalidInputException e) {
			playBtn.setVisible(true);
			errorMessage.setText(e.getMessage());
			return;
		}*/
		
		Thread t2 = new Thread(r2);
		t2.start();
	}
	
	
	public String takeInputs() {
		if(gameListener == null) {
			return "";
		}
		// check the takeInputs() implementation in the Block223Listener class
		// it saves the keyInputs string in a temp variable and then clears it
		return gameListener.takeInputs();
	}
	private void testBtnActionPerformed(java.awt.event.ActionEvent evt) {
		testBtn.setVisible(false);
		
		// initiating a thread to start listening to keyboard inputs
		gameListener = new Block223Listener();
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				// in the actual game, add keyListener to the game window
				frame.addKeyListener(gameListener);
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		// to be on the safe side use join to start executing thread t1 before executing the next thread
		try {
			t1.join();
		} catch (InterruptedException e1) {
			
		}
		//initiating a thread to start the game loop 
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				try {
					Block223Controller.testGame(PlayModePage.this);
					playBtn.setVisible(true);
				} catch (InvalidInputException e) {
				}
			}
		};
		Thread t2 = new Thread(r2);
		t2.start();
	}
	}

