// TODO: In updateGame 



package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
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
	private JComboBox yourGamesComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashBoardPage window = new AdminDashBoardPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(lblOr)
					.addContainerGap(414, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGameName)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(createGameBtn)
					.addGap(155))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(logOutBtn)
						.addComponent(lblYourGames))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(yourGamesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(44))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addComponent(deleteGameBtn)
							.addGap(44)))
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
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		frame.dispose();
		new WelcomeWindow();
		//new Block223Page().setVisible(true);
	}
	private void createGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		frame.dispose();
		new GameSettingPage();
		//new Block223Page().setVisible(true);
	}
	private void deleteGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	// here set up mechanism to take the game name from the previous page and then on the update settings page start it out with the textfields alrdy filled out w/ the characteristics of the game
    // use the select game method in the feature
	private void updateGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error msg and basic input validation
    	
    	int selectedGameIndex = yourGamesComboBox.getSelectedIndex();
		if (selectedGameIndex <0) {
			errorMessage.setText("A game needs to be selected to be updated! ");
			return;
		} 
		frame.dispose();
		new UpdateSettingPage();
	}
}
