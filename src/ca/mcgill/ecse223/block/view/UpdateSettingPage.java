package ca.mcgill.ecse223.block.view;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.*;


import javax.swing.JButton;

import java.awt.Color;
import java.awt.EventQueue;

public class UpdateSettingPage {
	//Game Settings
			private JFrame frame;
			private JLabel errorMessage;
				
			private JLabel gameSettingLabel;
			private JButton logOutBtn;
			private JButton nextBtn;
			
			private JLabel gameName;
			private JTextField gameNameTextField;
						
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
			
			private JButton updateGameSettingButton;
			private JButton backBtn;
			private JButton saveChangesBtn;

			public  UpdateSettingPage() {
				initComponents();
				//refreshPage();
			}
			
			private void initComponents()  {
						
				//Frame
				frame = new JFrame();
				frame.setBounds(100, 100, 700, 548);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				JDesktopPane desktopPane = new JDesktopPane();
				desktopPane.setBackground(Color.LIGHT_GRAY);
				frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
				
				errorMessage = new JLabel("");
				errorMessage.setForeground(Color.RED);
				errorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
				errorMessage.setBounds(25, 48, 376, 28);
				desktopPane.add(errorMessage);
				
				//elements for Game Settings
				gameSettingLabel = new JLabel("Update Game Settings ");
				gameSettingLabel.setBounds(15, 10, 376, 32);
				gameSettingLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				desktopPane.add(gameSettingLabel);
				
				updateGameSettingButton = new JButton("Update Game");
				updateGameSettingButton.setBounds(530, 358, 133, 28);
				updateGameSettingButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(updateGameSettingButton);
				
				logOutBtn = new JButton("Log Out");
				logOutBtn.setBounds(530, 16, 133, 28);
				desktopPane.add(logOutBtn);
				
				nextBtn = new JButton("Next");
				nextBtn.setBounds(530, 447, 133, 28);
				desktopPane.add(nextBtn);
				
				gameName = new JLabel("Name:  ");
				gameName.setBounds(15, 87, 202, 28);
				gameName.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(gameName);
				
				gameNameTextField = new JTextField();
				gameNameTextField.setBounds(312, 87, 94, 20);
				desktopPane.add(gameNameTextField);
				gameNameTextField.setColumns(10);
				
							
					//elements for Level
				nrOfLevelsTextField = new JTextField();
				nrOfLevelsTextField.setBounds(312, 119, 94, 20);
				desktopPane.add(nrOfLevelsTextField);
				nrOfLevelsTextField.setColumns(10);
				
				nrOfLevelsLabel = new JLabel("Number Of Levels");
				nrOfLevelsLabel.setBounds(15, 118, 202, 23);
				nrOfLevelsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(nrOfLevelsLabel);
				
				nrOfBlocksPerLevelTextField = new JTextField();
				nrOfBlocksPerLevelTextField.setBounds(312, 155, 94, 20);
				desktopPane.add(nrOfBlocksPerLevelTextField);
				nrOfBlocksPerLevelTextField.setColumns(10);
				
				nrOfBlocksPerLevelLabel = new JLabel("Number of Block Per Level");
				nrOfBlocksPerLevelLabel.setBounds(15, 155, 196, 20);
				nrOfBlocksPerLevelLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(nrOfBlocksPerLevelLabel);
				
							
					//elements for Ball
				ballLabel = new JLabel("Ball:");
				ballLabel.setBounds(15, 191, 94, 16);
				ballLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				desktopPane.add(ballLabel);
				
				minBallSpeedXTextField = new JTextField();
				minBallSpeedXTextField.setBounds(312, 219, 94, 20);
				desktopPane.add(minBallSpeedXTextField);
				minBallSpeedXTextField.setColumns(10);
				
				minBallSpeedXLabel = new JLabel("Minimum Ball Speed for X Coordinate");
				minBallSpeedXLabel.setBounds(15, 210, 270, 39);
				minBallSpeedXLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minBallSpeedXLabel);
				
				minBallSpeedYTextField = new JTextField();
				minBallSpeedYTextField.setBounds(312, 254, 94, 20);
				desktopPane.add(minBallSpeedYTextField);
				minBallSpeedYTextField.setColumns(10);
				
				minBallSpeedYLabel = new JLabel("Minimum Ball Speed for Y Coordinate");
				minBallSpeedYLabel.setBounds(15, 254, 270, 20);
				minBallSpeedYLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minBallSpeedYLabel);
				
				speedIncreaseFactorTextField = new JTextField();
				speedIncreaseFactorTextField.setBounds(312, 290, 94, 20);
				desktopPane.add(speedIncreaseFactorTextField);
				speedIncreaseFactorTextField.setColumns(10);
				
				speedIncreaseFactorLabel = new JLabel("Speed Increasing Factor");
				speedIncreaseFactorLabel.setBounds(15, 290, 257, 20);
				speedIncreaseFactorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(speedIncreaseFactorLabel);
				
				
					//elements for Paddle
				paddleLabel = new JLabel("Paddle: ");
				paddleLabel.setBounds(15, 326, 82, 20);
				paddleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				desktopPane.add(paddleLabel);
				
				minPaddleLengthTextField = new JTextField();
				minPaddleLengthTextField.setBounds(312, 362, 94, 20);
				desktopPane.add(minPaddleLengthTextField);
				minPaddleLengthTextField.setColumns(10);
				
				minPaddleLengthLabel = new JLabel("Minimum Paddle Length");
				minPaddleLengthLabel.setBounds(15, 362, 257, 20);
				minPaddleLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(minPaddleLengthLabel);
				
				maxPaddleLengthTextField = new JTextField();
				maxPaddleLengthTextField.setBounds(312, 393, 94, 20);
				desktopPane.add(maxPaddleLengthTextField);
				maxPaddleLengthTextField.setColumns(10);
				
				maxPaddleLengthLabel = new JLabel("Maximum Paddle Length");
				maxPaddleLengthLabel.setBounds(15, 393, 207, 20);
				maxPaddleLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(maxPaddleLengthLabel);
				
				backBtn = new JButton("Back");
				backBtn.setBounds(15, 447, 115, 29);
				desktopPane.add(backBtn);
				
				saveChangesBtn = new JButton("Save Changes");
				saveChangesBtn.setBounds(530, 402, 133, 29);
				desktopPane.add(saveChangesBtn);
			
				//listeners for Game Settings
				saveChangesBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saveChangesBtnActionPerformed(evt);
					}
				});
				backBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnBackActionPerformed(evt);
					}
				});
				
				updateGameSettingButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						updateGameSettingButtonActionPerformed(evt);
					}
				});
				
				logOutBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						logOutBtnActionPerformed(evt);
					}
				});
				nextBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						nextBtnActionPerformed(evt);
					}
				});
				
				refreshPage();
			}
			
			private void refreshPage() {
				try {
					TOGame currentGame = Block223Controller.getCurrentDesignableGame();
					gameNameTextField.setText(currentGame.getName());
					nrOfLevelsTextField.setText(String.valueOf(currentGame.getNrLevels()));
					nrOfBlocksPerLevelTextField.setText(String.valueOf(currentGame.getNrBlocksPerLevel()));
					minBallSpeedXTextField.setText(String.valueOf(currentGame.getMinBallSpeedX()));
					minBallSpeedYTextField.setText(String.valueOf(currentGame.getMinBallSpeedY()));
					speedIncreaseFactorTextField.setText(String.valueOf(currentGame.getBallSpeedIncreaseFactor()));
					minPaddleLengthTextField.setText(String.valueOf(currentGame.getMinPaddleLength()));
					maxPaddleLengthTextField.setText(String.valueOf(currentGame.getMaxPaddleLength()));
				}
				catch(InvalidInputException e) {
					errorMessage.setText(e.getMessage());
				}
				
				
			}
			private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
				frame.dispose();
				new AdminDashBoardPage();
			}
			
			private void updateGameSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
				int numLevels;
				int numBlocks;
				int minXSpeed;
				int minYSpeed;
				int minLength;
				int maxLength; 
				double speedIncFactor; 
				try {
					numLevels  = Integer.parseInt(nrOfLevelsTextField.getText());
					numBlocks = Integer.parseInt(nrOfBlocksPerLevelTextField.getText());
					minXSpeed = Integer.parseInt(minBallSpeedYTextField.getText());
					minYSpeed = Integer.parseInt(minBallSpeedYTextField.getText());
					minLength = Integer.parseInt(minPaddleLengthTextField.getText());
					maxLength = Integer.parseInt(maxPaddleLengthTextField.getText());
					speedIncFactor = Double.parseDouble(speedIncreaseFactorTextField.getText());
				}
				catch (NumberFormatException e) {
					errorMessage.setText("All fields besides name must be numerical values!");
					return;
				}
				try {
						Block223Controller.updateGame(gameNameTextField.getText(), numLevels, numBlocks, minXSpeed, minYSpeed, speedIncFactor, maxLength, minLength);
					} 
				catch (InvalidInputException e) {
						errorMessage.setText(e.getMessage());
						return;
					}
				errorMessage.setText("Game updated!");

				}
			private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
				frame.dispose();
				new WelcomeWindow();
				
			}
			private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
				frame.dispose();
				new GameDesignPage();
				
			}
			private void saveChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {
				errorMessage.setText("");
				try {
					Block223Controller.saveGame();
				} catch (InvalidInputException e) {
					errorMessage.setText(e.getMessage());
				}
				
			}
}
