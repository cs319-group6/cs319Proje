package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ChangeAccountSettings extends JPanel {
	private JTextField passwordField;
	private JTextField newPassField;
	private JTextField retypeField;
	private JTextField changeEmailField;
	public ChangeAccountSettings() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JButton back = new JButton("<- Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		back.setBounds(10, 266, 89, 23);
		panel.add(back);
		
		JButton save = new JButton("Save Changes");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		save.setBounds(327, 266, 113, 23);
		panel.add(save);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBounds(10, 11, 430, 96);
		panel.add(topPanel);
		topPanel.setLayout(null);
		
		JPanel topInPanel = new JPanel();
		topInPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		topInPanel.setBounds(10, 7, 255, 78);
		topPanel.add(topInPanel);
		topInPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 11, 46, 14);
		topInPanel.add(nameLabel);
		
		JLabel empNameLabel = new JLabel("John Barker");
		empNameLabel.setBounds(66, 11, 91, 14);
		topInPanel.add(empNameLabel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 36, 46, 14);
		topInPanel.add(lblId);
		
		JLabel label = new JLabel("12345678");
		label.setBounds(66, 36, 91, 14);
		topInPanel.add(label);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 61, 46, 14);
		topInPanel.add(lblEmail);
		
		JLabel lblcom = new JLabel("12345678@****.com");
		lblcom.setBounds(66, 61, 147, 14);
		topInPanel.add(lblcom);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		bottomPanel.setBounds(10, 118, 430, 137);
		panel.add(bottomPanel);
		bottomPanel.setLayout(null);
		
		JLabel passwordLabel = new JLabel("Enter Password to Change Settings: ");
		passwordLabel.setBounds(10, 11, 205, 14);
		bottomPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(201, 8, 133, 20);
		bottomPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setBounds(10, 39, 205, 14);
		bottomPanel.add(newPasswordLabel);
		
		newPassField = new JTextField();
		newPassField.setColumns(10);
		newPassField.setBounds(201, 36, 133, 20);
		bottomPanel.add(newPassField);
		
		JLabel retypeLabel = new JLabel("Re-type:");
		retypeLabel.setBounds(10, 67, 205, 14);
		bottomPanel.add(retypeLabel);
		
		retypeField = new JTextField();
		retypeField.setColumns(10);
		retypeField.setBounds(201, 64, 133, 20);
		bottomPanel.add(retypeField);
		
		JLabel changeEmailLabel = new JLabel("Change Email:");
		changeEmailLabel.setBounds(10, 95, 205, 14);
		bottomPanel.add(changeEmailLabel);
		
		changeEmailField = new JTextField();
		changeEmailField.setColumns(10);
		changeEmailField.setBounds(201, 92, 133, 20);
		bottomPanel.add(changeEmailField);
		
		JLabel employee = new JLabel("Employee");
		employee.setBounds(21, 0, 46, 14);
		panel.add(employee);
		
		JLabel changes = new JLabel("Changes");
		changes.setBounds(20, 106, 46, 14);
		panel.add(changes);
	}
}
