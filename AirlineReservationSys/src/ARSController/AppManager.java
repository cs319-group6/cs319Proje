package ARSController;

import ARSModel.*;
import ARSView.*;

import javax.swing.*;
import java.util.Calendar;
import java.util.List;

public class AppManager {
    public static final int LOGINPROCEED = 1;
    public static final int BACK = -1;

    public static MainFrame mainFrame = new MainFrame();
    public static int status = 0;
    public static User user = null;
    public static PanelObserver observer;

    public static DatabaseConnector connector = DatabaseConnector.getInstance();

    public static User verifyUser(int id, String pass) {
        User person = connector.verifyUser(id, pass);
        if (person == null) {
            return null;
        } else {
            return person;
        }
    }

    //Todo
//	public List<Flight> getFlightSchedule(Calendar dateTime, String destination){
//	}

    public static void setDisplayPanel(JPanel panel) {
        mainFrame.setPanel(panel);
    }

    //Todo
//    public boolean deleteFlight(Flight flight){
//        connector.deleteFlight(flight);
//    }

    //Todo
//    public boolean addFlight(Flight flight){
//        connector.addFlight(flight);
//    }

//    public boolean makeReservation(Seat seat, Passenger passenger){
//
//    }

    /**The method that updates JFrame by observing panels
     *
     *
     * @param action The parameter that indicates the button pressed
     * @param args  Unknown number of arguments that panel passes
     */
    public static void update(int action,Object... args){
        switch (status){
            case 0:
                if(action == LOGINPROCEED){
                    String id = (String)args[0];
                    String pass = String.valueOf((char[]) args[1]);
                    //user = verifyUser(id,pass);
                    //todo
                    if(user != null){

                    }
                    else{
                        setDisplayPanel(new ReservationPanel());
                        //JOptionPane.showMessageDialog(mainFrame,"Wrong Id or Password","Authentication failed",JOptionPane.ERROR_MESSAGE);
                    }
                }
        }
    }

	public static void main(String[] args){
//		DatabaseConnector db = DatabaseConnector.getInstance();
//		String word= "Sanem123";
//		String encrypt = db.encrypt(word);
//		System.out.println("Encrypted string = " + encrypt);
//		System.out.println("Decrypted string = " + db.decrypt(encrypt));
//		word= "sanem556";
//		encrypt = db.encrypt(word);
//		System.out.println("Encrypted string = " + encrypt);
//		System.out.println("Decrypted string = " + db.decrypt(encrypt));
//
//		db.addUser("sanem", "elbasi", "sanem.elbasi35@gmail.com", "33222188484", "04749987474", true);

        //
        JPanel currentPanel = new LoginPanel();
        //observer = new LoginPanelObserver((LoginPanel)currentPanel);
        setDisplayPanel(currentPanel);



		

		
	}

}
