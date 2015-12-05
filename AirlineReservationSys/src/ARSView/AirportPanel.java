package ARSView;

import javax.swing.JPanel;
import javax.swing.JButton;

public class AirportPanel extends JPanel {
	public AirportPanel() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Add New Airport");
		btnNewButton.setBounds(153, 75, 168, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete an Airport");
		btnNewButton_1.setBounds(153, 149, 168, 29);
		add(btnNewButton_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(23, 248, 117, 29);
		add(btnBack);
	}

}
