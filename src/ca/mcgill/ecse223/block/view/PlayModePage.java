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
import ca.mcgill.ecse223.block.model.PlayedGame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

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

	/**
	 * Create the application.
	 */
	public PlayModePage() {
		//System.out.println("hehe");
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
		
		previousEntriesBtn = new JButton("Previous Entries");
		
		followingEntriesBtn = new JButton("Following Entries");
		
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
		
		playLevelLayout = new PlayLevelLayout();
		playLevelLayout.setPreferredSize(new Dimension(390,390));
		playLevelLayout.setVisible(true);
		
		currentLevelLbl = new JLabel("");
		
		livesLbl = new JLabel("");
		
		scoreLbl = new JLabel("");
		
		levelLbl = new JLabel("");
		
		dummyArea = new JTextArea();
		dummyArea.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addGap(124))
								.addGroup(groupLayout.createSequentialGroup()
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
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(22)
											.addComponent(livesLbl))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scoreLbl)))
									.addGap(23))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(playLevelLayout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(110)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
							.addComponent(lblHallOfFame)
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(591, Short.MAX_VALUE)
					.addComponent(previousEntriesBtn)
					.addGap(26)
					.addComponent(followingEntriesBtn)
					.addContainerGap())
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
								.addComponent(currentLevelLbl)
								.addComponent(levelLbl))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLives)
										.addComponent(livesLbl))
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
											.addComponent(playLevelLayout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(50)
									.addComponent(dummyArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblHallOfFame))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
		System.out.println("refreshing UI");
		//PlayedGameLevel.setBlockAssignments();
		//PlayedGameLevel.refreshGame();
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
	
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		//reset paddle & ball
		//clear block assignments
 		
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
				dummyArea.addKeyListener(gameListener);
			}
		};
		Thread t1 = new Thread(r1);
		System.out.println(t1.getState());
		t1.start();
		System.out.println(t1.getState());
		// to be on the safe side use join to start executing thread t1 before executing the next thread
		try {
			t1.join();
			System.out.println(t1.getState());
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
		
		Thread t2 = new Thread(r2);
		t2.start();
		System.out.println("thread 2" + t1.getState());
		System.out.println("thread 2 was started bitch");
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
				dummyArea.addKeyListener(gameListener);
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

