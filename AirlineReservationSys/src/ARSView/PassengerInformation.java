package ARSView;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import ARSController.AppManager;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class PassengerInformation extends JPanel {
	private JTextField soNo;
	private JTextField name;
	private JTextField phone;
	private JTextField surname;
	private JTextField email;
	private JButton btnUpdate;
	public PassengerInformation() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 278);
		add(panel);
		panel.setLayout(null);
		
		JPanel passengerPanel = new JPanel();
		passengerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passengerPanel.setBounds(10, 32, 410, 208);
		panel.add(passengerPanel);
		passengerPanel.setLayout(null);
		
		JLabel label_1 = new JLabel("TC Number");
		label_1.setBounds(10, 6, 86, 14);
		passengerPanel.add(label_1);
		
		soNo = new JTextField();
		soNo.setBounds(10, 32, 86, 20);
		soNo.setColumns(10);
		soNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppManager.getPassenger(soNo.getText());
		    	  if(AppManager.ResPassenger != null){
			    	  name.setText(AppManager.ResPassenger.getName() );
			    	  surname.setText(AppManager.ResPassenger.getSurname());
			    	  email.setText(AppManager.ResPassenger.getEmail());
			    	  phone.setText(AppManager.ResPassenger.getPhoneNo());
			    	  name.setEditable(false);
			    	  surname.setEditable(false);
			    	  email.setEditable(false);
			    	  phone.setEditable(false);
			    	  btnUpdate.setText("Update");
		    	  }else{
		    		  AppManager.error(AppManager.NOPASSENGERFOUND);
		    		  name.setText("");
		    		  surname.setText("");
		    		  phone.setText("");
		    		  email.setText("");
		    		  name.setEditable(true);
			    	  surname.setEditable(true);
			    	  email.setEditable(true);
			    	  phone.setEditable(true);
			    	  btnUpdate.setText("Save");
		    	  }
				
			}
		});
		passengerPanel.add(soNo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 80, 53, 14);
		passengerPanel.add(lblName);
		
		name = new JTextField();
		name.setBounds(10, 104, 86, 20);
		name.setColumns(10);
		passengerPanel.add(name);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(132, 80, 81, 14);
		passengerPanel.add(lblSurname);
		
		phone = new JTextField();
		phone.setBounds(265, 104, 86, 20);
		phone.setColumns(10);
		passengerPanel.add(phone);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(268, 80, 56, 14);
		passengerPanel.add(lblPhone);
		
		surname = new JTextField();
		surname.setBounds(132, 104, 86, 20);
		surname.setColumns(10);
		passengerPanel.add(surname);
		
		email = new JTextField();
		email.setBounds(83, 169, 228, 20);
		email.setColumns(10);
		passengerPanel.add(email);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(83, 150, 76, 14);
		passengerPanel.add(lblEmail);
		
		JButton btnNewButton = new JButton("<- Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManager.cancelReservation();
				AppManager.update(AppManager.PROCEEDTOSEATPLAN);
				
			}
		});
		btnNewButton.setBounds(10, 255, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(331, 255, 89, 23);
		btnFinish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!AppManager.makeReservation()){
					AppManager.error(AppManager.RESERVATIONERROR);
				}else{
					AppManager.success();
					AppManager.update(AppManager.PROCEEDTOMAIN);
				}
			}
		});
		panel.add(btnFinish);
		
		JLabel lblPassenger = new JLabel("Passenger");
		lblPassenger.setBounds(24, 6, 89, 14);
		panel.add(lblPassenger);
		
		btnUpdate = new JButton("Save");
		btnUpdate.setBounds(216, 252, 89, 29);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()).getText().equals("Save")){
					if(soNo.isEditable()){
						if(!(name.getText().equals("") && surname.getText().equals("") && email.getText().equals("") && soNo.getText().equals("") && phone.getText().equals("") )){
							if(!AppManager.addPassenger(name.getText(), surname.getText(),email.getText(),soNo.getText(),phone.getText())){
								AppManager.error(AppManager.PASSENGERSAVEERROR);
							}else{
								soNo.setEditable(true);
								name.setEditable(false);
						    	surname.setEditable(false);
						    	email.setEditable(false);
						    	phone.setEditable(false);
						    	btnUpdate.setText("Update");
							}
						}else{
							AppManager.error(AppManager.EMPTYFIELD);
						}
				//		AppManager.addPassenger(name.getText(), surname.getText(),email.getText(),soNo.getText(),phone.getText());
					}else{
						if(AppManager.updatePassenger(name.getText(), surname.getText(),email.getText(),soNo.getText(),phone.getText())){
							soNo.setEditable(true);
							name.setEditable(false);
					    	surname.setEditable(false);
					    	email.setEditable(false);
					    	phone.setEditable(false);
					    	btnUpdate.setText("Update");
						}
					}
				}else if(((JButton)e.getSource()).getText().equals("Update")){
					soNo.setEditable(false);
					name.setEditable(true);
			    	surname.setEditable(true);
			    	email.setEditable(true);
			    	phone.setEditable(true);
			    	btnUpdate.setText("Save");
				}
			}
		});
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(111, 252, 89, 29);
		btnCancel.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AppManager.cancelReservation();
				AppManager.update(AppManager.CANCEL);
				
			}
		});
		panel.add(btnCancel);
	}
	
	public void initiliaze(){
		soNo.setText("");
		name.setText("");
		phone.setText("");
		surname.setText("");
		email.setText("");
	}
	
}
