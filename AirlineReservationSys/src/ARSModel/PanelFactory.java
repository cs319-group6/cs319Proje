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
            clerkPanels.add(new ChangeAccountSettings());
            clerkPanels.add(new AvailableFlightList());
        	
            return clerkPanels;
        }
        if(user instanceof Admin){
            return adminPanels;
        }

        return null;
    }

}
