package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayModePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayModePage window = new PlayModePage();
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
	public PlayModePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCurrentLevel = new JLabel("Current Level");
		lblCurrentLevel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblLives = new JLabel("Lives:");
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel hallOfFamePanel = new JPanel();
		hallOfFamePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null), new LineBorder(new Color(0, 255, 0), 8)));
		
		JLabel lblHallOfFame = new JLabel("Hall of Fame");
		lblHallOfFame.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton previousEntriesBtn = new JButton("Previous Entries");
		
		JButton followingEntriesBtn = new JButton("Following Entries");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblCurrentLevel)
					.addGap(152)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLives)
						.addComponent(lblScore))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
							.addComponent(lblHallOfFame)
							.addGap(102))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(325)
					.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(previousEntriesBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(followingEntriesBtn)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurrentLevel)
								.addComponent(lblLives))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblScore))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblHallOfFame)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(hallOfFamePanel, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(previousEntriesBtn)
							.addComponent(followingEntriesBtn)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public String takeInputs() {
		return null;
	}
	
	public void refresh() {
		PlayedGameLevel.setBlockAssignments();
		PlayedGameLevel.refreshGame();
	}
}
