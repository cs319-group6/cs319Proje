package ARSView;

import ARSController.AppManager;


import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

	private int action;
    AppManager manager;
    

	public MainMenu() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JButton newResBtn = new JButton("New Reservation");
		newResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				action =AppManager.PROCEEDTORESERVATON;
                manager.update(action);

			}
		});
		newResBtn.setBounds(144, 41, 131, 23);
		panel.add(newResBtn);
		
		JButton delResBtn = new JButton("Delete Reservation");

        delResBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = AppManager.PROCEEDTODELETERES;
                manager.update(action);
            }
        });
		delResBtn.setBounds(144, 99, 131, 23);
		panel.add(delResBtn);
		
		JButton accSetBtn = new JButton("Account Settings");
		accSetBtn.setBounds(144, 161, 131, 23);

        accSetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = AppManager.PROCEEDACCSETTINGS;
                manager.update(action);
            }
        });

		panel.add(accSetBtn);
		
		JButton logoutBtn = new JButton("Log out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                action = 0;
                manager.update(action);

			}
		});
		logoutBtn.setBounds(334, 244, 86, 23);
		panel.add(logoutBtn);
	}

}
