package ARSView;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public MainFrame() {
		super();
	}
	
	public void setPanel(JPanel newPanel){
		getContentPane().removeAll();
		getContentPane().add(newPanel);
	}
	
}
