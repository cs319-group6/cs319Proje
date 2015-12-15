package ARSView;

import ARSModel.Airport;
import ARSModel.ComboBItem;
import ARSController.AppManager;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.border.Border;


public class ReservationPanel extends JPanel {
    AppManager manager;
    public final String[] Day = {"1", "2", "3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31" };
    public final String[] Month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    public final String[] Year = {"2015", "2016"};
    public JComboBox destinationCB, departureCB;

	public ReservationPanel() {
		setLayout(null);
		setBackground(Color.white);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(11, 25, 46, 14);
		add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(11, 76, 46, 14);
		add(lblTo);
		
		Vector<ComboBItem> combo = getComboBoxItems(null, null);
		
		departureCB = new JComboBox(combo);
		departureCB.setBackground(Color.white);
		departureCB.setBounds(67, 25, 254, 20);
		departureCB.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	/*if(destinationCB.getSelectedItem() == null && departureCB.getSelectedItem() != null){
			    	Vector<ComboBItem> comboA = getComboBoxItems(((ComboBItem)(departureCB.getSelectedItem())).getAirport(), null);
			    	if(comboA.size() >1 ){
			    		destinationCB.removeAllItems();
				    	for(int i = 0;i < comboA.size();i++){
				    		destinationCB.addItem(comboA.get(i));
				    	}
			    	}else{
			    		AppManager.error(AppManager.NOFLIGHTAVAILABLE);
			    	}
		    	}*/
		        
		    }
		});
		//departureCB.setSelectedItem(null);
		add(departureCB);
		
		destinationCB = new JComboBox(combo);
		destinationCB.setBounds(67, 73, 254, 20);
		destinationCB.setBackground(Color.white);
		destinationCB.addActionListener (new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				/*if(departureCB.getSelectedItem() == null && destinationCB.getSelectedItem() != null){
			    	Vector<ComboBItem> comboA = getComboBoxItems(((ComboBItem)(destinationCB.getSelectedItem())).getAirport(), null);
			    	
			    	if(comboA.size() > 1 ){
			    		departureCB.removeAllItems();
				    	for(int i = 0;i < comboA.size();i++){
				    		departureCB.addItem(comboA.get(i));
				    	}
			    	}else{
			    		AppManager.error(AppManager.NOFLIGHTAVAILABLE);
			    	}
		    	}/*if(destinationCB.getSelectedItem() == null){
		    		Vector<ComboBItem> comboB = getComboBoxItems(null, null);
		    		departureCB.removeAllItems();
			    	for(int i = 0;i < comboB.size();i++){
			    		departureCB.addItem(comboB.get(i));
			    	}
		    	}*/
			}
		});
		//destinationCB.setSelectedItem(null);
		add(destinationCB);
		
		JComboBox DayCB = new JComboBox(Day);
		DayCB.setBackground(Color.white);
		DayCB.setToolTipText("Day\r\n");
		DayCB.setBounds(67, 122, 74, 20);
		add(DayCB);
		
		JComboBox MonthCB = new JComboBox(Month);
		MonthCB.setBackground(Color.white);
		MonthCB.setToolTipText("Month");
		MonthCB.setBounds(153, 122, 74, 20);
		add(MonthCB);
		
		JComboBox YearCB = new JComboBox(Year);
		YearCB.setBackground(Color.white);
		YearCB.setToolTipText("Year");
		YearCB.setBounds(233, 122, 88, 20);
		add(YearCB);
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(11, 125, 46, 14);
		add(lblDate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.black);
		btnBack.setContentAreaFilled(false);
		btnBack.setBounds(31, 236, 64, 40);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppManager.update(manager.BACK);

            }
        });
		add(btnBack);

	
		JButton listFlightButton = new JButton("List Flights");
		listFlightButton.setBackground(Color.black);
		listFlightButton.setContentAreaFilled(false);
		listFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( departureCB.getSelectedItem()!= null && destinationCB.getSelectedItem() != null &&((ComboBItem)departureCB.getSelectedItem()).getAirport() != null && ((ComboBItem)destinationCB.getSelectedItem()).getAirport() != null 
						&& YearCB.getSelectedItem() != null && MonthCB.getSelectedItem() != null && DayCB.getSelectedItem() != null){
					int year = YearCB.getSelectedIndex()+2015;
					int month = MonthCB.getSelectedIndex();
					int day = DayCB.getSelectedIndex()+1;
					if(checkDate(year,month,day )){
						AppManager.ResDeparture = ((ComboBItem)departureCB.getSelectedItem()).getAirport();
						AppManager.ResDestination = ((ComboBItem)destinationCB.getSelectedItem()).getAirport();
						AppManager.ResDate = getDateinMilies(year, month, day);
		                AppManager.update(AppManager.LISTFLIGHT);
					}else{
						JOptionPane.showMessageDialog(AppManager.mainFrame,"Wrong Date","Selected Date cannot be before than today",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(AppManager.mainFrame,"Empty Field error","All fields must be filled ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		listFlightButton.setBounds(185, 236, 113, 40);
		add(listFlightButton);

	}
	
	private Vector<ComboBItem> getComboBoxItems(Airport departure, Airport destination){
		ArrayList<Airport> airports = new ArrayList<Airport>();
		if(departure == null && destination == null){
			airports = AppManager.getAllAirports();
		}else if(destination == null && departure != null){
			airports = AppManager.getDestAirportsFrom(departure);
		}else if(destination != null && departure == null){
			airports = AppManager.getDepartAirportsTo(departure);
		}
		Vector<ComboBItem> combo = new Vector<ComboBItem>(airports.size()+1);
		combo.add(null);
		for(int i = 0; i< airports.size(); i++ ){
			combo.add(new ComboBItem(airports.get(i)));
		}
		return combo;
		
	}
	
	private boolean checkDate(int year, int month, int day){
		Calendar c = Calendar.getInstance();

		// set the calendar to start of today
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		// and get that as a Date
		Date today = c.getTime();

		// or as a timestamp in milliseconds
		long todayInMillis = c.getTimeInMillis();

		// reuse the calendar to set user specified date
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		// and get that as a Date
		Date dateSpecified = c.getTime();

		// test your condition
		if (dateSpecified.before(today)) {
		  return false;
		} else {
		  return true;
		}
	}
	
	private long getDateinMilies(int year, int month, int day){
		Calendar c = Calendar.getInstance();

		// set the calendar to start of today
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		
		return c.getTime().getTime();
	}

	public void initialize(){
		Vector<ComboBItem> combo = getComboBoxItems(null, null);
		departureCB.removeAllItems();
    	for(int i = 0;i < combo.size();i++){
    		departureCB.addItem(combo.get(i));
    	}
    	destinationCB.removeAllItems();
    	for(int i = 0;i < combo.size();i++){
    		destinationCB.addItem(combo.get(i));
    	}
	}
}
