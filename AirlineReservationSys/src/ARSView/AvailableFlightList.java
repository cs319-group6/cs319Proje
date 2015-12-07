package ARSView;

import ARSController.AppManager;
import ARSModel.Flight;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AvailableFlightList extends JPanel {
	AppManager manager;
    private final int PROCEEDTOSEATPLAN = 6;
    private final int BACK = -1;
    private Flight selectedFlight;
    private ArrayList<Flight> flights;
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
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.update(PROCEEDTOSEATPLAN);
            }
        });
        add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(31, 236, 64, 40);
		btnBack.setBackground(Color.black);
		btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.update(BACK);
            }
        });
		add(btnBack);

	}


    public void setFlight(ArrayList<Flight> flights) {
    }
}
