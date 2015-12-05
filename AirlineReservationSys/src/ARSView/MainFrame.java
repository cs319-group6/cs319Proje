package ARSView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	public JPanel currentPanel = null;
	public MainFrame() {
		super();
		super.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	
		//this.pack();
		this.setVisible(true);
		this.setSize(600, 400);
	}
	
	public void setPanel(JPanel newPanel){
		if(currentPanel != null){
			currentPanel.setVisible(false);
			getContentPane().remove(currentPanel);
			
		}
		currentPanel = newPanel;
		getContentPane().add(newPanel);
		pack();
		newPanel.setVisible(true);
		this.setSize(600, 400);
	}
	
}
