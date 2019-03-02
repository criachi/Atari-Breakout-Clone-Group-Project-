// check my method: private void addBlockBtnActionPerformed(java.awt.event.ActionEvent evt) 
// as a template for how to call refresh methods and stuff like that... 
// also refer to code from the BtmsPage class in Btms Version 2 (tuto 5) and even the one from version 4!


// CHRISTINA: LOOK AT DELETEBLOCKACTIONPERFORMED METHOD CUZ IF SELECTEDINDEX <0 U JUST ADD THE ERROR TO THE STRING BUT REFRESH DATA IS CALLED IN TRY CATCH BLOCK SO IT MIGHT NOT EXECUTE TO DISPLAY THE STRING IF THERE IS AN ERROR





package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;
import ca.mcgill.ecse223.block.controller.*;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import java.awt.Scrollbar;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Canvas;
import javax.swing.Box;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GameDesignPage {

	private JFrame frame;
	
	// error JLabel 
	private JLabel errorMessage;
	// textfield components for characteristics of blocks (colors + points)
	private JTextField redTextField;
	private JTextField blueTextField;
	private JTextField greenTextField;
	private JTextField pointsTextField;
	
	// Blocks in a Game
	private JComboBox<String> yourBlocksComboBox;
	private JLabel lblYourBlocks;
	private JLabel redLbl;
	private JLabel blueLbl;
	private JLabel greenLbl;
	private JLabel pointsLbl;
	private JButton deleteBlockBtn;
	private JButton addBlockBtn;
	private JButton updateBlockBtn;
	private JButton saveChangesBtn;
	
	// Blocks in a Level (Block Assignment)
	private JButton removeBlockAssignmentBtn;
	
	// Log Out
	private JButton logOutBtn;
	
	// Back
	private JButton backBtn;

	// data elements: each JComboBox needs to know which model object an entry in it refers to
	// also, check my question abt this to Gunter: he said error messages also require hashmaps and any table we make requires one too 
	// add/remove block - Work in Progress: unsure what value type to identify block
	private HashMap<Integer, TOBlock> gameBlocks;
	private JLabel lblSelectALevel;
	private JComboBox levelComboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameDesignPage window = new GameDesignPage();
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
	public GameDesignPage() {
		initialize();
		//refreshData(); idk
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Initializing frame of application
		frame = new JFrame();
		frame.setBounds(100, 100, 1270, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initializing elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// Initializing Log Out elements
		logOutBtn = new JButton("Log Out");
		logOutBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutBtnActionPerformed(evt);
			}
		});
		
		// Initializing Block Settings elements
		lblYourBlocks = new JLabel("YOUR BLOCKS");
		lblYourBlocks.setFont(new Font("Times New Roman", Font.BOLD, 25));
		 
		redLbl = new JLabel("Red");
		
		blueLbl = new JLabel("Blue");
		
		greenLbl = new JLabel("Green");
		
		pointsLbl = new JLabel("Points");
		
		// Delete Block
		deleteBlockBtn = new JButton("Delete Block");
		// Action Listener for deleteBlock
		deleteBlockBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteBlockBtnActionPerformed(evt);
			}
		});
		// Add Block
		addBlockBtn = new JButton("Add Block");
		// Action Listener for addBlock
		addBlockBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBlockBtnActionPerformed(evt);
			}
		});
		
		// Haluk: Update Block
		updateBlockBtn = new JButton("Update Block");
		// Action Listener for updateBlock
		updateBlockBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateBlockBtnActionPerformed(evt);
			}
		});

		// Save Changes 
		saveChangesBtn = new JButton("Save Changes");
		// Action Listener for saveChanges
		saveChangesBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveChangesBtnActionPerformed(evt);
			}
		});
		
		// Initializing text fields for characteristics of blocks
		redTextField = new JTextField();
		redTextField.setColumns(10);
		
		blueTextField = new JTextField();
		blueTextField.setColumns(10);
		
		greenTextField = new JTextField();
		greenTextField.setColumns(10);
		
		pointsTextField = new JTextField();
		pointsTextField.setColumns(10);
		
		// Display List of Blocks
		yourBlocksComboBox = new JComboBox<String>();
		yourBlocksComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		yourBlocksComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				yourBlocksComboBoxActionPerformed(evt);
			}
		});
		// Haluk: Remove Block from a Level
		removeBlockAssignmentBtn = new JButton("Remove Block Assignment");
		// Action Listener for removeBlockfromaLevel
		removeBlockAssignmentBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeBlockAssignmentBtnActionPerformed(evt);
			}
		});
		
		// Go back
		backBtn = new JButton("Back");
		
		lblSelectALevel = new JLabel("Select a Level: ");
		lblSelectALevel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		levelComboBox = new JComboBox();
		
		// DON'T TOUCH: U CHANGE THIS BY DRAGGING AND DROPPING THINGS IN THE DESIGN WINDOW
		// Group Layout of Page
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(removeBlockAssignmentBtn)
									.addPreferredGap(ComponentPlacement.RELATED, 761, Short.MAX_VALUE)
									.addComponent(blueLbl))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap(1001, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(greenLbl)
										.addComponent(redLbl))))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(997, Short.MAX_VALUE)
							.addComponent(pointsLbl)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pointsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(updateBlockBtn))
					.addGap(44))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(1030, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYourBlocks)
						.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addGap(35))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSelectALevel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 853, Short.MAX_VALUE)
							.addComponent(logOutBtn))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(backBtn)
							.addGap(18)
							.addComponent(saveChangesBtn)
							.addPreferredGap(ComponentPlacement.RELATED, 747, Short.MAX_VALUE)
							.addComponent(deleteBlockBtn)
							.addGap(29)
							.addComponent(addBlockBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSelectALevel)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(logOutBtn)))
					.addGap(56)
					.addComponent(lblYourBlocks)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
					.addComponent(updateBlockBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(redLbl))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(blueLbl)
								.addComponent(removeBlockAssignmentBtn))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(greenLbl))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pointsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pointsLbl))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(addBlockBtn)
								.addComponent(deleteBlockBtn))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(saveChangesBtn)
								.addComponent(backBtn))
							.addGap(27))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	// Confusion... we will decide eventually how to organize refresh methods once I and we understand UI better :')
	private void refreshData(){
		refreshBlocks();
	}
	// Christina
	private void refreshBlocks() {
		if (errorMessage.getText() == "") {
			//populate page with data 
			Integer index = 0;
			//block characteristics 
			//red text field
			redTextField.setText("");
			//blue text field
			blueTextField.setText("");
			//green text field
			greenTextField.setText("");
			//points text field
			pointsTextField.setText("");
			//list of blocks (combo box)
			gameBlocks = new HashMap<Integer,TOBlock>();
			yourBlocksComboBox.removeAllItems();
			index = 0;
			try {
			for(TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				gameBlocks.put(index, block);
				yourBlocksComboBox.addItem("Red: " + block.getRed() + "Green: " + block.getGreen() + "Blue: " + block.getBlue() + "Points: " + block.getPoints());
				index++;
			}
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
		}
		//pack()? is it needed? check btms tutorial 5
	} 	
	// Christina
	private void addBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		// call the controller 
		// but first convert the textfield inputs from strings to integers
		int red = 0;
		int blue = 0;
		int green = 0; 
		int points = 0;
		try {
			red = Integer.parseInt(redTextField.getText());
			blue = Integer.parseInt(blueTextField.getText());
			green = Integer.parseInt(greenTextField.getText());
			points = Integer.parseInt(pointsTextField.getText());
			Block223Controller.addBlock(red, green, blue, points);
			// you have to wrap this refresh call in a try catch block
			// not sure yet if need to call refreshData() or just refreshBlocks()
			refreshBlocks();
		} catch (NumberFormatException e) {
			errorMessage.setText("All field entries need to be numerical values!");
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	// Christina
	private void deleteBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlock = yourBlocksComboBox.getSelectedIndex();
		if (selectedBlock < 0) {
			errorMessage.setText("A block needs to be selected to be deleted! ");
			return;
		}
			TOBlock block = gameBlocks.get(selectedBlock);
			// call the controller 
			try {
				Block223Controller.deleteBlock(gameBlocks.get(selectedBlock).getId());
				//update visuals 
				refreshBlocks();
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
	}
	
	// Haluk's methods
	// method for Update Block in a Game feature
	private void updateBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlockIndex = yourBlocksComboBox.getSelectedIndex();
		if(selectedBlockIndex < 0) {
			errorMessage.setText("A block needs to be selected to be updated! ");
			return;
		}
			// call the controller 
			TOBlock block = gameBlocks.get(selectedBlockIndex);
			try {
				Block223Controller.updateBlock(block.getId(), Integer.parseInt(redTextField.getText()), Integer.parseInt(greenTextField.getText()), Integer.parseInt(blueTextField.getText()), Integer.parseInt(pointsTextField.getText()));
				refreshData();
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
	}
	// method for Remove Block from a Level 
	private void removeBlockAssignmentBtnActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {
		//frame.dispose() will delete the current page (from what i understand)
		frame.dispose();
		new WelcomeWindow();
		//new Block223Page().setVisible(true);
	}
	// to display the info of the currently selected block in the respective text fields 
	private void yourBlocksComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedBlockIndex = yourBlocksComboBox.getSelectedIndex();
		if(selectedBlockIndex>=0) {
			TOBlock block = gameBlocks.get(selectedBlockIndex);
			redTextField.setText(Integer.toString(block.getRed()));
			blueTextField.setText(Integer.toString(block.getBlue()));
			greenTextField.setText(Integer.toString(block.getGreen()));
			pointsTextField.setText(Integer.toString(block.getPoints()));
		}
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
