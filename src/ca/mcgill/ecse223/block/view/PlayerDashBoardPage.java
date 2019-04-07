package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ca.mcgill.ecse223.block.controller.*;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PlayerDashBoardPage {

	private JFrame frame;
	private JButton logOutBtn;
	private JLabel lblGamesAvailable;
	private JComboBox gamesAvailableComboBox;
	private JButton startGameBtn;
	private JLabel lblContinueGames;
	private JComboBox continueGamesComboBox;
	private JButton continueGameBtn;
	private JLabel errorMessage;
	private HashMap<Integer, TOPlayableGame> resumableGames;
	private HashMap<Integer, TOPlayableGame> startableGames;
	
	/**
	 * Create the application.
	 */
	public PlayerDashBoardPage() {
		initialize();
		refreshContinueGamesComboBox();
		refreshGamesAvailComboBox();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
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
		
		gamesAvailableComboBox = new JComboBox<String>();
		gamesAvailableComboBox.addItem("");
		
		startGameBtn = new JButton("Start Game ");
		startGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startGameBtnActionPerformed(evt);
			}
		});
		
		lblContinueGames = new JLabel("Continue Games");
		lblContinueGames.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		continueGamesComboBox = new JComboBox<String>();
		continueGamesComboBox.addItem("");
		
		continueGameBtn = new JButton("Continue Game");
		continueGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				continueGameBtnActionPerformed(evt);
			}
		});
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
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
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(continueGameBtn)
										.addComponent(startGameBtn))
									.addGap(260))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(logOutBtn)
									.addGap(37))))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(continueGamesComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblContinueGames, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(errorMessage)
					.addGap(4)
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
					.addContainerGap(116, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	@SuppressWarnings("unchecked")
	private void refreshContinueGamesComboBox() {
		Integer index = 0;
		errorMessage.setText("");
		resumableGames = new HashMap<Integer, TOPlayableGame>();
		continueGamesComboBox.removeAllItems();
		continueGamesComboBox.addItem("");

		try {
			for(TOPlayableGame playableGame : Block223Controller.getPlayableGames()) {
				if(playableGame.getNumber() != -1 && playableGame.getCurrentLevel() != 0) {
					//System.out.println("Game "+playableGame.getName()+" added at index "+index+" with an id of "+playableGame.getNumber());
					resumableGames.put(index++, playableGame);
					continueGamesComboBox.addItem(playableGame.getName() + " " + playableGame.getNumber());
				}
			}
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	private void refreshGamesAvailComboBox() {
		Integer index = 0;
		errorMessage.setText("");
		startableGames = new HashMap<Integer, TOPlayableGame>();
		gamesAvailableComboBox.removeAllItems();
		gamesAvailableComboBox.addItem("");
		try {
			for(TOPlayableGame playableGame : Block223Controller.getPlayableGames()) {
				if(playableGame.getNumber() == -1 && playableGame.getCurrentLevel() == 0) {
					startableGames.put(index, playableGame);
					gamesAvailableComboBox.addItem(playableGame.getName());
				}
				index++;
			}
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		Block223Controller.logout();
		frame.dispose();
		new WelcomeWindow();
		//new Block223Page().setVisible(true);
	}
	
	private void startGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		Integer selectedGameIndex = gamesAvailableComboBox.getSelectedIndex();
		if(selectedGameIndex < 1) {
			errorMessage.setText("A game needs to be selected to start.");
			return;
		}
		
		try {
			//(String) gamesAvailableComboBox.getItemAt(index)
			Block223Controller.selectPlayableGame((startableGames.get(selectedGameIndex-1)).getName(), -1);
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		frame.dispose();
		new PlayModePage();
		
	}
	
	private void continueGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		Integer selectedPlayedGameIndex = continueGamesComboBox.getSelectedIndex();
		if(selectedPlayedGameIndex <1) {
			errorMessage.setText("A game needs to be selected to continue it.");
			return;
		}
		
		try {
			//System.out.println("Attempting to continue "+resumableGames.get(selectedPlayedGameIndex-1).getName()+" from index "+(selectedPlayedGameIndex-1)+" with id "+resumableGames.get(selectedPlayedGameIndex-1).getNumber());
			Block223Controller.selectPlayableGame(null, (resumableGames.get(selectedPlayedGameIndex-1)).getNumber());
		} catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		frame.dispose();
		new PlayModePage();
	}
	
}
