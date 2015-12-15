package ARSView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ARSController.AppManager;
import ARSModel.ARSDate;
import ARSModel.Airport;
import ARSModel.ComboBItem;
import javax.swing.JTextField;

public class AddFlightPanel extends JPanel {
	private JComboBox departure, destination, year, month, day, hour, minute;
	public final String[] Day = {"01", "02", "03","04","05","06","07","08","09","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31" };
    public final String[] Month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    public final String[] Year = {"2015", "2016"};
    public final String[] Hour = {"00","01","02","03","04","05", "06", "07", "08", "09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    public final String[] Minute = {"01", "02", "03","04","05","06","07","08","09","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30",
            "31","32","33","34","35","36","37","38","39","40",
            "41","42","43","44","45","46","47","48","49","50",
            "51","52","53","54","55","56","57","58","59","00"
    };
    private JTextField duration;
	public AddFlightPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 288);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewFlight = new JLabel("New Flight");
		lblNewFlight.setBounds(16, 6, 82, 16);
		panel.add(lblNewFlight);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 34, 403, 207);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Vector<ComboBItem> combo = getComboBoxItems(null, null);
		JLabel lblFrom = new JLabel("Departure:");
		lblFrom.setBounds(6, 6, 101, 16);
		panel_1.add(lblFrom);
		
		 departure = new JComboBox(combo);
		departure.setBounds(0, 34, 144, 27);
		panel_1.add(departure);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(230, 6, 89, 16);
		panel_1.add(lblDestination);
		
		 destination = new JComboBox(combo);
		destination.setBounds(225, 34, 144, 27);
		panel_1.add(destination);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(10, 73, 61, 16);
		panel_1.add(lblDay);
		
		 day = new JComboBox(Day);
		day.setBounds(6, 92, 72, 27);
		panel_1.add(day);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(109, 73, 61, 16);
		panel_1.add(lblMonth);
		
		 month = new JComboBox(Month);
		month.setBounds(104, 92, 83, 27);
		panel_1.add(month);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(205, 73, 61, 16);
		panel_1.add(lblYear);
		
		 year = new JComboBox(Year);
		year.setBounds(205, 92, 89, 27);
		panel_1.add(year);
		
		JLabel lblHour = new JLabel("Hour:");
		lblHour.setBounds(10, 136, 61, 16);
		panel_1.add(lblHour);
		
		 hour = new JComboBox(Hour);
		hour.setBounds(10, 164, 72, 27);
		panel_1.add(hour);
		
		JLabel lblMinutes = new JLabel("Minutes:");
		lblMinutes.setBounds(109, 136, 78, 16);
		panel_1.add(lblMinutes);
		
		 minute = new JComboBox(Minute);
		minute.setBounds(109, 164, 78, 27);
		panel_1.add(minute);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(230, 136, 61, 16);
		panel_1.add(lblDuration);
		
		duration = new JTextField();
		duration.setBounds(224, 162, 80, 20);
		panel_1.add(duration);
		duration.setColumns(10);
		
		JButton button = new JButton("<- Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.BACK);
			}
		});
		button.setBounds(6, 253, 117, 29);
		panel.add(button);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(destination.getSelectedIndex()<0 || departure.getSelectedIndex()<0 || year.getSelectedIndex()<0 ||
						month.getSelectedIndex()<0 || day.getSelectedIndex()<0 || hour.getSelectedIndex()<0 || minute.getSelectedIndex()<0 || duration.getText().equals(""))){
					
					ARSDate dateTime = new ARSDate(year.getSelectedItem().toString() + "-" +month.getSelectedItem().toString() +"-" + day.getSelectedItem().toString(), hour.getSelectedItem().toString() +":" + minute.getSelectedItem().toString()+ ":00");
					if(checkDate(dateTime)){
						if(AppManager.addFlight(((ComboBItem)departure.getSelectedItem()).getAirport(),((ComboBItem)destination.getSelectedItem()).getAirport(), dateTime, Integer.parseInt(duration.getText())))
							AppManager.success();
					}else{
						AppManager.error(AppManager.WRONGDATE);
					}
					
				}else{
					AppManager.error(AppManager.EMPTYFIELD);
				}
			
			}
		});
		btnAdd.setBounds(315, 253, 117, 29);
		panel.add(btnAdd);
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
	
	private boolean checkDate(ARSDate dateTime){
		Calendar c = Calendar.getInstance();
		// and get that as a Date
		Date today = c.getTime();

		// or as a timestamp in milliseconds
		long todayInMillis = c.getTimeInMillis();


		// test your condition
		if (todayInMillis > dateTime.getMilies()) {
		  return false;
		} else {
		  return true;
		}
	}
	public void initiliaze(){
		departure.setSelectedIndex(0);
		destination.setSelectedIndex(0); 
		year.setSelectedIndex(0);
		month.setSelectedIndex(0);
		day.setSelectedIndex(0);
		hour.setSelectedIndex(0);
		minute.setSelectedIndex(0);
	}
}
