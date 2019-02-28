// check my method: private void addBlockBtnActionPerformed(java.awt.event.ActionEvent evt) 
// as a template for how to call refresh methods and stuff like that... 
// also refer to code from the BtmsPage class in Btms Version 2 (tuto 5) and even the one from version 4!
package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;
import ca.mcgill.ecse223.block.controller.*;

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
	JComboBox<String> yourBlocksComboBox;
	JLabel lblYourBlocks;
	JLabel redLbl;
	JLabel blueLbl;
	JLabel greenLbl;
	JLabel pointsLbl;
	
	// Log Out
	JButton btnLogOut;
	
	// error string
	private String error = null;
	// data elements: each JComboBox needs to know which model object an entry in it refers to
	// also, check my question abt this to Gunter: he said error messages also require hashmaps and any table we make requires one too 
	// add/remove block - Work in Progress: unsure what value type to identify block
	private HashMap<Integer, Integer> gameBlocks;
	
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
		btnLogOut = new JButton("Log Out");
		
		// Initializing Block Settings elements
		lblYourBlocks = new JLabel("YOUR BLOCKS");
		lblYourBlocks.setFont(new Font("Times New Roman", Font.BOLD, 25));
		 
		redLbl = new JLabel("Red");
		
		blueLbl = new JLabel("Blue");
		
		greenLbl = new JLabel("Green");
		
		pointsLbl = new JLabel("Points");
		
		// Delete Block
		JButton deleteBlockBtn = new JButton("Delete Block");
		deleteBlockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		// Add Block
		JButton addBlockBtn = new JButton("Add Block");
		addBlockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		// listeners for addBlock
				addBlockBtn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						addBlockBtnActionPerformed(evt);
					}
				});
		
		// Group Layout of Page
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(982, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLogOut)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pointsLbl)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(blueLbl)
										.addComponent(greenLbl)
										.addComponent(redLbl))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pointsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(greenTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(44))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deleteBlockBtn)
							.addGap(29)
							.addComponent(addBlockBtn)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblYourBlocks)
								.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
							.addGap(35))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogOut)
					.addGap(56)
					.addComponent(lblYourBlocks)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yourBlocksComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(redTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(redLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(blueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(blueLbl))
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
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private void refreshData() throws InvalidInputException {
		refreshBlocks();
	}
	private void refreshBlocks() throws InvalidInputException {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
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
			gameBlocks = new HashMap<Integer,Integer>();
			yourBlocksComboBox.removeAllItems();
			index = 0;
			for(TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				gameBlocks.put(index, block.getId());
				yourBlocksComboBox.addItem("Red: " + block.getRed() + "Green: " + block.getGreen() + "Blue: " + block.getBlue() + "Points: " + block.getPoints());
				index++;
			}
		}
		//pack()? is it needed? check btms tutorial 5
	}	
	private void addBlockBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message 
		error = null;
		
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
		} catch (NumberFormatException e) {
			error = "All field entries need to be numerical values!";
		} // it dsnt seem to make sense: where is the error printed on the screen? in refreshData method! (check btms tutorial) 
		if(error.length() == 0) {
			try {
				Block223Controller.addBlock(red, green, blue, points);
				// you have to wrap this refresh call in a try catch block
				// not sure yet if need to call refreshData() or just refreshBlocks()
				refreshBlocks();
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
	}
}
