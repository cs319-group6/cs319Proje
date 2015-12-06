package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class DeleteReservation extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public DeleteReservation() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 22, 410, 152);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TC Number");
		lblNewLabel.setBounds(10, 11, 74, 14);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 28, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name*");
		lblName.setBounds(131, 11, 74, 14);
		panel_1.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(131, 28, 86, 20);
		panel_1.add(textField_1);
		
		JLabel lblSurname = new JLabel("Surname*");
		lblSurname.setBounds(250, 11, 74, 14);
		panel_1.add(lblSurname);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(250, 28, 86, 20);
		panel_1.add(textField_2);
		
		JLabel lblPhone = new JLabel("Phone Number*");
		lblPhone.setBounds(10, 79, 86, 14);
		panel_1.add(lblPhone);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 96, 86, 20);
		panel_1.add(textField_3);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth*");
		lblDateOfBirth.setBounds(131, 79, 86, 14);
		panel_1.add(lblDateOfBirth);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(131, 96, 86, 20);
		panel_1.add(textField_4);
		
		JButton btnGetReservations = new JButton("Get Reservations");
		btnGetReservations.setBounds(145, 196, 152, 23);
		panel.add(btnGetReservations);
		
		JButton button = new JButton("<- Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(10, 244, 89, 23);
		panel.add(button);
		
		JLabel lblPassenger = new JLabel("Passenger");
		lblPassenger.setBounds(22, 11, 77, 14);
		panel.add(lblPassenger);
	}

}
