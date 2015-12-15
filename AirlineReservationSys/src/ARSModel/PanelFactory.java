package ARSModel;

import ARSModel.Admin;
import ARSModel.Clerk;
import ARSModel.User;
import ARSView.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PanelFactory {
    private ArrayList<JPanel> clerkPanels = new ArrayList<>();
    private ArrayList<JPanel> adminPanels = new ArrayList<>();

    private JPanel reservationPanel = new JPanel();
    //private JPanel

    public PanelFactory(){
    	
        //JPanel reservationPanel = new ReservationPanel();
        //JPanel airportPanel = new AirportPanel();

        

    }
    public ArrayList<JPanel> getPanels(User user){
        if(user instanceof Clerk){
        	clerkPanels.add(new LoginPanel());
            clerkPanels.add(new MainMenu());
            clerkPanels.add(new ReservationPanel());
            clerkPanels.add(new DeleteReservation());
            clerkPanels.add(new ChangeAccountSettings(user));
            clerkPanels.add(new AvailableFlightList());
        	clerkPanels.add(new SeatPlan());
        	clerkPanels.add(new PassengerInformation());
        	clerkPanels.add(new ReservationListPanel());
            return clerkPanels;
        }
        if(user instanceof Admin){
        	adminPanels.add(new LoginPanel());
        	adminPanels.add(new AdminMainPanel());
        	adminPanels.add(new AirportPanel());
        	adminPanels.add(new UserPanel()); 
        	adminPanels.add(new ChangeAccountSettings(user));
        	adminPanels.add(new FlightMenuPanel());
        	adminPanels.add(new AddFlightPanel());
        	adminPanels.add(new DeleteFlightPanel());
        	
            return adminPanels;
        }

        return null;
    }

}
