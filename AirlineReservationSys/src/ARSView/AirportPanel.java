package ARSView;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ARSController.AppManager;

public class AirportPanel extends JPanel {
	private JTextField name;
	private JTextField city;
	private JTextField country;
	private JButton btnSave;
	public AirportPanel() {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(23, 248, 95, 29);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AppManager.update(AppManager.BACK);
			}
		});
		add(btnBack);
		
		JLabel lblAirport = new JLabel("Airport");
		lblAirport.setBounds(25, 23, 61, 16);
		add(lblAirport);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 39, 408, 207);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 26, 61, 16);
		panel.add(lblName);
		
		name = new JTextField();
		name.setBounds(79, 20, 134, 28);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!name.getText().equals("")){
					AppManager.getAirport(name.getText());
					if(AppManager.airport !=  null){
						name.setText(AppManager.airport.getName() );
						city.setText(AppManager.airport.getCity());
						country.setText(AppManager.airport.getCountry());
						name.setEditable(true);
						city.setEditable(false);
						country.setEditable(false);
						btnSave.setText("Delete");
					}
					else{
						//name.setText("" );
						city.setText("");
						country.setText("");
						name.setEditable(true);
						city.setEditable(true);
						country.setEditable(true);
						btnSave.setText("Save");
					}
				}
				else{
					//name.setText("" );
					city.setText("");
					country.setText("");
					name.setEditable(true);
					city.setEditable(true);
					country.setEditable(true);
					btnSave.setText("Save");
				}
			}
		});
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(6, 83, 61, 16);
		panel.add(lblCity);
		
		city = new JTextField();
		city.setBounds(79, 77, 134, 28);
		panel.add(city);
		city.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(6, 134, 74, 16);
		panel.add(lblCountry);
		
		country = new JTextField();
		country.setBounds(79, 128, 134, 28);
		panel.add(country);
		country.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()).getText().equals("Save")){
					if(!name.getText().equals("") && !city.getText().equals("") && !country.getText().equals("")){
						if(AppManager.addAirport(name.getText(),city.getText(),country.getText())){
							AppManager.success();
							name.setEditable(true);
							city.setEditable(false);
							country.setEditable(false);
							btnSave.setText("Delete");
						}else{
							AppManager.error(AppManager.ERROR);
						}
					}else{
						AppManager.error(AppManager.EMPTYFIELD);
					}
				}else{
					if(AppManager.deleteAirport(name.getText())){
						AppManager.success();
						name.setText("" );
						city.setText("");
						country.setText("");
						name.setEditable(true);
						city.setEditable(true);
						country.setEditable(true);
						btnSave.setText("Save");
					}else{
						AppManager.error(AppManager.ERROR);
					}
				}
			}
		});
		btnSave.setBounds(291, 248, 117, 29);
		add(btnSave);
	}
}
