package ARSView;


import ARSController.AppManager;

import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;


import java.time.Month;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
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
    public static final int LISTFLIGHT = 5;



	public ReservationPanel() {
		setLayout(null);
		setBackground(Color.white);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(11, 25, 46, 14);
		add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(11, 76, 46, 14);
		add(lblTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.white);
		comboBox.setBounds(67, 25, 254, 20);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(67, 73, 254, 20);
		comboBox_1.setBackground(Color.white);

		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox(Day);
		comboBox_2.setBackground(Color.white);
		comboBox_2.setToolTipText("Day\r\n");
		comboBox_2.setBounds(67, 122, 46, 20);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox(Month);
		comboBox_3.setBackground(Color.white);
		comboBox_3.setToolTipText("Month");
		comboBox_3.setBounds(126, 122, 48, 20);
		add(comboBox_3);
		
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
                manager.update(manager.BACK);

            }
        });
		add(btnBack);

	
		JButton btnNewButton = new JButton("List Flights");
		btnNewButton.setBackground(Color.black);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                manager.update(LISTFLIGHT);
			}
		});
		btnNewButton.setBounds(181, 172, 113, 40);
		add(btnNewButton);

	}
}
