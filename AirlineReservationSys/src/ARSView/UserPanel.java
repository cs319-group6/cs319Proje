package ARSView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import ARSController.AppManager;
import javax.swing.JCheckBox;

public class UserPanel extends JPanel {

	private JTextField soNo;
	private JTextField name;
	private JTextField phone;
	private JTextField surname;
	private JTextField email;
	private JButton btnUpdate;
	private JTextField userID;
	public UserPanel() {
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
				AppManager.getUser(soNo.getText());
		    	  if(AppManager.UpUser != null){
			    	  name.setText(AppManager.UpUser.getName() );
			    	  surname.setText(AppManager.UpUser.getSurname());
			    	  email.setText(AppManager.UpUser.getEmail());
			    	  phone.setText(AppManager.UpUser.getPhoneNo());
			    	  userID.setText("" + AppManager.UpUser.getPersonID());
			    	  name.setEditable(false);
			    	  surname.setEditable(false);
			    	  email.setEditable(false);
			    	  phone.setEditable(false);
			    	  userID.setEditable(false);
			    	  btnUpdate.setText("Delete");
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
			    	  userID.setEditable(true);
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
		
		JCheckBox chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setBounds(265, 30, 128, 23);
		passengerPanel.add(chckbxAdmin);
		
		JLabel lblUserd = new JLabel("UserID");
		lblUserd.setBounds(143, 5, 61, 16);
		passengerPanel.add(lblUserd);
		
		userID = new JTextField();
		userID.setBounds(127, 32, 86, 20);
		userID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppManager.getUser(Integer.parseInt(userID.getText()));
		    	  if(AppManager.UpUser != null){
			    	  name.setText(AppManager.UpUser.getName() );
			    	  surname.setText(AppManager.UpUser.getSurname());
			    	  email.setText(AppManager.UpUser.getEmail());
			    	  phone.setText(AppManager.UpUser.getPhoneNo());
			    	  userID.setText("" + AppManager.UpUser.getPersonID());
			    	  soNo.setText("" + AppManager.UpUser.getSocialSecurityNo());
			    	  name.setEditable(false);
			    	  surname.setEditable(false);
			    	  email.setEditable(false);
			    	  phone.setEditable(false);
			    	  userID.setEditable(true);
			    	  btnUpdate.setText("Delete");
		    	  }else{
		    		  AppManager.error(AppManager.NOUSERFOUND);
		    		  name.setText("");
		    		  surname.setText("");
		    		  phone.setText("");
		    		  email.setText("");
		    		  soNo.setText("");
		    		  name.setEditable(true);
			    	  surname.setEditable(true);
			    	  email.setEditable(true);
			    	  phone.setEditable(true);
			    	  userID.setEditable(true);
			    	  btnUpdate.setText("Save");
		    	  }
				
			}
		});
		passengerPanel.add(userID);
		userID.setColumns(10);
		
		JButton btnNewButton = new JButton("<- Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AppManager.cancelReservation();
				AppManager.update(AppManager.BACK);
				
			}
		});
		btnNewButton.setBounds(10, 255, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblPassenger = new JLabel("User");
		lblPassenger.setBounds(24, 6, 89, 14);
		panel.add(lblPassenger);
		
		btnUpdate = new JButton("Save");
		btnUpdate.setBounds(331, 252, 89, 29);
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()).getText().equals("Save")){
					//if(soNo.isEditable()){
						if(!name.getText().equals("") && surname.getText().equals("") && email.getText().equals("") && soNo.getText().equals("") && phone.getText().equals("") ){
							AppManager.addUser(name.getText(), surname.getText(),email.getText(),soNo.getText(),phone.getText(), chckbxAdmin.isSelected());
							if(AppManager.UpUser != null){
								AppManager.error(AppManager.USERSAVEERROR);
							}else{
								soNo.setEditable(true);
								name.setEditable(false);
						    	surname.setEditable(false);
						    	email.setEditable(false);
						    	phone.setEditable(false);
						    	userID.setText("" + AppManager.UpUser.getPersonID());
						    	userID.setEditable(false);
						    	
						    	btnUpdate.setText("Delete");
							}
						}else{
							AppManager.error(AppManager.EMPTYFIELD);
						}
				//		AppManager.addPassenger(name.getText(), surname.getText(),email.getText(),soNo.getText(),phone.getText());
					/*}else{
						if(AppManager.deleteUser(soNo.getText())){
							soNo.setEditable(true);
							name.setEditable(false);
					    	surname.setEditable(false);
					    	email.setEditable(false);
					    	phone.setEditable(false);
					    	btnUpdate.setText("Update");
						}
					}*/
				}else if(((JButton)e.getSource()).getText().equals("Delete")){
					soNo.setEditable(false);
					name.setEditable(true);
			    	surname.setEditable(true);
			    	email.setEditable(true);
			    	phone.setEditable(true);
			    	userID.setEditable(true);
			    	btnUpdate.setText("Save");
				}
			}
		});
		panel.add(btnUpdate);
	}
	
	public void initiliaze(){
		  soNo.setText("");
		  name.setText("");
		  surname.setText("");
		  phone.setText("");
		  email.setText("");
		  userID.setText("");
		  soNo.setEditable(true);
		  name.setEditable(true);
	  	  surname.setEditable(true);
	  	  email.setEditable(true);
	  	  phone.setEditable(true);
	  	  userID.setEditable(true);
	  	  btnUpdate.setText("Save");
	}
}
