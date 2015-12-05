package ARSView;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainPanel extends JPanel {
	public AdminMainPanel() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Flights");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(154, 61, 135, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Users");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		btnNewButton_1.setBounds(154, 108, 135, 29);
		add(btnNewButton_1);
		
		JButton btnAirports = new JButton("Airports");
		btnAirports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				
			}
		});
		btnAirports.setBounds(154, 155, 135, 29);
		add(btnAirports);
		
		JButton btnAccountsettings = new JButton("Account Settings");
		btnAccountsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		btnAccountsettings.setBounds(154, 196, 135, 29);
		add(btnAccountsettings);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		btnLogout.setBounds(327, 6, 117, 29);
		add(btnLogout);
		setVisible(true);
	}
}
