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




package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		yourGamesComboBox = new JComboBox();
		
		lblOr = new JLabel("OR:");
		lblOr.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		
		lblGameName = new JLabel("Game name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		errorMessage = new JLabel("New label");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(deleteGameBtn)
								.addComponent(updateGameBtn))))
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
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorMessage)
					.addContainerGap(499, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(yourGamesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteGameBtn))
					.addGap(44)
					.addComponent(updateGameBtn)
					.addGap(112)
					.addComponent(lblOr)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createGameBtn)
						.addComponent(lblGameName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(72))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		ArrayList<TOGame> availableGames = new ArrayList<TOGame>();
		try {
			availableGames = (ArrayList<TOGame>) Block223Controller.getDesignableGames();
		}
		catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
		
		gameListSize = availableGames.size();
		System.out.println("You have "+gameListSize+" games!");
		
		yourGamesComboBox.addItem("");
		
		for(int i = 0; i < gameListSize; i++) {
			yourGamesComboBox.addItem(availableGames.get(i).getName());
		}
		
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
			Block223Controller.createGame(textField.getText());
		}
		catch(InvalidInputException e) {
			String testString = new String(e.getMessage());
			System.out.println(testString);
			errorMessage.setText(testString);
			return;
		}
		
		
		
		frame.dispose();
		new GameSettingPage();
		//new Block223Page().setVisible(true);
	}
	private void deleteGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			Block223Controller.deleteGame(yourGamesComboBox.getItemAt(yourGamesComboBox.getSelectedIndex()));
		}
		catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		
		yourGamesComboBox.remove(yourGamesComboBox.getSelectedIndex());
		yourGamesComboBox.updateUI();
	}
	// here set up mechanism to take the game name from the previous page and then on the update settings page start it out with the textfields alrdy filled out w/ the characteristics of the game
    // use the select game method in the feature
	private void updateGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error msg and basic input validation
    	
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
}
