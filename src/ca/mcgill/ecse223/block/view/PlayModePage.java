package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.PlayedGame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PlayModePage implements Block223PlayModeInterface {

	private JFrame frame;
	private JButton playBtn;
	private JButton testBtn;
	private JLabel errorMessage;
	private JLayeredPane playLevelLayout;
	Block223Listener gameListener;
	private JButton previousEntriesBtn;
	private JButton followingEntriesBtn;
	private JLabel currentLevelLbl;
	private JLabel livesLbl;
	private JLabel scoreLbl;
	private JLabel levelLbl;
	private JTextArea dummyArea;
	private JButton logOutBtn;
	private JLabel lblCurrentLevel;
	private JLabel lblLives;
	private JPanel hallOfFamePanel;
	private JPanel newHallOfFamePanel;
	private int hallOfFameStartEntry = 1;
	private int hallOfFameEndEntry = 10;
	private JLabel lblHallOfFame;
	private JLabel lblScore;
	private JButton backBtn;
	private JLabel YOUWONlbl;
	private JLabel YOULOSTLbl;
	private JLabel hofEntry1;
	private GroupLayout groupLayout;

	/**
	 * Create the application.
	 */
	public PlayModePage() {
		////System.out.println("hehe");
		initialize();
		// THIS IS WRONG MY COMMENT
		// if u hve a current playable game set alrdy, then it will display the info of where u left off 
		// if u dont, it will display empty play area
		refresh(); // refresh shld call repaint on the JComponent PlayLevelLayout
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 952, 778);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lblCurrentLevel = new JLabel("Current Level");
		lblCurrentLevel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblLives = new JLabel("Lives:");
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		backBtn = new JButton("Back");
		backBtn.setFocusable(false);
		backBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backBtnActionPerformed(evt);
			}
		});
		
		hallOfFamePanel = new HallOfFameView(hallOfFameStartEntry, hallOfFameEndEntry);
		hallOfFamePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new LineBorder(new Color(0, 255, 0), 8)));
		
		lblHallOfFame = new JLabel("Hall of Fame");
		lblHallOfFame.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		previousEntriesBtn = new JButton("Previous Entries");
		previousEntriesBtn.setFocusable(false);
		previousEntriesBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previousEntriesBtnActionPerformed(evt);
			}
		});
		
		
		followingEntriesBtn = new JButton("Following Entries");
		followingEntriesBtn.setFocusable(false);
		followingEntriesBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				followingEntriesBtnActionPerformed(evt);
			}
		});
		
		playBtn = new JButton("PLAY");
		playBtn.setFocusable(false);
		playBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playBtnActionPerformed(evt);
			}
		});
		playBtn.setFont(new Font("Tahoma", Font.PLAIN, 44));
		
		testBtn = new JButton("TEST");
		testBtn.setFocusable(false);
		testBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testBtnActionPerformed(evt);
			}
		});
		testBtn.setFont(new Font("Tahoma", Font.PLAIN, 44));
	
		errorMessage = new JLabel("");
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		playLevelLayout = new PlayLevelLayout();
		playLevelLayout.setPreferredSize(new Dimension(390,390));
		
		currentLevelLbl = new JLabel("");
		
		livesLbl = new JLabel("");
		
		scoreLbl = new JLabel("");
		
		levelLbl = new JLabel("");
		
		dummyArea = new JTextArea();
		dummyArea.setVisible(false);
		
		logOutBtn = new JButton("Log Out");
		logOutBtn.setFocusable(false);
		logOutBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutBtnActionPerformed(evt);
			}
		});
		
		hofEntry1 = new JLabel("");
		hofEntry1.setSize(new Dimension(10,10));
		groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(25)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
												.addGap(30)
												.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
												.addGap(124))
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(lblCurrentLevel)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																	.addGap(245)
																	.addComponent(lblLives))
																.addGroup(groupLayout.createSequentialGroup()
																	.addGap(18)
																	.addComponent(currentLevelLbl))
																.addGroup(groupLayout.createSequentialGroup()
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(levelLbl))))
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(15)
															.addComponent(dummyArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblScore)))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(logOutBtn))
													.addGroup(groupLayout.createSequentialGroup()
														.addGap(18)
														.addComponent(livesLbl))
													.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(scoreLbl)))
												.addGap(59))))
									.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(playLevelLayout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(126)))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(hofEntry1, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(100)
									.addComponent(lblHallOfFame))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(591, Short.MAX_VALUE)
							.addComponent(previousEntriesBtn)
							.addGap(26)
							.addComponent(followingEntriesBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurrentLevel)
								.addComponent(currentLevelLbl)
								.addComponent(levelLbl)))
						.addComponent(logOutBtn))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLives)
										.addComponent(livesLbl)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblHallOfFame)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblScore)
										.addComponent(scoreLbl))
									.addGap(45)
									.addComponent(playLevelLayout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(hofEntry1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(dummyArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(followingEntriesBtn)
							.addComponent(previousEntriesBtn)
							.addComponent(backBtn))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		YOUWONlbl = new JLabel("YOU WON!");
		YOUWONlbl.setFont(new Font("Tahoma", Font.PLAIN, 35));
		YOUWONlbl.setBounds(93, 155, 191, 69);
		YOUWONlbl.setVisible(false);
		playLevelLayout.add(YOUWONlbl);
		
		YOULOSTLbl = new JLabel("CIAO :(");
		YOULOSTLbl.setHorizontalAlignment(SwingConstants.CENTER);
		YOULOSTLbl.setFont(new Font("Tahoma", Font.PLAIN, 35));
		YOULOSTLbl.setBounds(79, 216, 210, 50);
		YOULOSTLbl.setVisible(false);
		playLevelLayout.add(YOULOSTLbl);
		frame.getContentPane().setLayout(groupLayout);
		
		if(Block223Controller.getUserMode().getMode() == Mode.Design) {
			hallOfFamePanel.setVisible(false);
			lblHallOfFame.setVisible(false);
			previousEntriesBtn.setVisible(false);
			followingEntriesBtn.setVisible(false);
			playBtn.setVisible(false);
		}
		else {
			testBtn.setVisible(false);
		}
		
		refreshHallOfFame();
	}
	
	private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		PlayedGame currentGame = Block223Application.getCurrentPlayableGame();
		if(currentGame != null) {
			if(Block223Application.getCurrentPlayableGame().getPlayer() == null) {
				Block223Application.setCurrentPlayableGame(null);
				new AdminDashBoardPage();
				return;
			}
		} else { 
			if(Block223Controller.getUserMode().getMode() == Mode.Design) {
				Block223Application.setCurrentPlayableGame(null);
				// carefule w/ this
				Block223Application.setCurrentGame(null);
				new AdminDashBoardPage();
				return;
			}
		}
		new PlayerDashBoardPage();
	}

	public void previousEntriesBtnActionPerformed(java.awt.event.ActionEvent evt) {		
		if(hallOfFameStartEntry != 1) {
			hallOfFameStartEntry -= 10;
			hallOfFameEndEntry -= 10;
			
			
			newHallOfFamePanel = new HallOfFameView(hallOfFameStartEntry, hallOfFameEndEntry);
			
			groupLayout.replace(hallOfFamePanel, newHallOfFamePanel);
			hallOfFamePanel = new HallOfFameView(hallOfFameStartEntry, hallOfFameEndEntry);
			groupLayout.replace(newHallOfFamePanel, hallOfFamePanel);
			
			hallOfFamePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new LineBorder(new Color(0, 255, 0), 8)));
		}
		
		
	}
	
	public void followingEntriesBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int totalEntries = ((HallOfFameView)hallOfFamePanel).getNumOfEntries();
		
		//System.out.println("Total number of entries is "+totalEntries);
		
		if(hallOfFameEndEntry <= totalEntries) {
			hallOfFameStartEntry += 10;
			hallOfFameEndEntry += 10;
			
			
			newHallOfFamePanel = new HallOfFameView(hallOfFameStartEntry, hallOfFameEndEntry);
			
			groupLayout.replace(hallOfFamePanel, newHallOfFamePanel);
			hallOfFamePanel = new HallOfFameView(hallOfFameStartEntry, hallOfFameEndEntry);
			groupLayout.replace(newHallOfFamePanel, hallOfFamePanel);
			
			hallOfFamePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new LineBorder(new Color(0, 255, 0), 8)));
		}
		
		
		
		
	}
	public void refreshHallOfFame() {
		
	}
	public void refresh() {
		System.out.println("refreshing UI");
		disableButtons();
		playLevelLayout.repaint();
		// Update lives label
		int lives = ((PlayLevelLayout) playLevelLayout).getLives();
		String livesStr = String.valueOf(lives);
		livesLbl.setText(livesStr);
		// Update score label
		int score = ((PlayLevelLayout) playLevelLayout).getScore();
		String scoreStr =String.valueOf(score);
		scoreLbl.setText(scoreStr);
		// Update the level label
		int level = ((PlayLevelLayout) playLevelLayout).getCurrentLevel();
		String levelStr = String.valueOf(level);
		levelLbl.setText(levelStr);
		
	}
	public void disableButtons() {
		if(!(Block223Controller.isGameReady())) {
			logOutBtn.setEnabled(false);
			backBtn.setEnabled(false);
		}
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		Block223Controller.logout();
		frame.dispose();
		new WelcomeWindow();
	}
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		//System.out.println("executing endgame()");
		testBtn.setVisible(false);
		playBtn.setVisible(false);
		logOutBtn.setEnabled(true);
		backBtn.setEnabled(true);
		if(nrOfLives == 0) {
			YOULOSTLbl.setVisible(true);
			//int lives = ((PlayLevelLayout) playLevelLayout).getLives();
			//String livesStr = String.valueOf(lives);
			livesLbl.setText("0");
		} else {
			YOUWONlbl.setVisible(true);
		}
	}
	
	private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {
		playBtn.setVisible(false);
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
					if (Block223Controller.isGamePaused()) {
						playBtn.setVisible(true);
						backBtn.setEnabled(true);
						logOutBtn.setEnabled(true);
					}
					if(Block223Controller.isGameReady()) {
						playBtn.setVisible(true);
					}
				} catch (InvalidInputException e) {
				}
			}
		};
		
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
					if (Block223Controller.isGamePaused()) {
						playBtn.setVisible(true);
						logOutBtn.setEnabled(true);
						backBtn.setEnabled(true);
					}
					if(Block223Controller.isGameReady()) {
						System.out.println("im in here");
						playBtn.setVisible(true);
					}
				} catch (InvalidInputException e) {
					errorMessage.setText(e.getMessage());
					return;
				}
			}
		};
		Thread t2 = new Thread(r2);
		t2.start();
	}
	}

