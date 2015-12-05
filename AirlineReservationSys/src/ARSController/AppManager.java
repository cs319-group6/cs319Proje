package ARSController;

import ARSModel.*;
import ARSView.*;

public class AppManager {
	public static MainFrame frame = new MainFrame();
	public static AdminMainPanel aMP = new AdminMainPanel();
	public static void main(String[]args){
		DatabaseConnector db = DatabaseConnector.getInstance();
		String word= "Sanem123";
		String encrypt = db.encrypt(word);
		System.out.println("Encrypted string = " + encrypt);
		System.out.println("Decrypted string = " + db.decrypt(encrypt));
		
		frame.setPanel(aMP);
		
		
	}
}
