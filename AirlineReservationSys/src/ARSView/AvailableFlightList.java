package ARSView;


import ARSController.AppManager;
import ARSModel.Flight;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AvailableFlightList extends JPanel {
    //private ArrayList<Flight> flights;
    private JTable table;
    JPanel flightsPanel;
    JScrollPane scrollPane = new JScrollPane();
    JButton[] buttons;
	/**
	 * Create the panel.
	 */

	public AvailableFlightList() {
		setLayout(null);
		
		JButton btnNext = new JButton("Proceed");
		btnNext.setBounds(326, 241, 89, 23);
		btnNext.setBackground(Color.black);
		btnNext.setContentAreaFilled(false);
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(table.getSelectedRow() >= 0){
	            	AppManager.ResFlight = AppManager.flights.get(table.getSelectedRow());
	            	//System.out.println(AppManager.ResFlight);
	                AppManager.update(AppManager.PROCEEDTOSEATPLAN);
            	}else{
            		AppManager.error(AppManager.NOFLIGHTSELECTED);
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
	
		if(AppManager.flights != null){
			String[] column = {"Flight ID" ,"Departure", "Destination ", "Departure Time", "Duration "};  
			Object[][] row = new Object[AppManager.flights.size()][5];
			
			buttons = new JButton[AppManager.flights.size()];
			for(int i = 0; i <AppManager.flights.size(); i++){
				Flight f = AppManager.flights.get(i);
				//System.out.println("Flight : " +f);
				JButton flightBtn = new JButton("Select");
				flightBtn.setName(String.valueOf(i));
				flightBtn.setBounds(26, 241, 84, 23);
				flightBtn.setBackground(Color.black);
				flightBtn.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	int j = Integer.parseInt(flightBtn.getName());
		            	AppManager.ResFlight = AppManager.flights.get(j);
		                AppManager.update(AppManager.PROCEEDTOSEATPLAN);
		            }
		        });
				buttons[i] = flightBtn;
				row[i][0] = ""+ AppManager.flights.get(i).getFlightID();
				row[i][1] = ""+ AppManager.flights.get(i).getDeparture().getName();
				row[i][2] = ""+ AppManager.flights.get(i).getDestination().getName();
				row[i][3] = ""+ AppManager.flights.get(i).getDateTime().getTime();
				row[i][4]  = "" + AppManager.flights.get(i).getDuration();
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
			table.setBounds(26,26,389,203);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setVisible(true);
			scrollPane = new JScrollPane();
			scrollPane.setBounds(26,26,389,203);
			scrollPane.setVisible(true);
			scrollPane.setViewportView(table);
			this.add(scrollPane);
			
		}
	}
	
	
	public void initiliaze(){
		if( AppManager.flights.size() < 1){
			AppManager.error(AppManager.NOFLIGHTAVAILABLE);
			AppManager.update(AppManager.BACK);
		}
		
	}

    public void setFlight(ArrayList<Flight> flights) {
    }
}
