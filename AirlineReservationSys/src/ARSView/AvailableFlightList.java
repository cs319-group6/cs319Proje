package ARSView;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AvailableFlightList extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AvailableFlightList() {
		setBackground(Color.white);

		
		String columnNames[]= {"Departure","Arrival","Duration","FlightId","Cost"};
		setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 141, 430, -125);
		add(table);
		
		JButton btnNext = new JButton("Proceed");
		btnNext.setBounds(197, 144, 89, 23);
		btnNext.setBackground(Color.black);
		btnNext.setContentAreaFilled(false);
		add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(31, 236, 64, 40);
		try {
		    Image img1 = ImageIO.read(getClass().getResource("Ic_arrow_back_36px.png"));
		    btnBack.setIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}
		btnBack.setBackground(Color.black);
		btnBack.setContentAreaFilled(false);
		add(btnBack);

	}

}
