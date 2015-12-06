package ARSView;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {
	public MainMenu() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JButton newResBtn = new JButton("New Reservation");
		newResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newResBtn.setBounds(144, 41, 131, 23);
		panel.add(newResBtn);
		
		JButton delResBtn = new JButton("Delete Reservation");
		delResBtn.setBounds(144, 99, 131, 23);
		panel.add(delResBtn);
		
		JButton accSetBtn = new JButton("Account Settings");
		accSetBtn.setBounds(144, 161, 131, 23);
		panel.add(accSetBtn);
		
		JButton logoutBtn = new JButton("Log out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logoutBtn.setBounds(334, 244, 86, 23);
		panel.add(logoutBtn);
	}

}
