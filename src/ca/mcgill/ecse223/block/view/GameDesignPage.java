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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Canvas;
import javax.swing.Box;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GameDesignPage {

	private JFrame frame;
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
	private HashMap<Integer, List<TOGridCell>> levels;
	private HashMap<Integer, TOGridCell> blockAssignments;
	private HashMap<TOBlock, TOGridCell> blocks;
	private JLabel lblSelectALevel;
	private JComboBox<String> levelComboBox;
	private JComboBox<String> blockAssignmentComboBox;
	private JLabel lblSelectABlockassignment;
	private JTextField gridHorizontalPositionTextField;
	private JLabel lblHorizontalPosition;
	private JTextField gridVerticalPositionTextField;
	private JLabel lblVerticalPosition;
	private JButton btnPositionBlocks;
	private JTextField newGridHorizontalPositionTextField;
	private JLabel lblNewHorizontalPosition;
	private JLabel lblNewVerticalPosition;
	private JTextField newGridVerticalPositionTextField;
	private JButton btnMoveBlock;
	private JComponent levelLayout;
	private JButton adminDashboardBtn;
	


	/**
	 * Create the application.
	 */
	public GameDesignPage() {
		initialize();
		refreshLevels();
		refreshBlocks(); //for now it is refresh blocks, but we shld call a global refresh method which refreshes evth in page 
		refreshBlockAssignmentComboBox();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Initializing frame of application
		frame = new JFrame();
		frame.setBounds(100, 100, 1270, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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
		yourBlocksComboBox.addItem("");
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
		backBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backBtnActionPerformed(evt);
			}
		});
		
		lblSelectALevel = new JLabel("Select a Level: ");
		lblSelectALevel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		levelComboBox = new JComboBox<String>();
		levelComboBox.addItem("");
		levelComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				levelComboBoxActionPerformed(evt);
			}
		});
		
		errorMessage = new JLabel("");
		
		blockAssignmentComboBox = new JComboBox<String>();
		blockAssignmentComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				blockAssignmentComboBoxActionPerformed(evt);
			}
		});
		
		lblSelectABlockassignment = new JLabel("Select a BlockAssignment:");
		
		gridHorizontalPositionTextField = new JTextField();
		gridHorizontalPositionTextField.setColumns(10);
		
		lblHorizontalPosition = new JLabel("Horizontal Position:");
		
		gridVerticalPositionTextField = new JTextField();
		gridVerticalPositionTextField.setColumns(10);
		
		lblVerticalPosition = new JLabel("Vertical Position:");
		
		btnPositionBlocks = new JButton("Position Block");
		btnPositionBlocks.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				positionBlockBtnActionPerformed(evt);
			}
		});
	
		newGridHorizontalPositionTextField = new JTextField();
		newGridHorizontalPositionTextField.setColumns(10);
		
		lblNewHorizontalPosition = new JLabel("New Horizontal Position:");
		
		lblNewVerticalPosition = new JLabel("New Vertical Position:");
		
		newGridVerticalPositionTextField = new JTextField();
		newGridVerticalPositionTextField.setColumns(10);
		
		btnMoveBlock = new JButton("Move Block");
		btnMoveBlock.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveBlockBtnActionPerformed(evt);
			}
		});
		
		levelLayout = new LevelLayout();
		
		adminDashboardBtn = new JButton("Admin Dashboard");
		adminDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adminDashboardBtnActionPerformed(evt);
			}
		});
		
		// DON'T TOUCH: U CHANGE THIS BY DRAGGING AND DROPPING THINGS IN THE DESIGN WINDOW
		// Group Layout of Page
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 724, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblSelectABlockassignment)
												.addComponent(lblSelectALevel))
											.addGap(28)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(blockAssignmentComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(levelComboBox, Alignment.LEADING, 0, 112, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewHorizontalPosition)
												.addComponent(lblVerticalPosition)
												.addComponent(lblHorizontalPosition)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(10)
													.addComponent(lblNewVerticalPosition)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(newGridVerticalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(newGridHorizontalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(gridHorizontalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(gridVerticalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(btnPositionBlocks, Alignment.LEADING))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(20)
													.addComponent(btnMoveBlock))))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(removeBlockAssignmentBtn)
											.addGap(33)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(levelLayout, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(redLbl)
										.addComponent(blueLbl)
										.addComponent(greenLbl)
										.addComponent(pointsLbl))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(updateBlockBtn)
										.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(pointsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(198))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(deleteBlockBtn)
									.addGap(18)
									.addComponent(addBlockBtn)
									.addGap(151))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblYourBlocks))
											.addGap(162))
										.addComponent(logOutBtn))
									.addGap(29)))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(backBtn)
							.addGap(78)
							.addComponent(saveChangesBtn)
							.addPreferredGap(ComponentPlacement.RELATED, 748, Short.MAX_VALUE)
							.addComponent(adminDashboardBtn)
							.addGap(96))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(85)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(blockAssignmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSelectABlockassignment))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(gridHorizontalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHorizontalPosition)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSelectALevel)
										.addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblYourBlocks))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(updateBlockBtn)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(redLbl)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(logOutBtn)
								.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(blueLbl))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(greenLbl))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(pointsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(pointsLbl))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(addBlockBtn)
										.addComponent(deleteBlockBtn))
									.addGap(152)
									.addComponent(adminDashboardBtn)
									.addGap(20))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(levelLayout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(167)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblVerticalPosition)
												.addComponent(gridVerticalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnPositionBlocks)
											.addGap(15)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(newGridHorizontalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewHorizontalPosition))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(newGridVerticalPositionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewVerticalPosition))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnMoveBlock)
											.addGap(30)
											.addComponent(removeBlockAssignmentBtn)))
									.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(backBtn)
										.addComponent(saveChangesBtn))))))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	// Christina
	private void refreshBlocks() {
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
			yourBlocksComboBox.addItem("");

			try {
			for(TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				gameBlocks.put(index, block);
				yourBlocksComboBox.addItem("R: " + block.getRed() + " G: " + block.getGreen() + " B: " + block.getBlue() + " Points: " + block.getPoints());
				index++;
			}
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
		
		//pack()? is it needed? check btms tutorial 5
	} 
	
	private void refreshBlockAssignmentComboBox() {
		errorMessage.setText("");
		gridHorizontalPositionTextField.setText("");
		gridVerticalPositionTextField.setText("");
		newGridHorizontalPositionTextField.setText("");
		newGridVerticalPositionTextField.setText("");
		blockAssignmentComboBox.removeAllItems();
		blockAssignmentComboBox.addItem("");
		blockAssignments = new HashMap<Integer, TOGridCell>();
		blocks = new HashMap<TOBlock, TOGridCell>();
		int level = levelComboBox.getSelectedIndex();
		int index = 0;
		try {
			for(TOGridCell cell: Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level + 1)) {
				TOBlock block = Block223Controller.getBlockOfCurrentDesignableGame(cell.getId());
				blockAssignments.put(index, cell);
				blocks.put(block, cell);
				blockAssignmentComboBox.addItem("R: " + cell.getRed() + " G: " + cell.getGreen() + " B: " + cell.getBlue() + " Points: " + cell.getPoints() + " X: " + cell.getGridHorizontalPosition() + " Y: " + cell.getGridVerticalPosition());
				index++;
			}
		} catch (InvalidInputException e) {
			
		}
	}
	private void refreshLevels() {
		errorMessage.setText("");
		levelComboBox.removeAllItems();
		levels = new HashMap<Integer, List<TOGridCell>>();
		try {
			int nrLevels = Block223Controller.getCurrentDesignableGame().getNrLevels();
			for(int i = 0; i < nrLevels; i++) {
				levels.put(i, Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(i+1));
				levelComboBox.addItem("Level " + (i+1));
			}
			levelComboBox.setSelectedIndex(0);
			((LevelLayout) levelLayout).setLevel(1);
		} catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	
	private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new UpdateSettingPage();
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
		if (selectedBlock < 1) {
			errorMessage.setText("A block needs to be selected to be deleted! ");
			return;
		}
			// call the controller 
			try {

				//TOGridCell cell = blocks.get(gameBlocks.get(selectedBlock-1));
				//Block223Controller.removeBlock(levelComboBox.getSelectedIndex()+1, cell.getGridHorizontalPosition(), cell.getGridVerticalPosition());
				Block223Controller.deleteBlock(gameBlocks.get(selectedBlock-1).getId());
				//update visuals 
				refreshBlocks();
				refreshBlockAssignmentComboBox();
				((LevelLayout) levelLayout).setBlockAssignments();
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
	}
	
	// Haluk's methods
	// method for Update Block in a Game feature
	private void updateBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlockIndex = yourBlocksComboBox.getSelectedIndex();
		if(selectedBlockIndex <= 0) {
			errorMessage.setText("A block needs to be selected to be updated! ");
			return;
		}
			// call the controller 
			TOBlock block = gameBlocks.get(selectedBlockIndex-1);
			try {
				int red = Integer.parseInt(redTextField.getText());
				int blue = Integer.parseInt(blueTextField.getText());
				int green = Integer.parseInt(greenTextField.getText());
				int points = Integer.parseInt(pointsTextField.getText());
				Block223Controller.updateBlock(block.getId(), red, green, blue, points);
				refreshBlocks();
				refreshBlockAssignmentComboBox();
				((LevelLayout)levelLayout).setBlockAssignments();
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			} catch (NumberFormatException e) {
				errorMessage.setText(e.getMessage());
			}
	}
	
	private void positionBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlockIndex = yourBlocksComboBox.getSelectedIndex();
		if(selectedBlockIndex <= 0) {
			errorMessage.setText("A block needs to be selected to be positioned! ");
			return;
		}
		
		TOBlock block = gameBlocks.get(selectedBlockIndex-1);

		try {
			int h = Integer.parseInt(gridHorizontalPositionTextField.getText());
			int v = Integer.parseInt(gridVerticalPositionTextField.getText());
			int level = levelComboBox.getSelectedIndex() + 1;
			Block223Controller.positionBlock(block.getId(), level, h, v);
			refreshBlockAssignmentComboBox();
			((LevelLayout) levelLayout).setBlockAssignments();
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		} catch (NumberFormatException e) {
			errorMessage.setText("You must have numerical values for the horizontal and vertical positions.");
		}
	}
	
	private void moveBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlockAssignmentIndex = blockAssignmentComboBox.getSelectedIndex();
		if(selectedBlockAssignmentIndex <= 0) {
			errorMessage.setText("A block assignment has to be selected to move a block! ");
			return;
		}
		
		TOGridCell cell = blockAssignments.get(selectedBlockAssignmentIndex-1);
		try {
			int newH = Integer.parseInt(newGridHorizontalPositionTextField.getText());
			int newV = Integer.parseInt(newGridVerticalPositionTextField.getText());
			int level = levelComboBox.getSelectedIndex() + 1;
			Block223Controller.moveBlock(level, cell.getGridHorizontalPosition(), cell.getGridVerticalPosition(), newH, newV);
			refreshBlockAssignmentComboBox();
			((LevelLayout)levelLayout).setBlockAssignments();
		} catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		} catch(NumberFormatException e) {
			errorMessage.setText("You must have numerical values for your new horizontal and new vertical positions.");
		}
	}
	
	// method for Remove Block from a Level 
	private void removeBlockAssignmentBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		int selectedBlockAssignmentIndex = blockAssignmentComboBox.getSelectedIndex();
		if(selectedBlockAssignmentIndex <= 0) {
			errorMessage.setText("A block assignment has to be selected to be removed! ");
			return;
		}
		TOGridCell cell = blockAssignments.get(selectedBlockAssignmentIndex-1);
		try {
			int level = levelComboBox.getSelectedIndex() + 1;
			Block223Controller.removeBlock(level, cell.getGridHorizontalPosition(), cell.getGridVerticalPosition());
			refreshBlockAssignmentComboBox();
			((LevelLayout)levelLayout).setBlockAssignments();
		}catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}catch(NumberFormatException e) {
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
	// to display the info of the currently selected block in the respective text fields 
	private void yourBlocksComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedBlockIndex = yourBlocksComboBox.getSelectedIndex();
		if(selectedBlockIndex>=1) {
			TOBlock block = gameBlocks.get(selectedBlockIndex-1);
			redTextField.setText(Integer.toString(block.getRed()));
			blueTextField.setText(Integer.toString(block.getBlue()));
			greenTextField.setText(Integer.toString(block.getGreen()));
			pointsTextField.setText(Integer.toString(block.getPoints()));
		} else { 
			redTextField.setText("");
			blueTextField.setText("");
			greenTextField.setText("");
			pointsTextField.setText("");
		}
	}
	//comment this out when running the program
	private void levelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedLevelIndex = levelComboBox.getSelectedIndex();
		if(selectedLevelIndex >= 0) {
			refreshBlockAssignmentComboBox();
			((LevelLayout) levelLayout).setLevel(selectedLevelIndex+1);
		}
	}
	//comment this out when running the program
	private void blockAssignmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedBlockAssignmentIndex = blockAssignmentComboBox.getSelectedIndex();
		if(selectedBlockAssignmentIndex >= 1) {
			TOGridCell cell = blockAssignments.get(selectedBlockAssignmentIndex-1);
			gridHorizontalPositionTextField.setText(Integer.toString(cell.getGridHorizontalPosition()));
			gridVerticalPositionTextField.setText(Integer.toString(cell.getGridVerticalPosition()));
			yourBlocksComboBox.setSelectedIndex(selectedBlockAssignmentIndex-1);
		} else { 
			gridHorizontalPositionTextField.setText("");
			gridVerticalPositionTextField.setText("");
			newGridVerticalPositionTextField.setText("");
			newGridHorizontalPositionTextField.setText("");
		}
	}
	
	private void saveChangesBtnActionPerformed(java.awt.event.ActionEvent evt) {
		errorMessage.setText("");
		try {
			Block223Controller.saveGame();
			errorMessage.setText("Save Successful!");
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	private void adminDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {
		frame.dispose();
		new AdminDashBoardPage();
	}
}
