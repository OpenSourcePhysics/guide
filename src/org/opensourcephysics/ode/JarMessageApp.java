package org.opensourcephysics.ode;
import javax.swing.JOptionPane;
/**
 * Shows a simple message if a user executes the osp_ode.jar archive.
 *
 * @author W. Christian
 * @version 1.0
 */
public class JarMessageApp{
   public static void main(String[] args){
      JOptionPane.showMessageDialog(null,"The osp_ode.jar archive contains high-order differential equation solvers.\n"+
                                    "This Java archive does not contain stand-alone programs.",
                                    "Open Source Physics Library",JOptionPane.INFORMATION_MESSAGE);
     System.exit(0);
   }
}
