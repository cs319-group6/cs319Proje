package ARSModel;

import ARSModel.Admin;
import ARSModel.Clerk;
import ARSModel.User;

import javax.swing.*;

/**
 * Created by burak on 06.12.2015.
 */
public class PanelFactory {

    public JPanel getTypeOfUser(User user){
        if(user == null){
            return null;
        }
        if(user instanceof Clerk){
            return clerkPanels;
        }
        if(user instanceof Admin){
            return adminPanels;
        }
    }

}
