package ARSView;

import javax.swing.JPanel;

import ARSController.AppManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainPanel extends JPanel {
	public AdminMainPanel() {
		setLayout(null);
		
		JButton btnFlights = new JButton("Flights");
		btnFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.PROCEEDTOFLIGHTMENU);
			}
		});
		btnFlights.setBounds(154, 61, 135, 29);
		add(btnFlights);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				AppManager.update(AppManager.PROCEEDTOUSER);
			}
		});
		btnUsers.setBounds(154, 108, 135, 29);
		add(btnUsers);
		
		JButton btnAirports = new JButton("Airports");
		btnAirports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				AppManager.update(AppManager.PROCEEDTOAIRPORT);
			}
		});
		btnAirports.setBounds(154, 155, 135, 29);
		add(btnAirports);
		
		JButton btnAccountsettings = new JButton("Account Settings");
		btnAccountsettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				AppManager.update(AppManager.PROCEEDACCSETTINGS);
			}
		});
		btnAccountsettings.setBounds(154, 196, 135, 29);
		add(btnAccountsettings);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				AppManager.update(0);
			}
		});
		btnLogout.setBounds(327, 6, 117, 29);
		add(btnLogout);
		setVisible(true);
	}
}
