package fummy;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		setBackground(Color.white);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(197, 144, 89, 23);
		btnLogin.setBackground(Color.black);
		btnLogin.setContentAreaFilled(false);
		add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(171, 82, 133, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(87, 85, 66, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Pass");
		lblPassword.setBounds(87, 116, 46, 14);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 113, 133, 20);
		add(passwordField);

	}
}
