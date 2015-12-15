package ARSView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import ARSController.AppManager;
import ARSModel.Reservation;

public class ReservationListPanel extends JPanel {
	//private ArrayList<Reservation> reservations;
    private JTable table;
    JPanel flightsPanel;
    JScrollPane scrollPane;
    JButton[] buttons;
	/**
	 * Create the panel.
	 */

	public ReservationListPanel() {
		setLayout(null);
		
		JButton btnNext = new JButton("Delete");
		btnNext.setBounds(326, 241, 89, 23);
		btnNext.setBackground(Color.black);
		btnNext.setContentAreaFilled(false);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//TODO for each reservation in reservations at selected rowds add to delreservations
            	if(table.getSelectedRows().length > 0){	
            		for(int i = 0; i < table.getSelectedRows().length; i++){
	            		AppManager.DelReservations.add(AppManager.reservations.get(table.getSelectedRows()[i]));
	            		
	            	}
            	
            		if(!AppManager.deleteReservations())
            			AppManager.error(AppManager.ERROR);
            	}
            	else{
            		AppManager.error(AppManager.NORESERVATIONSELECTED);
            	}
            }
        });
        add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(26, 241, 84, 23);
		btnBack.setBackground(Color.black);
		btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppManager.update(AppManager.BACK);
            }
        });
		add(btnBack);
	
		AppManager.reservations = AppManager.getReservations();
		System.out.println(AppManager.reservations);
		if(AppManager.reservations != null){
			String[] column = {"Reservation No" , "Departure", "Destination", "Date", "Time", "Seat"};  
			Object[][] row = new Object[AppManager.reservations.size()][6];
			
			for(int i = 0; i <AppManager.reservations.size(); i++){
				
				row[i][0] = ""+ AppManager.reservations.get(i).getID();
				row[i][1] = ""+ AppManager.reservations.get(i).getFlight().getDeparture().getName();
				row[i][2]  = "" + AppManager.reservations.get(i).getFlight().getDestination().getName();
				row[i][3] = ""+ AppManager.reservations.get(i).getFlight().getDateTime().getDate();
				row[i][4]  = "" + AppManager.reservations.get(i).getFlight().getDateTime().getTime();
				row[i][5]  = "" + AppManager.reservations.get(i).getSeats();
			}
			table = new JTable(row, column);
			/*TableModel model = new DefaultTableModel() {
			      public boolean isCellEditable(int rowIndex, int mColIndex) {
			        return false;
			      }
			    };*/
			//table.setModel(new MyModel());
			table.setAutoResizeMode(WHEN_FOCUSED);
			table.setCellEditor(null);
			//table.setBounds(26,26,389,203);
			//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setVisible(true);
			scrollPane = new JScrollPane();
			scrollPane.setBounds(26,26,389,203);
			scrollPane.setVisible(true);
			scrollPane.setViewportView(table);
			this.add(scrollPane);
		}else{
			//AppManager.error(AppManager.ERROR);
		}
	}
	

}
