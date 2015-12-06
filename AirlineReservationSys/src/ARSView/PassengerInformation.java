package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class PassengerInformation extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	public PassengerInformation() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 11, 410, 113);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("TC Number");
		label_1.setBounds(10, 10, 53, 14);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(10, 32, 86, 20);
		textField.setColumns(10);
		panel_1.add(textField);
		
		JLabel label_2 = new JLabel("Name*");
		label_2.setBounds(131, 10, 33, 14);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(131, 32, 86, 20);
		textField_1.setColumns(10);
		panel_1.add(textField_1);
		
		JLabel label_3 = new JLabel("Surname*");
		label_3.setBounds(243, 10, 48, 14);
		panel_1.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 82, 86, 20);
		textField_2.setColumns(10);
		panel_1.add(textField_2);
		
		JLabel label_4 = new JLabel("Phone Number*");
		label_4.setBounds(106, 63, 76, 14);
		panel_1.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(243, 32, 86, 20);
		textField_3.setColumns(10);
		panel_1.add(textField_3);
		
		JLabel label_5 = new JLabel("Date of Birth*");
		label_5.setBounds(10, 63, 67, 14);
		panel_1.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(106, 82, 86, 20);
		textField_4.setColumns(10);
		panel_1.add(textField_4);
		
		JLabel lblEmail = new JLabel("E-mail*");
		lblEmail.setBounds(202, 63, 76, 14);
		panel_1.add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(202, 82, 86, 20);
		panel_1.add(textField_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Citizenship");
		comboBox.setBounds(305, 82, 95, 20);
		panel_1.add(comboBox);
		
		JLabel lblCitizenship = new JLabel("Citizenship");
		lblCitizenship.setBounds(305, 63, 76, 14);
		panel_1.add(lblCitizenship);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 135, 410, 114);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(0, 0, 410, 113);
		panel_2.add(panel_3);
		
		JLabel label_6 = new JLabel("TC Number");
		label_6.setBounds(10, 10, 53, 14);
		panel_3.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(10, 32, 86, 20);
		panel_3.add(textField_6);
		
		JLabel label_7 = new JLabel("Name*");
		label_7.setBounds(131, 10, 33, 14);
		panel_3.add(label_7);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(131, 32, 86, 20);
		panel_3.add(textField_7);
		
		JLabel label_8 = new JLabel("Surname*");
		label_8.setBounds(243, 10, 48, 14);
		panel_3.add(label_8);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(10, 82, 86, 20);
		panel_3.add(textField_8);
		
		JLabel label_9 = new JLabel("Phone Number*");
		label_9.setBounds(106, 63, 76, 14);
		panel_3.add(label_9);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(243, 32, 86, 20);
		panel_3.add(textField_9);
		
		JLabel label_10 = new JLabel("Date of Birth*");
		label_10.setBounds(10, 63, 67, 14);
		panel_3.add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(106, 82, 86, 20);
		panel_3.add(textField_10);
		
		JLabel label_11 = new JLabel("E-mail*");
		label_11.setBounds(202, 63, 76, 14);
		panel_3.add(label_11);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(202, 82, 86, 20);
		panel_3.add(textField_11);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Citizenship");
		comboBox_1.setBounds(305, 82, 95, 20);
		panel_3.add(comboBox_1);
		
		JLabel label_12 = new JLabel("Citizenship");
		label_12.setBounds(305, 63, 76, 14);
		panel_3.add(label_12);
		
		JButton btnNewButton = new JButton("<- Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 255, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(331, 255, 89, 23);
		panel.add(btnFinish);
		
		JLabel lblPassenger = new JLabel("Passenger");
		lblPassenger.setBounds(26, 0, 89, 14);
		panel.add(lblPassenger);
		
		JLabel label = new JLabel("Passenger");
		label.setBounds(20, 121, 89, 14);
		panel.add(label);
	}
}
