// SAVE GAME SETTING BUTTON ACTION PERFORMED METHOD FIX WAY OF DISPLAYING ERROR




package ca.mcgill.ecse223.block.view;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.EventQueue;

public class GameSettingPage {
	//Game Settings
			private JFrame frame;
			private JLabel errorMessage;
	
			private JLabel gameSettingLabel;
			private JButton logOutBtn;
			
			private int nrLevels = 0;
			private int nrBlocksPerLevel = 1;
			private int minBallSpeedX = 1;
			private int minBallSpeedY = 1;
			private int maxPaddleLength = 10;
			private double ballSpeedIncreaseFactor = 1.0;
			private int minPaddleLength = 10;
			
			private int nrLevelsOld;
			private int nrBlocksPerLevelOld;
			private int minBallSpeedXOld;
			private int minBallSpeedYOld;
			private int maxPaddleLengthOld;
			private int minPaddleLengthOld;
			private double ballSpeedIncreaseFactorOld;
			
						
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
			
			private JButton setSettingButton;
			private JButton saveGameBtn;
			private JButton backBtn;
			private JButton nextBtn;

			public  GameSettingPage() {
				initComponents();
			}
			
			private void initComponents() {
				//Frame
				frame = new JFrame();
				frame.setBounds(100, 100, 639, 545);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				JDesktopPane desktopPane = new JDesktopPane();
				desktopPane.setBackground(Color.LIGHT_GRAY);
				frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
				
				errorMessage = new JLabel("");
				errorMessage.setForeground(Color.RED);
				errorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
				errorMessage.setBounds(15, 26, 376, 28);
				desktopPane.add(errorMessage);
				
				//elements for Game Settings
				gameSettingLabel = new JLabel("Define Game Settings ");
				gameSettingLabel.setBounds(15, 0, 327, 28);
				gameSettingLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
				desktopPane.add(gameSettingLabel);
				
				setSettingButton = new JButton("Set Settings\r\n");
				setSettingButton.setBounds(469, 331, 133, 28);
				setSettingButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				desktopPane.add(setSettingButton);
				
				logOutBtn = new JButton("Log Out");
				logOutBtn.setBounds(469, 16, 133, 28);
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
				
				saveGameBtn = new JButton("Save Game");
				saveGameBtn.setBounds(469, 390, 133, 28);
				desktopPane.add(saveGameBtn);
				
				backBtn = new JButton("Back");
				backBtn.setBounds(15, 445, 115, 29);
				desktopPane.add(backBtn);
				
				nextBtn = new JButton("Next");
				nextBtn.setBounds(469, 445, 133, 28);
				desktopPane.add(nextBtn);
				
				
				//listeners for Game Settings
				backBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						backBtnActionPerformed(evt);
					}
				});
				
				nextBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						nextBtnActionPerformed(evt);
					}
				});
				
				saveGameBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						saveGameBtnActionPerformed(evt);
					}
				});
				setSettingButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						setSettingButtonActionPerformed(evt);
					}
				});
				
				logOutBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						logOutBtnActionPerformed(evt);
					}
				});
				
				refreshPage();
			}
			
			private void refreshPage() {
				try {
					TOGame currentGame = Block223Controller.getCurrentDesignableGame();
					nrOfLevelsTextField.setText("1"); //before: String.valueOf(currentGame.getNrLevels())
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
			
			private void setSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
				
				errorMessage.setText("");
				
				/*
				 * The variable_old declared here are in case the try catch block
				 * fails so that the variables are set to their old values.
				 * This way if the user is not able to set any settings but 
				 * they did change the text fields, the variables holding the 
				 * values in the text fields dont get set with unallowed values
				 * (which was causing problems when we tried to go the next page
				 * without successfully setting stuff.)
				 */
				
				nrLevelsOld = nrLevels;
				nrBlocksPerLevelOld = nrBlocksPerLevel;
				minBallSpeedXOld = minBallSpeedX;
				minBallSpeedYOld = minBallSpeedY;
				ballSpeedIncreaseFactorOld = ballSpeedIncreaseFactor;
				maxPaddleLengthOld = maxPaddleLength;
				minPaddleLengthOld = minPaddleLength; 
				
				try {
					nrLevels = Integer.parseInt(nrOfLevelsTextField.getText());
					nrBlocksPerLevel = Integer.parseInt(nrOfBlocksPerLevelTextField.getText());
					minBallSpeedX = Integer.parseInt(minBallSpeedXTextField.getText());
					minBallSpeedY = Integer.parseInt(minBallSpeedYTextField.getText());
					ballSpeedIncreaseFactor = Double.parseDouble(speedIncreaseFactorTextField.getText());
					maxPaddleLength = Integer.parseInt(maxPaddleLengthTextField.getText());
					minPaddleLength = Integer.parseInt(minPaddleLengthTextField.getText());
					Block223Controller.setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
				}
				catch (NumberFormatException e) {
					errorMessage.setText("All fields need to be set to numerical values! ");
					
					nrLevels = nrLevelsOld;
					nrBlocksPerLevel = nrBlocksPerLevelOld;
					minBallSpeedX = minBallSpeedXOld;
					minBallSpeedY = minBallSpeedYOld;
					ballSpeedIncreaseFactor = ballSpeedIncreaseFactorOld;
					maxPaddleLength = maxPaddleLengthOld;
					minPaddleLength = minPaddleLengthOld;
					
					return; 
				} catch (InvalidInputException e) {
						errorMessage.setText(e.getMessage());
						
						nrLevels = nrLevelsOld;
						nrBlocksPerLevel = nrBlocksPerLevelOld;
						minBallSpeedX = minBallSpeedXOld;
						minBallSpeedY = minBallSpeedYOld;
						ballSpeedIncreaseFactor = ballSpeedIncreaseFactorOld;
						maxPaddleLength = maxPaddleLengthOld;
						minPaddleLength = minPaddleLengthOld;
						
						return;
				}
				
				errorMessage.setText("Game settings set!");
				// update visuals
				//refreshPage(); -> idk if we need this somewhere in this method
			}
			private void saveGameBtnActionPerformed(java.awt.event.ActionEvent evt) {
					errorMessage.setText("");
					try {
						Block223Controller.saveGame();
					} catch (InvalidInputException e) {
						errorMessage.setText(e.getMessage());
					}
			    
			}
			private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
				Block223Controller.logout();
				frame.dispose();
				new WelcomeWindow();
				
			}
			private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
				frame.dispose();
				new AdminDashBoardPage();
				
			}
			private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
				if(nrLevels == 0) {
					try {
						Block223Controller.setGameDetails(1, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
					}
					catch (NumberFormatException e) {
						errorMessage.setText("All fields need to be set to numerical values! ");
						return; 
					} catch (InvalidInputException e) {
						errorMessage.setText(e.getMessage());
						return;
					}
				}
				frame.dispose();
				new GameDesignPage();
				
			}
}
