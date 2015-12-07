package ARSView;

import ARSController.AppManager;


import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
    //AppManager manager;
    //private final int PROCEED = 1;

    private JPasswordField passwordField;
    private JTextField textField;

    protected int action;
    protected String id;
    protected char[] password;

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
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id = textField.getText();
				password = passwordField.getPassword();
                action = AppManager.PROCEEDTOMAIN;
                AppManager.update(action,id,password);
			}
		});

		add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(171, 82, 133, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Handle");
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
