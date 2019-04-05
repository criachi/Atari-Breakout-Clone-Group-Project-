// TODO: when the user clicks create game, he is taken to 
//game settings page and create game creates the default game ofc 
//but careful it hasnt saved it in persistence yet! 
//once he defines all game settings, 
//he presses save game and its saved (call save Game in action listener)
// the set settings button calls serGameDetails which changes the model object but not persistence 
// only delete game and the register method call save automatically in their code 
// also, when u delete a game, make sure the combobox has had sth selected from it beforehand (check my delete block action listener) 
// otherwise display an error 
// and then in the action listener for the delete method, u need to refresh the page (so refresh the combo box) to give a list w/ 1 less game... 
// check my refresh Blocks! 
// all these methods im asking u to check are in gameDesignPage... 


//chekc update game action performed method i commented sth out bc when it is uncommented, it gives a nullpointer exception on the console!! and dsnt take us to the next pg
package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AdminDashBoardPage {

	private JFrame frame;
	private JLabel errorMessage;
	private JLabel lblYourGames;
	private JButton logOutBtn;
	private JButton createGameBtn;
	private JButton deleteGameBtn;
	private JButton btnUpdateGame;
	private JLabel lblOr;
	private JLabel lblGameName;
	private JTextField textField;
	private JButton updateGameBtn;
	private JComboBox<String> yourGamesComboBox;
	private int gameListSize;
	private HashMap<Integer, TOGame> yourGames;
	private JButton testGameBtn;
	private JButton publishGameBtn;

	/**
	 * Create the application.
	 */
	public AdminDashBoardPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		lblYourGames = new JLabel("YOUR GAMES");
		lblYourGames.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		// Log Out
		logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutBtnActionPerformed(evt);
			}
		});
		
		// Create Game
		createGameBtn = new JButton("Create Game");
		createGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createGameBtnActionPerformed(evt);
			}
		});
		
		// Delete Game
		deleteGameBtn = new JButton("Delete Game");
		deleteGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteGameBtnActionPerformed(evt);
			}
		});
		
		// Update Game
		updateGameBtn = new JButton("Update Game");
		updateGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateGameBtnActionPerformed(evt);
			}
		});
		yourGamesComboBox = new JComboBox<String>();
		yourGamesComboBox.addItem("");
		lblOr = new JLabel("OR:");
		lblOr.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		
		lblGameName = new JLabel("Game name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		
		testGameBtn = new JButton("Test Game");
		testGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				testGameBtnActionPerformed(evt);
			}
		});
		publishGameBtn = new JButton("Publish Game");
		publishGameBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				publishGameBtnActionPerformed(evt);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblYourGames)
									.addPreferredGap(ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
									.addComponent(logOutBtn))
								.addComponent(yourGamesComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(testGameBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deleteGameBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(publishGameBtn)
								.addComponent(updateGameBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(25))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(lblOr)
					.addContainerGap(437, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGameName)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(createGameBtn)
					.addGap(155))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorMessage)
					.addContainerGap(568, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(logOutBtn)
						.addComponent(lblYourGames))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(yourGamesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteGameBtn))
					.addGap(37)
					.addComponent(updateGameBtn)
					.addGap(40)
					.addComponent(testGameBtn)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(lblOr)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(createGameBtn)
								.addComponent(lblGameName)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(publishGameBtn)))
					.addGap(72))
		);
		frame.getContentPane().setLayout(groupLayout);
		refreshComboBox();
		}
	private void refreshComboBox() {
		errorMessage.setText("");
			try {
				List<TOGame> availableGames = Block223Controller.getDesignableGames();
				yourGamesComboBox.removeAllItems();
				yourGamesComboBox.addItem("");
				for(TOGame game : availableGames) {
					yourGamesComboBox.addItem(game.getName());
				}
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
		
		//hacky way to do things 
		/*ArrayList<TOGame> availableGames = new ArrayList<TOGame>();
		try {
			availableGames = (ArrayList<TOGame>) Block223Controller.getDesignableGames();
		}
		catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
		
		gameListSize = availableGames.size();
		
		yourGamesComboBox.addItem("");
		
		for(int i = 0; i < gameListSize; i++) {
			yourGamesComboBox.addItem(availableGames.get(i).getName());
		} */
		
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		Block223Controller.logout();
		frame.dispose();
		new WelcomeWindow();
		//new Block223Page().setVisible(true);
	}
	
	private void createGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		
		try {
			String newName = textField.getText();
			Block223Controller.createGame(newName);
			// added this after long rant abt updateGame diff name w/ alrdy existing game
			// create game in controller used to call setCurrentGame on application class to set current game var to the game we just created so the test wld fail
			Block223Controller.selectGame(newName);
		}
		catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		frame.dispose();
		new GameSettingPage();
		//new Block223Page().setVisible(true);
	}
	private void deleteGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedGameIndex = yourGamesComboBox.getSelectedIndex();
		if (selectedGameIndex < 1) {
			errorMessage.setText("A game needs to be selected to be deleted! ");
			return;
		}
		try {
			Block223Controller.deleteGame(yourGamesComboBox.getItemAt(selectedGameIndex));
		}
		catch(InvalidInputException e) {
			//System.out.println(e.getMessage());
			errorMessage.setText(e.getMessage());
			return;
		}
		refreshComboBox();
	} 
	private void publishGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedGameIndex = yourGamesComboBox.getSelectedIndex();
		if(selectedGameIndex < 1) {
			errorMessage.setText("A game needs to be selected to be published! ");
			return;
		}
		
		try { 
			Block223Controller.selectGame(yourGamesComboBox.getItemAt(selectedGameIndex));
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		
		try {
			Block223Controller.publishGame();
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		refreshComboBox();
		try {
			Block223Controller.saveGame();
		}
		catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	private void updateGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// error msg is cleared when we call refresh methods at the end of actionperformed methods 
    	
    	int selectedGameIndex = yourGamesComboBox.getSelectedIndex();
		if (selectedGameIndex < 1) {
			errorMessage.setText("A game needs to be selected to be updated! ");
			return;
		} 
		
		try {
			Block223Controller.selectGame(yourGamesComboBox.getItemAt(selectedGameIndex));
		}
		catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		
		frame.dispose();
		new UpdateSettingPage();
	}
	private void testGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedGameIndex = yourGamesComboBox.getSelectedIndex();
		if(selectedGameIndex < 1) {
			errorMessage.setText("A game needs to be selected to be tested! ");
			return; 
		}
		
		try {
			Block223Controller.selectGame(yourGamesComboBox.getItemAt(selectedGameIndex));
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		frame.dispose();
		new PlayModePage();
		/*PlayModePage testGame = new PlayModePage();
		//FOr some reason the startGame call in testGame results inthe screen being blank... still working on it
		try {
			frame.dispose();
			Block223Controller.testGame(testGame);
		} catch (InvalidInputException e) {
			AdminDashBoardPage catchPage = new AdminDashBoardPage();
			catchPage.errorMessage.setText(e.getMessage());
			testGame.deletePage();
		}*/
		
	}
}
