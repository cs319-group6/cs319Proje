package ARSModel;

import javax.swing.JPanel;

import ARSController.AppManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlightMenuPanel extends JPanel {
	public FlightMenuPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 288);
		add(panel);
		panel.setLayout(null);
		
		JButton btnAddFlight = new JButton("Add Flight");
		btnAddFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.PROCEEDTOADDFLIGHT);
			}
		});
		btnAddFlight.setBounds(142, 69, 117, 29);
		panel.add(btnAddFlight);
		
		JButton btnDeleteFlight = new JButton("Delete Flight");
		btnDeleteFlight.setBounds(142, 145, 117, 29);
		btnDeleteFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.PROCEEDTODELETEFLIGHT);
			}
		});
		panel.add(btnDeleteFlight);
		
		JButton button = new JButton("<- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.BACK);
			}
		});
		button.setBounds(6, 253, 117, 29);
		panel.add(button);
	}

}
