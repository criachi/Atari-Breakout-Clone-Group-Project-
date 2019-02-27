package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogInWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInWindow window = new LogInWindow();
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
	public LogInWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		GridBagLayout gbl_desktopPane = new GridBagLayout();
		gbl_desktopPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_desktopPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_desktopPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_desktopPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		desktopPane.setLayout(gbl_desktopPane);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblLogIn = new GridBagConstraints();
		gbc_lblLogIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogIn.gridx = 2;
		gbc_lblLogIn.gridy = 1;
		desktopPane.add(lblLogIn, gbc_lblLogIn);
		
		JLabel lblNewLabel = new JLabel("Username: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		desktopPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		desktopPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		desktopPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		desktopPane.add(passwordField, gbc_passwordField);
		
		JButton btnPlayer = new JButton("Log In");
		GridBagConstraints gbc_btnPlayer = new GridBagConstraints();
		gbc_btnPlayer.gridx = 3;
		gbc_btnPlayer.gridy = 7;
		desktopPane.add(btnPlayer, gbc_btnPlayer);
	}

}
