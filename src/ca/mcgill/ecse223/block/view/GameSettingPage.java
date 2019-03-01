package ca.mcgill.ecse223.block.view;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JButton;

import java.awt.Color;

public class GameSettingPage {
	//Game Settings
			private JFrame frame;
			private JLabel errorMessage;
	
			private JLabel gameSettingLabel;
			private JButton logOutBtn;
			
						
			//Level
			private JTextField nrOfLevelsTextField;
			private JLabel nrOfLevelsLabel;
			private JTextField nrOfBlocksPerLevelTextField;
			private JLabel nrOfBlocksPerLevelLabel;
			
			//Ball
			private JLabel ballLabel;
			private JTextField minBallSpeedXTextField;
			private JLabel minBallSpeedXLabel;
			private JTextField minBallSpeedYTextField;
			private JLabel minBallSpeedYLabel;
			private JTextField speedIncreaseFactorTextField;
			private JLabel speedIncreaseFactorLabel; 
			
			//Paddle
			private JLabel paddleLabel;
			private JTextField minPaddleLengthTextField;
			private JLabel minPaddleLengthLabel;
			private JTextField maxPaddleLengthTextField;
			private JLabel maxPaddleLengthLabel;
			
			private JButton saveGameSettingButton;
			

			public  GameSettingPage() {
				initComponents();
				//refreshData(); -> if needed
			}
			
			private void initComponents() {
				//Frame
				frame = new JFrame();
				frame.setBounds(500, 800, 611, 504);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				JDesktopPane desktopPane = new JDesktopPane();
				desktopPane.setBackground(Color.LIGHT_GRAY);
				frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
				
				errorMessage = new JLabel("");
				errorMessage.setForeground(Color.RED);
				errorMessage.setFont(new Font("Tahoma", Font.BOLD, 18));
				errorMessage.setBounds(15, 404, 376, 28);
				desktopPane.add(errorMessage);
				
				//elements for Game Settings
				gameSettingLabel = new JLabel("Game Settings ");
				gameSettingLabel.setBounds(15, 16, 202, 28);
				gameSettingLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				desktopPane.add(gameSettingLabel);
				
				saveGameSettingButton = new JButton("Save Game");
				saveGameSettingButton.setBounds(442, 404, 133, 28);
				saveGameSettingButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(saveGameSettingButton);
				
				logOutBtn = new JButton("Log Out");
				logOutBtn.setBounds(442, 16, 133, 28);
				desktopPane.add(logOutBtn);
				
							
					//elements for Level
				nrOfLevelsTextField = new JTextField();
				nrOfLevelsTextField.setBounds(312, 61, 94, 20);
				desktopPane.add(nrOfLevelsTextField);
				nrOfLevelsTextField.setColumns(10);
				
				nrOfLevelsLabel = new JLabel("Number Of Levels");
				nrOfLevelsLabel.setBounds(15, 60, 202, 23);
				nrOfLevelsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(nrOfLevelsLabel);
				
				nrOfBlocksPerLevelTextField = new JTextField();
				nrOfBlocksPerLevelTextField.setBounds(312, 85, 94, 20);
				desktopPane.add(nrOfBlocksPerLevelTextField);
				nrOfBlocksPerLevelTextField.setColumns(10);
				
				nrOfBlocksPerLevelLabel = new JLabel("Number of Block Per Level");
				nrOfBlocksPerLevelLabel.setBounds(15, 85, 196, 20);
				nrOfBlocksPerLevelLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(nrOfBlocksPerLevelLabel);
				
							
					//elements for Ball
				ballLabel = new JLabel("Ball:");
				ballLabel.setBounds(15, 121, 94, 16);
				ballLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				desktopPane.add(ballLabel);
				
				minBallSpeedXTextField = new JTextField();
				minBallSpeedXTextField.setBounds(312, 144, 94, 20);
				desktopPane.add(minBallSpeedXTextField);
				minBallSpeedXTextField.setColumns(10);
				
				minBallSpeedXLabel = new JLabel("Minimum Ball Speed for X Coordinate");
				minBallSpeedXLabel.setBounds(15, 135, 270, 39);
				minBallSpeedXLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minBallSpeedXLabel);
				
				minBallSpeedYTextField = new JTextField();
				minBallSpeedYTextField.setBounds(312, 170, 94, 20);
				desktopPane.add(minBallSpeedYTextField);
				minBallSpeedYTextField.setColumns(10);
				
				minBallSpeedYLabel = new JLabel("Minimum Ball Speed for Y Coordinate");
				minBallSpeedYLabel.setBounds(15, 170, 270, 20);
				minBallSpeedYLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minBallSpeedYLabel);
				
				speedIncreaseFactorTextField = new JTextField();
				speedIncreaseFactorTextField.setBounds(312, 194, 94, 20);
				desktopPane.add(speedIncreaseFactorTextField);
				speedIncreaseFactorTextField.setColumns(10);
				
				speedIncreaseFactorLabel = new JLabel("Speed Increasing Factor");
				speedIncreaseFactorLabel.setBounds(15, 194, 257, 20);
				speedIncreaseFactorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(speedIncreaseFactorLabel);
				
				
					//elements for Paddle
				paddleLabel = new JLabel("Paddle: ");
				paddleLabel.setBounds(15, 245, 82, 20);
				paddleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				desktopPane.add(paddleLabel);
				
				minPaddleLengthTextField = new JTextField();
				minPaddleLengthTextField.setBounds(312, 270, 94, 20);
				desktopPane.add(minPaddleLengthTextField);
				minPaddleLengthTextField.setColumns(10);
				
				minPaddleLengthLabel = new JLabel("Minimum Paddle Length");
				minPaddleLengthLabel.setBounds(15, 270, 257, 20);
				minPaddleLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minPaddleLengthLabel);
				
				maxPaddleLengthTextField = new JTextField();
				maxPaddleLengthTextField.setBounds(312, 294, 94, 20);
				desktopPane.add(maxPaddleLengthTextField);
				maxPaddleLengthTextField.setColumns(10);
				
				maxPaddleLengthLabel = new JLabel("Maximum Paddle Length");
				maxPaddleLengthLabel.setBounds(15, 294, 207, 20);
				maxPaddleLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(maxPaddleLengthLabel);
			
				
				//listeners for Game Settings
				saveGameSettingButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saveGameSettingButtonActionPerformed(evt);
					}
				});
				
				logOutBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						logOutBtnActionPerformed(evt);
					}
				});
			}
		//if it is needed for save button listener
		/*	private void refreshData() {
				// error
				errorMessage.setText(error);
				if (error == null || error.length() == 0) {
					//GameSetting			
						
						//Level
					nrOfLevelsTextField.setText("");
					currentLevelNumberTextField.setText("");
					nrOfBlocksPerLevelTextField.setText("");
									
						//Ball
					minBallSpeedXTextField.setText("");
					minBallSpeedYTextField.setText("");
					speedIncreaseFactorTextField.setText("");
									
						//Paddle
					minPaddleLengthTextField.setText("");
					maxPaddleLengthTextField.setText("");
										
				}
			}
			*/ 
			
			private void saveGameSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
				
				String error = null;
				
				int nrLevels = 0;
				try {
					nrLevels = Integer.parseInt(nrOfLevelsTextField.getText());
				}
				catch (NumberFormatException e) {
					error = "Number of levels needs to be a numerical value!";
				}
				int nrBlocksPerLevel = 0;
				try {
					nrBlocksPerLevel = Integer.parseInt(nrOfBlocksPerLevelTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Number of blocks per level needs to be a numerical value!";
				}
				int minBallSpeedX = 0;
				try {
					minBallSpeedX = Integer.parseInt(minBallSpeedXTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Minimum ball speed for X coordinate needs to be a numerical value!";
				}
				int minBallSpeedY = 0;
				try {
					minBallSpeedY = Integer.parseInt(minBallSpeedYTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Minimum ball speed for Y coordinate needs to be a numerical value!";
				}
				double ballSpeedIncreaseFactor = 0;
				try {
					ballSpeedIncreaseFactor = Double.parseDouble(speedIncreaseFactorTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Speed increase factor needs to be a numerical value!";
				}
				int maxPaddleLength = 0;
				try {
					maxPaddleLength = Integer.parseInt(maxPaddleLengthTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Maximum paddle length needs to be a numerical value!";
				}
				int minPaddleLength = 0;
				try {
					minPaddleLength = Integer.parseInt(minPaddleLengthTextField.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Minimum paddle length needs to be a numerical value!";
				}
				
				error.trim();
				
				if (error.length() == 0) {
				
					try {
						Block223Controller.setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				new GameDesignPage();
				// update visuals
				// refreshData(); -> idk if we need this somewhere in this method
			}
			private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
				frame.dispose();
				new WelcomeWindow();
				
			}
}