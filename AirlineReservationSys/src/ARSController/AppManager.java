package ARSController;

import java.util.ArrayList;

import ARSModel.*;
import ARSView.*;

public class AppManager {
	//public static MainFrame frame = new MainFrame();
	//public static AdminMainPanel aMP = new AdminMainPanel();
	public static void main(String[]args){
		DatabaseConnector db = DatabaseConnector.getInstance();
		/*String word= "Sanem123";
		String encrypt = db.encrypt(word);
		System.out.println("Encrypted string = " + encrypt);
		System.out.println("Decrypted string = " + db.decrypt(encrypt));
		word= "sanem556";
		encrypt = db.encrypt(word);
		System.out.println("Encrypted string = " + encrypt);
		System.out.println("Decrypted string = " + db.decrypt(encrypt));
		
		db.addUser("sanem", "elbasi", "sanem.elbasi35@gmail.com", "33222188484", "04749987474", true);
		
		frame.setPanel(aMP);*/
		
		ArrayList<Airport>  air = db.getAllAirports();
		long date = 856648800000L;
		
		ArrayList<Flight> f = db.getFlight(air.get(0), air.get(1), date );
		
		if(f != null){
			System.out.println(f.get(0));
			
		}
		
	}
}
