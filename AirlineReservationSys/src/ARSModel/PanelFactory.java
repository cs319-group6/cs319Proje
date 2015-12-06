package ARSModel;

import ARSModel.Admin;
import ARSModel.Clerk;
import ARSModel.User;
import ARSView.AirportPanel;
import ARSView.ReservationPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by burak on 06.12.2015.
 */
public class PanelFactory {
    private JPanel clerkPanels = new JPanel(new CardLayout());
    private JPanel adminPanels = new JPanel(new CardLayout());

    private JPanel reservationPanel = new JPanel();
    //private JPanel

    public PanelFactory(){
        JPanel reservationPanel = new ReservationPanel();
        JPanel airportPanel = new AirportPanel();

        clerkPanels.add(reservationPanel,"respanel");
        clerkPanels.add(airportPanel,"airpanel");
    }
    public JPanel getPanels(User user){
        if(user instanceof Clerk){
            return clerkPanels;
        }
        if(user instanceof Admin){
            return adminPanels;
        }

        return null;
    }

}
