package org.opensourcephysics.display3d.jogl;
import javax.swing.JOptionPane;
/**
 * Shows a simple message if a user executes the osp_jogl.jar archive.
 *
 * @author W. Christian
 * @version 1.0
 */
public class JarMessageApp{
   public static void main(String[] args){
      JOptionPane.showMessageDialog(null,"The osp_jogl.jar archive contains an implementation of the OSP 3D API that uses Java Bindings for Open GL (JOGL).\n"+
                                    "This Java archive does not contain stand-alone programs.",
                                    "Open Source Physics Library",JOptionPane.INFORMATION_MESSAGE);
     System.exit(0);
   }
}
