package ARSView;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ARSController.AppManager;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatPlan extends JPanel {
	
	JCheckBox [][] buttons = new JCheckBox[6][13];
	JLabel destination, departure, time, date, duration;
	JLabel [] seatNo = new JLabel [13];
	public SeatPlan() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(227, 0, 203, 278);
		panel.add(panel_1);
		panel_1.setLayout(null);
		int x = 40;
		int y = 20;
		for(int i = 0; i < buttons.length; i++ ){
			y = 20;
			if(i == 3)
				x = x + 20;
			for (int j = 0 ; j < buttons[i].length; j++){
				
				buttons[i][j] = new JCheckBox(""+ i + "," + j);
				buttons[i][j].setBackground(SystemColor.text);
				buttons[i][j].setForeground(Color.BLACK);
				buttons[i][j].setBounds(x, y, 14, 20);
				if(AppManager.ResFlight != null){
					buttons[i][j].setEnabled(AppManager.ResFlight.isAvailable(i,j));
					//buttons[i][j].setSelected(!AppManager.ResFlight.isAvailable(i,j));
				}
				buttons[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String seat = ((JCheckBox)(e.getSource())).getText();
						int row = Integer.parseInt(seat.substring(2));
						int column = Integer.parseInt(seat.substring(0, 1));
						
						if(((JCheckBox)(e.getSource())).isSelected()){
						//System.out.println(AppManager.ResFlight.getSeat(column,row));
							if(!AppManager.reserveSeat(column, row)){//(AppManager.ResFlight.getSeat(column,row))){
								((JCheckBox)(e.getSource())).setSelected(false);
							}
						}else{
							AppManager.removeReservation(column,row);
							//AppManager.ResFlight.getSeat(column,row-1).setAvailable(true);
						}
						System.out.println(AppManager.ResSeats);
					}
				});
				panel_1.add(buttons[i][j]);
				
				
				y = y + 20;
			}
			x = x + 24;
		}
		y = 20;
		for(int i = 0; i < buttons[0].length;i++){
			seatNo[i] = new JLabel(String.valueOf(i+1));
			seatNo[i].setBounds(8, y, 22, 14);
			panel_1.add(seatNo[i]);
			y = y +20;
		}
		
		JLabel lblNewLabel = new JLabel("A");
		lblNewLabel.setBounds(43, 0, 14, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblB = new JLabel("B");
		lblB.setBounds(64, 0, 14, 14);
		panel_1.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setBounds(87, 0, 14, 14);
		panel_1.add(lblC);
		
		JLabel lblD = new JLabel("D");
		lblD.setBounds(131, 0, 14, 14);
		panel_1.add(lblD);
		
		JLabel lblE = new JLabel("E");
		lblE.setBounds(155, 0, 14, 14);
		panel_1.add(lblE);
		
		JLabel lblF = new JLabel("F");
		lblF.setBounds(179, 0, 14, 14);
		panel_1.add(lblF);
		
		JLabel lblDeparture = new JLabel("Departure:");
		lblDeparture.setBounds(6, 25, 79, 16);
		panel.add(lblDeparture);
		
		JLabel lblDestination = new JLabel("Destination: ");
		lblDestination.setBounds(6, 64, 90, 16);
		panel.add(lblDestination);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(6, 98, 79, 16);
		panel.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(6, 139, 61, 16);
		panel.add(lblTime);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(6, 182, 79, 16);
		panel.add(lblDuration);
		
		departure = new JLabel("New label");
		departure.setBounds(97, 25, 79, 16);
		panel.add(departure);
		
		destination = new JLabel("New label");
		destination.setBounds(97, 64, 79, 16);
		panel.add(destination);
		
		date = new JLabel("New label");
		date.setBounds(97, 98, 79, 16);
		panel.add(date);
		
		time = new JLabel("");
		time.setBounds(97, 139, 79, 16);
		panel.add(time);
		
		duration = new JLabel("New label");
		duration.setBounds(97, 182, 79, 16);
		panel.add(duration);
		
		if(AppManager.ResFlight != null){
			departure.setText(AppManager.ResFlight.getDeparture().getName());
			destination.setText(AppManager.ResFlight.getDestination().getName());
			date.setText(AppManager.ResFlight.getDateTime().getDate());
			time.setText(AppManager.ResFlight.getDateTime().getTime());
			duration.setText(String.valueOf(AppManager.ResFlight.getDuration()));
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 243, 79, 29);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppManager.update(AppManager.BACK);
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(btnBack);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(125, 243, 90, 29);
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//AppManager.addReservation();
				if(AppManager.ResSeats.size() > 0){
				AppManager.update(AppManager.PROCEEDTOPASSENGERINFO);
				}else{
					AppManager.error(AppManager.NOSEATSELECTED);
				}
			}
		});
		panel.add(btnNext);
	}
	
	public void initialize(){
		destination.setText(AppManager.ResFlight.getDestination().getName());
		departure.setText(AppManager.ResFlight.getDeparture().getName());
		time.setText(AppManager.ResFlight.getDateTime().getTime());
		date.setText(AppManager.ResFlight.getDateTime().getDate());
		duration.setText(String.valueOf(AppManager.ResFlight.getDuration()));
		
		for(int i = 0; i< buttons.length; i++){
			for(int j = 0; j< buttons[i].length; j++){
				if(AppManager.ResFlight != null)
					buttons[i][j].setEnabled(AppManager.ResFlight.isAvailable(i,j));
			}
		}
	}
	
}
