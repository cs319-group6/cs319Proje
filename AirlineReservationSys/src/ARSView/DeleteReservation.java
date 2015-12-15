package ARSView;

import ARSController.AppManager;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class DeleteReservation extends JPanel {

	AppManager manager;
	private JTextField socialNo;
	private JTextField name;
	private JTextField surname;
	private JTextField phone;
	public DeleteReservation() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(14, 32, 410, 152);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TC Number");
		lblNewLabel.setBounds(10, 11, 74, 14);
		panel_1.add(lblNewLabel);
		
		socialNo = new JTextField();
		socialNo.setBounds(10, 28, 86, 20);
		panel_1.add(socialNo);
		socialNo.setColumns(10);
		socialNo.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          
		    	  AppManager.getPassenger(socialNo.getText());
		    	  if(AppManager.ResPassenger != null){
			    	  name.setText(AppManager.ResPassenger.getName()  + " " + AppManager.ResPassenger.getSurname());
			    	  surname.setText(AppManager.ResPassenger.getEmail());
			    	  phone.setText(AppManager.ResPassenger.getPhoneNo());
		    	  }else{
		    		  AppManager.error(AppManager.NOPASSENGERFOUND);
		    		  name.setText("");
		    		  surname.setText("");
		    		  phone.setText("");
		    	  }
		        }
		      });
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(26, 83, 74, 14);
		panel_1.add(lblName);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(26, 109, 95, 20);
		panel_1.add(name);
		
		JLabel lblSurname = new JLabel("Email:");
		lblSurname.setBounds(158, 83, 74, 14);
		panel_1.add(lblSurname);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(158, 109, 95, 20);
		panel_1.add(surname);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(288, 83, 86, 14);
		panel_1.add(lblPhone);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(288, 109, 95, 20);
		panel_1.add(phone);
		
		JButton btnGetReservations = new JButton("Get Reservations");
		btnGetReservations.setBounds(145, 196, 152, 23);
		btnGetReservations.addActionListener(new ActionListener() {
			//todo
			public void actionPerformed(ActionEvent e) {
				if(name.getText().equals("")|| surname.getText().equals("") || phone.getText().equals("")){
					AppManager.error(AppManager.EMPTYFIELD);
				}else
					AppManager.update(manager.PROCEEDTORESERVATIONLIST);
			}
		});
		panel.add(btnGetReservations);
		
		JButton button = new JButton("<- Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.update(manager.BACK);

			}
		});
		button.setBounds(10, 244, 89, 23);
		panel.add(button);
		
		JLabel lblPassenger = new JLabel("Passenger");
		lblPassenger.setBounds(22, 11, 77, 14);
		panel.add(lblPassenger);
	}
	


}
