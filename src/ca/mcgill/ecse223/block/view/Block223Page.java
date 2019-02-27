package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOUserMode;

public class Block223Page extends JFrame{

	private static final long serialVersionUID = -3399224687781482984L;
	
	// UI elements
		private JLabel errorMessage; 
		
	//Game Settings
		
		private JLabel gameSettingsLabel;
		
		//Level
		private JTextField nrOfLevelsTextField;
		private JLabel nrOfLevelsLabel;
		private JTextField currentLevelNumberTextField; // UI Mock up ??
		private JLabel currentLevelNumberLabel;
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
		
		//data elements
		private String error = null;
		
		
		public Block223Page() {
			initComponents();
			refreshData();
			
		}
		
		private void initComponents () {
			// elements for error message
			errorMessage = new JLabel();
			errorMessage.setForeground(Color.RED);
			
			//elements for Game Settings
			gameSettingsLabel = new JLabel ();
			gameSettingsLabel.setText("Game Settings");
			saveGameSettingButton = new JButton();
			saveGameSettingButton.setText("Save Game");
			
				//elements for Level
			nrOfLevelsTextField = new JTextField();
			nrOfLevelsLabel = new JLabel();
			nrOfLevelsLabel.setText("Number Of Levels");
			currentLevelNumberTextField = new JTextField();
			currentLevelNumberLabel = new JLabel();
			currentLevelNumberLabel.setText("Current Level Number");
			nrOfBlocksPerLevelTextField = new JTextField();
			nrOfBlocksPerLevelLabel = new JLabel();
			nrOfBlocksPerLevelLabel.setText("Number of Block Per Level");
						
				//elements for Ball
			ballLabel = new JLabel();
			ballLabel.setText("Ball:");
			minBallSpeedXTextField = new JTextField();
			minBallSpeedXLabel = new JLabel();
			minBallSpeedXLabel.setText("Minimum Ball Speed for X Coordinate");
			minBallSpeedYTextField = new JTextField();
			minBallSpeedYLabel = new JLabel();
			minBallSpeedYLabel.setText("Minimum Ball Speed for Y Coordinate");
			speedIncreaseFactorTextField = new JTextField();
			speedIncreaseFactorLabel = new JLabel();
			speedIncreaseFactorLabel.setText("Speed Increasing Factor");
			
				//elements for Paddle
			paddleLabel = new JLabel();
			paddleLabel.setText("Paddle:");
			minPaddleLengthTextField = new JTextField();
			minPaddleLengthLabel = new JLabel();
			minPaddleLengthLabel.setText("Minimum Paddle Length");
			maxPaddleLengthTextField = new JTextField();
			maxPaddleLengthLabel = new JLabel();
			maxPaddleLengthLabel.setText("Maximum Paddle Length");
			
			
			// global settings
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("Block 223");
			
			//listeners for Game Settings
			saveGameSettingButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					saveGameSettingButtonActionPerformed(evt);
				}
			});
			
			
			///layout
		}
		private void refreshData() {
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
		
		private void saveGameSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
			error = null;
			
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
			// update visuals
			refreshData();
		}
		
		
}
