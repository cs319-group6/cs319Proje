package ARSController;

import java.util.ArrayList;

import ARSModel.*;
import ARSView.*;

import javax.swing.*;

public class AppManager {
    public static final int PROCEEDTOMAIN = 1;
    public static final int PROCEEDTORESERVATON = 2;
    public static final int PROCEEDTODELETERES = 3;
    public static final int PROCEEDACCSETTINGS = 4;
    public static final int LISTFLIGHT = 5;
    public static final int PROCEEDTOSEATPLAN = 6;
    public static final int PROCEEDTORESERVATIONLIST = 7;
    public static final int LOGOUT = 0;
    public static final int BACK = -1;
    public static final int PROCEEDTOPASSENGERINFO = 8;
    public static final int PROCEEDTOAIRPORT = 9;
    public static final int PROCEEDTOUSER = 10;
    public static final int PROCEEDTOFLIGHTMENU = 11;
    public static final int PROCEEDTOADDFLIGHT = 12;
    public static final int PROCEEDTODELETEFLIGHT = 13;
    public static final int CANCEL = -2;
    //ERRORS
    public static final int ERROR = -1;
    public static final int PASSWORDMISMATCH = 1;
    public static final int EMPTYFIELD = 2;
    public static final int USERIDPASSWORDMISMATCH= 3;
    public static final int USERINFOUPDATE= 4;
    public static final int OLDPASSWORDMISMATCH= 5;
    public static final int UPDATESUCCESS= 6;
    public static final int NOPASSENGERFOUND = 7;
    public static final int NOFLIGHTSELECTED = 8;
    public static final int PASSENGERSAVEERROR = 9;
    public static final int NOSEATSELECTED = 10;
    public static final int RESERVATIONERROR = 11;
    public static final int NORESERVATIONSELECTED = 12;
    public static final int USERSAVEERROR = 13;
    public static final int NOUSERFOUND  = 14;
    public static final int WRONGDATE  = 15;
    public static final int NOFLIGHTAVAILABLE = 16;

    public static MainFrame mainFrame = new MainFrame();
    public static int status = 0;
    public static User user = null;
    public static ArrayList<JPanel> panels;
    public static PanelFactory factory = new PanelFactory();
    public static User activeUser;
    public static ArrayList<Flight> flights = null;
    public static ArrayList<Reservation> DelReservations = new ArrayList<Reservation>();
    public static ArrayList<Reservation> reservations = null;
    public static Airport ResDeparture = null;
    public static Airport ResDestination = null;
    public static long ResDate = 0;
    public static Flight ResFlight = null;
    public static Passenger ResPassenger = null;
    public static ArrayList<Seat> ResSeats =  new ArrayList<Seat>();
    public static ArrayList<Reservation> ResRes = new ArrayList<Reservation>();
    public static Airport airport = null;
    public static User UpUser = null;
    //public static Passenger ResDelPass = null;
    
    public static DatabaseConnector connector = DatabaseConnector.getInstance();

    public static User verifyUser(int id, String pass) {
        User person = connector.verifyUser(id, pass);
        if (person == null) {
            return null;
        } else {
            return person;
        }
    }
    
    public static void reset(){
    	DelReservations = new ArrayList<Reservation>();
    	reservations = null;
    	flights = null;
        ResDeparture = null;
        ResDestination = null;
        ResDate = 0;
        ResFlight = null;
        ResPassenger = null;
        ResSeats =  new ArrayList<Seat>();
        ResRes = new ArrayList<Reservation>();
        airport = null;
        UpUser = null;
        
    }

    //Todo
	public static ArrayList<Flight> getFlightsAtDate(Airport from, Airport to, long date){
        return connector.getFlightAtDate(from,to,new ARSDate(date));
	}

    public static void setDisplayPanel(JPanel panel) {
        mainFrame.setPanel(panel);
    }

    public static void getPassenger(String soNo){
    	ResPassenger = connector.getPassenger(soNo);
    }
    public static void getAirport(String name){
    	airport = connector.getAirport(name);
    }
    
    public static boolean reserveSeat(int i, int j){
    	if(ResRes.add(connector.addReservation(ResFlight, (Clerk)user, ResFlight.getSeat(i,j)))){
	    	ResSeats.add(ResFlight.getSeat(i,j));
	    	ResFlight.setAvailable(i,j,false);
	    	return true;
    	}
    	return false;
    }
    
    public static ArrayList<Reservation> getReservations(){
    	if(ResPassenger != null){
    		//System.out.println("aaa" +ResPassenger);
    		return connector.getReservations(ResPassenger);
    	}
    	else
    		return null;
    }
    
    public static void getUser(String soNo){
    	UpUser =connector.getUserSoNo(soNo);
    }
    public static void getUser(int id){
    	UpUser =connector.getUserByID(id);
    }
    public static void addUser(String name, String surname, String soNo, String email, String phone, boolean type){
    	UpUser = connector.addUser(name, surname, email, soNo, phone, type);
    }
    public static void removeReservation(int i, int j){
    	ResSeats.remove(ResFlight.getSeat(i,j));
    	ResFlight.setAvailable(i,j,true);
    	ResRes.remove(connector.getReservation(ResFlight,(Clerk)user ,ResFlight.getSeat(i, j)));
    }
    
    public static void cancelReservation(){
    	for(int i=0; i < ResRes.size();i ++){
    		ResFlight.cancelReservation(ResSeats);
    		connector.deleteReservation(ResRes.get(i).getID());
    		//ResFlight.generateDefaultSeats();
  
    	}
    	ResSeats =  new ArrayList<Seat>();
    	ResRes =  new ArrayList<Reservation>();
    }
    public static void updateUserInfo(String newPass, String newEmail, String newPhone){
    	String password, email, phone;
    	if(newPass != null)
    		password = newPass;
    	else
    		password = user.getPassword();
    	if(newEmail != null)
    		email = newEmail;
    	else
    		email  =user.getEmail();
    	if(newPhone != null)
    		phone =newPhone;
    	else
    		phone = user.getPhoneNo();
    	if(connector.updateUser(user.getPersonID(),  password, email, phone)){
    		user.setPassword(password);
    		user.setEmail(email);
    		user.setPhoneNo(phone);
    		error(UPDATESUCCESS);
    	}else 
    		error(USERINFOUPDATE);
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setPhoneNo(phone);
    	
    }
    
    
    //Todo
//    public boolean deleteFlight(Flight flight){
//        connector.deleteFlight(flight);
//    }

    //Todo
    public static boolean addFlight(Airport depart, Airport dest, ARSDate dateTime, int duration){
        
    	if(connector.addFlight(depart, dest, 1,dateTime,duration)!= null)
    		return true;
    	else 
    		return false;
    }

    
    public static boolean updatePassenger(String name, String surname, String email, String SoNo, String phone){
    	return connector.updatePassenger(name, surname, email, SoNo, phone);
    	
    }
    
    public static boolean addPassenger(String name, String surname, String email, String SoNo, String phone){
    	ResPassenger = connector.addPassenger(name, surname, email, SoNo, phone);
    	if(ResPassenger != null){
    		return true;
    	}else
    		return false;
    }
   
    public static boolean makeReservation(){
    	
    	if(ResPassenger == null){
    		error(NOPASSENGERFOUND);
    	}else if(ResRes.size() < 1){
    		error(NOSEATSELECTED);
    	}
    	if(ResPassenger != null && ResRes.size() > 0){
    		boolean result = true;
    		for(int i = 0; i <ResRes.size(); i++){
    			if(connector.makeReservation(ResRes.get(i), ResPassenger) == null){
    				result = false;
    			}
    		}
    		return result;
    	}else 
    		return false;
    }
    
    public static boolean deleteReservations(){
    	
    	for(int i = 0; i < DelReservations.size(); i++){
    		if(!connector.deleteReservation(DelReservations.get(i).getID()))
    			return false;
    	}
    	return true; 
    }
    
    public static ArrayList<Airport> getAllAirports(){
    	return connector.getAllAirports();
    }
    
    public static ArrayList<Airport> getDepartAirportsTo(Airport airport){
    	return connector.getDepartureAirportsArrivesTo(airport);
    }
    
    public static ArrayList<Airport> getDestAirportsFrom(Airport airport){
    	return connector.getDestinationAirportsDepartsFrom(airport);
    }
    
    public static boolean addAirport(String name, String city, String country){
    	return (connector.addAirport(country, city, name) != null);
    }
    
    public static boolean deleteAirport(String name){
    	return connector.deleteAirport(name);
    }
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
                    user = verifyUser(id,pass);
                    //todo
                    if(user != null){
                        panels = factory.getPanels(user);
                        activeUser = user;
                        user.addObserver((ChangeAccountSettings)panels.get(4));
                        setDisplayPanel(panels.get(1));
                        status = 1;
                    }
                    else{
                        error(USERIDPASSWORDMISMATCH);
                    }
                }
                break;
            case 1:
            	if(action == PROCEEDTOMAIN){
            		setDisplayPanel(panels.get(1));
            		reset();
                    status = 1;
            	}
                if(action == PROCEEDTORESERVATON){
                	((ReservationPanel)panels.get(2)).initialize();
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
                if(action == PROCEEDTOAIRPORT){
                	setDisplayPanel(panels.get(2));
                	status = 2;
                }
                if(action == PROCEEDTOUSER){
                	((UserPanel)panels.get(3)).initiliaze();
                	setDisplayPanel(panels.get(3));
                	status = 2;
                }
                if(action == PROCEEDTOFLIGHTMENU){
                	setDisplayPanel(panels.get(5));
                	status = 2;
                }
                break;
            case 2:
                if(action == LISTFLIGHT){
                	if(ResDeparture != null &&ResDestination != null &&ResDate != 0){
	                    flights = getFlightsAtDate(ResDeparture,ResDestination,ResDate);
	                    //System.out.println(flights);
	                    if(flights.size() < 1){
	                    	error(NOFLIGHTAVAILABLE);
	                    }else{
		                    panels.set(5, new AvailableFlightList());
		                    ((AvailableFlightList)panels.get(5)).initiliaze();
		                    setDisplayPanel(panels.get(5));
		                    status = 5;
	                    }
	                    //mainFrame.pack();
                	}else{
                		error(EMPTYFIELD);
                	}
                }
                if(action == PROCEEDTOADDFLIGHT){
                	setDisplayPanel(panels.get(6));
                	status = 2;
                }
                if(action == PROCEEDTODELETEFLIGHT){
                	setDisplayPanel(panels.get(7));
                	status = 2;
                }
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    reset();
                    status = 1;
                }
                break;
            //todo : other cases
            case 3:
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    reset();
                    status = 1;
                }
                if(action ==PROCEEDTORESERVATIONLIST ){
                	panels.set(8, new ReservationListPanel());
                	setDisplayPanel(panels.get(8));
                	status =  3;
                }
                break;
            case 4:
                if(action == BACK){
                    setDisplayPanel(panels.get(1));
                    reset();
                    status = 1;
                }
                if(true){

                }
                break;
            case 5:
            	if(action == PROCEEDTOSEATPLAN){
            		//TODO
            		panels.set(6, new SeatPlan());
            		setDisplayPanel(panels.get(6));
            		status = 6;
            	}
                if(action == BACK){
                	((ReservationPanel)panels.get(2)).initialize();
                    setDisplayPanel(panels.get(2));
                    status = 2;
                }
                if(action == CANCEL){
                	setDisplayPanel(panels.get(1));
                    reset();
                    status = 1;
                }
                if(action == PROCEEDTOMAIN){
                	setDisplayPanel(panels.get(1));
                    reset();
                    status = 1;
                }

                break;
            case 6: //TODO
            	if(action == PROCEEDTOPASSENGERINFO){
            		((PassengerInformation)panels.get(7)).initiliaze();
            		ResPassenger = null;
            		setDisplayPanel(panels.get(7));
            		status = 5;
            	}
            	if(action == BACK){
            		if(ResDeparture != null &&ResDestination != null &&ResDate != 0){
	                    flights = getFlightsAtDate(ResDeparture,ResDestination,ResDate);
	                    //System.out.println(flights);
	                    
	                    panels.set(5, new AvailableFlightList());
	                    setDisplayPanel(panels.get(5));
	                    status = 5;
	                    //mainFrame.pack();
                	}else{
                		error(EMPTYFIELD);
                	}
            	}
            		
            	break;
        }

    }

    public static void error(int i){
    	switch(i){
    	case PASSWORDMISMATCH:
    		JOptionPane.showMessageDialog(mainFrame, "Password Mismatch","Password Mismatch",JOptionPane.ERROR_MESSAGE);
    		break;
    	case USERIDPASSWORDMISMATCH:
    		JOptionPane.showMessageDialog(mainFrame, "Wrong Id or Password","Authentication failed",JOptionPane.ERROR_MESSAGE);
    		break;
    	case EMPTYFIELD:
    		JOptionPane.showMessageDialog(mainFrame, "Empty Field Error ", "Empty Field error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case USERINFOUPDATE: 
    		JOptionPane.showMessageDialog(mainFrame, "Update error", "Update Failed ",JOptionPane.ERROR_MESSAGE);
    		break;
    	case OLDPASSWORDMISMATCH: 
    		JOptionPane.showMessageDialog(mainFrame, "Old Password Mismatch","Old password error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case UPDATESUCCESS: 
    		JOptionPane.showMessageDialog(mainFrame, "User Information Updated Successfully", "Success",JOptionPane.INFORMATION_MESSAGE);
    		break;
    	case NOPASSENGERFOUND: 
    		JOptionPane.showMessageDialog(mainFrame, "No Passenger Found", "No Passenger Found",JOptionPane.ERROR_MESSAGE);
    		break;
    	case NOFLIGHTSELECTED: 
    		JOptionPane.showMessageDialog(mainFrame, "No Flight Selected", "No Flight Selected",JOptionPane.ERROR_MESSAGE);
    		break;
    	case PASSENGERSAVEERROR: 
    		JOptionPane.showMessageDialog(mainFrame, "An Error Occured While Saving Passenger", "Passenger Save Error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case NOSEATSELECTED: 
    		JOptionPane.showMessageDialog(mainFrame, "No Seat Selected", "No Seat Selected",JOptionPane.ERROR_MESSAGE);
    		break;
    	case RESERVATIONERROR: 
    		JOptionPane.showMessageDialog(mainFrame, "An Error Occured While Saving Reservation", "Reservation Save Error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case ERROR: 
    		JOptionPane.showMessageDialog(mainFrame, "An Unexpected Error Occured ", "Error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case NORESERVATIONSELECTED: 
    		JOptionPane.showMessageDialog(mainFrame, "No Reservation Selected", "No Reservation Selected",JOptionPane.ERROR_MESSAGE);
    		break;
    	case USERSAVEERROR: 
    		JOptionPane.showMessageDialog(mainFrame, "An Error Occured While Saving User", "User Save Error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case NOUSERFOUND: 
    		JOptionPane.showMessageDialog(mainFrame, "No User Found", "No User Found",JOptionPane.ERROR_MESSAGE);
    		break;
    	case WRONGDATE: 
    		JOptionPane.showMessageDialog(mainFrame, "Wrong Date Error", "Wrong Date Error",JOptionPane.ERROR_MESSAGE);
    		break;
    	case NOFLIGHTAVAILABLE: 
    		JOptionPane.showMessageDialog(mainFrame, "There is no Flight Available", "No Flight Available Error",JOptionPane.ERROR_MESSAGE);
    		break;
    		
    	}
    }
    
    public static void success(){
    	JOptionPane.showMessageDialog(mainFrame, "Success", "Success",JOptionPane.INFORMATION_MESSAGE);
    }
    
    
	public static void main(String[] args){
		
        JPanel currentPanel = new LoginPanel();
        
        setDisplayPanel(currentPanel);

		
	}

}
