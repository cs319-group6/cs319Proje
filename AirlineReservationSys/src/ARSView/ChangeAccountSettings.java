package ARSView;

import ARSController.AppManager;
import ARSModel.User;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ChangeAccountSettings extends JPanel implements Observer {

	AppManager manage;

	private JPasswordField passwordField;
	private JPasswordField newPassField;
	private JPasswordField retypeField;
	private JTextField changeEmailField;
	private JLabel empNameLabel, personID, personEmail, Personphone;
	private JTextField phone;
	public ChangeAccountSettings(User user) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		JButton back = new JButton("<- Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage.update(manage.BACK);

			}
		});
		back.setBounds(10, 266, 89, 23);
		panel.add(back);
		
		JButton save = new JButton("Save Changes");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPassword = null; 
				String newEmail = null;
				String newPhone = null;
				
				if(AppManager.user.getPassword().equals(new String(passwordField.getPassword()))){
					if(newPassField.getPassword().length>0 && retypeField.getPassword().length>0 ){
						if(new String(newPassField.getPassword()).equals(new String(retypeField.getPassword())) ){
							newPassword = new String(retypeField.getPassword());
						}else{
							AppManager.error(AppManager.PASSWORDMISMATCH);
						}
					}else if(changeEmailField.getText().equals("") && phone.getText().equals("")){
						AppManager.error(AppManager.EMPTYFIELD);
						return;
					}
					if(!changeEmailField.getText().equals("")){
						newEmail = changeEmailField.getText();
					}
					if(!phone.getText().equals("")){
						newPhone = phone.getText();
					}
					AppManager.updateUserInfo(newPassword, newEmail, newPhone);
					
				}else{
					//System.out.println("USer password : " + AppManager.user.getPassword() + "  Entered pass: " + new String(passwordField.getPassword()));
					AppManager.error(AppManager.OLDPASSWORDMISMATCH);
					return;
				}
				
				
				//TODO appmamger change password change email
			}
		});
		save.setBounds(327, 266, 113, 23);
		panel.add(save);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBounds(10, 15, 430, 96);
		panel.add(topPanel);
		topPanel.setLayout(null);
		
		JPanel topInPanel = new JPanel();
		topInPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		topInPanel.setBounds(10, 7, 414, 78);
		topPanel.add(topInPanel);
		topInPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(10, 11, 46, 14);
		topInPanel.add(nameLabel);
		
		empNameLabel = new JLabel(user.getName() + " " + user.getSurname());
		empNameLabel.setBounds(66, 11, 91, 14);
		topInPanel.add(empNameLabel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 36, 46, 14);
		topInPanel.add(lblId);
		
		personID = new JLabel(String.valueOf(user.getPersonID()));
		personID.setBounds(66, 36, 91, 14);
		topInPanel.add(personID);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 61, 46, 14);
		topInPanel.add(lblEmail);
		
		personEmail = new JLabel(user.getEmail());
		personEmail.setBounds(66, 61, 147, 14);
		topInPanel.add(personEmail);
		
		JLabel lblPhone_1 = new JLabel("Phone:");
		lblPhone_1.setBounds(208, 10, 61, 16);
		topInPanel.add(lblPhone_1);
		
		Personphone = new JLabel(AppManager.user.getPhoneNo());
		Personphone.setBounds(281, 10, 61, 16);
		topInPanel.add(Personphone);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		bottomPanel.setBounds(10, 129, 430, 137);
		panel.add(bottomPanel);
		bottomPanel.setLayout(null);
		
		JLabel passwordLabel = new JLabel("Old Password:");
		passwordLabel.setBounds(10, 11, 205, 14);
		bottomPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 8, 133, 20);
		bottomPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setBounds(10, 39, 205, 14);
		bottomPanel.add(newPasswordLabel);
		
		newPassField = new JPasswordField();
		newPassField.setColumns(10);
		newPassField.setBounds(201, 36, 133, 20);
		bottomPanel.add(newPassField);
		
		JLabel retypeLabel = new JLabel("Re-type:");
		retypeLabel.setBounds(10, 67, 205, 14);
		bottomPanel.add(retypeLabel);
		
		retypeField = new JPasswordField();
		retypeField.setColumns(10);
		retypeField.setBounds(201, 64, 133, 20);
		bottomPanel.add(retypeField);
		
		JLabel changeEmailLabel = new JLabel("Change Email:");
		changeEmailLabel.setBounds(10, 90, 205, 14);
		bottomPanel.add(changeEmailLabel);
		
		changeEmailField = new JTextField();
		changeEmailField.setColumns(10);
		changeEmailField.setBounds(201, 90, 133, 20);
		bottomPanel.add(changeEmailField);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(10, 115, 173, 16);
		bottomPanel.add(lblPhone);
		
		phone = new JTextField();
		phone.setBounds(200, 115, 134, 20);
		bottomPanel.add(phone);
		phone.setColumns(10);
		
		JLabel employee = new JLabel("Account");
		employee.setBounds(21, 0, 78, 14);
		panel.add(employee);
		
		JLabel changes = new JLabel("Changes");
		changes.setBounds(20, 112, 79, 14);
		panel.add(changes);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		personEmail.setText(((User)o).getEmail());
		Personphone.setText(((User)o).getPhoneNo());
		
		
		
	}
}
