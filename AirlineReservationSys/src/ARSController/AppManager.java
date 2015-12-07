package ARSController;

import java.util.ArrayList;

import ARSModel.*;
import ARSView.*;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppManager {
    public static final int PROCEEDTOMAIN = 1;
    public static final int PROCEEDTORESERVATON = 2;
    public static final int PROCEEDTODELETERES = 3;
    public static final int PROCEEDACCSETTINGS = 4;
    public static final int LISTFLIGHT = 5;
    public static final int PROCEEDTOSEATPLAN = 6;
    public static final int PROCEEDTOGETRESERVATION = 7;
    public static final int LOGOUT = 0;
    public static final int BACK = -1;

    public static MainFrame mainFrame = new MainFrame();
    public static int status = 0;
    public static User user = null;
    public static ArrayList<JPanel> panels;
    public static PanelFactory factory = new PanelFactory();
    public static User activeUser;
    public static ArrayList<Flight> flights = new  ArrayList<>();

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
	public List<Flight> getFlight(Airport from, Airport to, long date){
        connector.getFlight(from,to,date);
	}

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
     * status indicates which panel is currently demonstrated
     * @param action The parameter that indicates the button pressed
     * @param args  Unknown number of arguments that panel passes
     */
    public static void update(int action,Object... args){
        switch (status){
            case 0:
                if(action == PROCEEDTOMAIN){
                    int id = Integer.parseInt((String)args[0]);
                    String pass = String.valueOf((char[]) args[1]);
                    //user = verifyUser(id,pass);
                    //todo
                    if(true){
                        panels = factory.getPanels(user);
                        activeUser = user;
                        setDisplayPanel(panels.get(1));
                        status = 1;
                    }
                    else{
                        JOptionPane.showMessageDialog(mainFrame,"Wrong Id or Password","Authentication failed",JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case 1:
                if(action == PROCEEDTORESERVATON){
                    setDisplayPanel(panels.get(2));
                    status = 2;
                }
                if(action == PROCEEDTODELETERES){
                    setDisplayPanel(panels.get(3));
                    status = 3;
                }
                if(action == PROCEEDACCSETTINGS){
                    setDisplayPanel(panels.get(4));
                    status = 4;
                }
                if(action == LOGOUT){
                    setDisplayPanel(panels.get(0));
                    status = 0;
                }
                break;
            case 2:
                if(action == LISTFLIGHT){
                    flights = getFlight(args[0],args[1],args[2]);
                    ((AvailableFlightList)panels.get(5)).setFlight(flights);
                    setDisplayPanel(panels.get(5));
                    status = 5;
                }
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    status = 1;
                }
                break;
            //todo : other cases
            case 3:
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    status = 1;
                }
                if(action ==PROCEEDTOGETRESERVATION ){

                }
                break;
            case 4:
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    status = 1;
                }
                if(true){

                }
                break;
            case 5:
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    status = 1;
                }

                break;
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
