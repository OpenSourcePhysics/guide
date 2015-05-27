/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.display3d.core.interaction.*;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.frames.*;

/**
 * Interaction3DApp demonstrates how to add and handle actions in an Display3DFrame.
 *
 * @author Wolfgang Christian
 * @author Francisco Esquembre
 * @version 1.0  revised 06/05/2005
 */
public class Interaction3DApp implements InteractionListener {
  Element particle = new ElementCircle();
  ElementArrow arrow = new ElementArrow();

  Interaction3DApp() {
    Display3DFrame frame = new Display3DFrame("3D Interactions");
    frame.setPreferredMinMax(-2.5, 2.5, -2.5, 2.5, -2.5, 2.5);
    particle.setSizeXYZ(1, 1, 1);
    particle.getInteractionTarget(org.opensourcephysics.display3d.core.Element.TARGET_POSITION).setEnabled(true); // enables interactions that change positions
    particle.addInteractionListener(this); // accepts interactions from the particle
    frame.addElement(particle);                                       // adds the particle to the panel
    arrow.getInteractionTarget(org.opensourcephysics.display3d.core.Element.TARGET_SIZE).setEnabled(true); // enables interactions that change the size
    arrow.addInteractionListener(this); // accepts interactions from the arrow
    frame.addElement(arrow);            // adds the arrow to the panel
    frame.enableInteraction(true);      // enables interactions with the 3D Frame
    frame.addInteractionListener(this); // accepts interactions from the frame's DrawingPanel3D
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Handles the interactions sent to this class.
   * @param _evt InteractionEvent
   */
  public void interactionPerformed(InteractionEvent evt) {
    Object source = evt.getSource();
    if(evt.getID()==InteractionEvent.MOUSE_PRESSED) { // check for a particular mouse action
      System.out.println("Mouse Pressed");
    }
    if(source==particle) { // check for a particular element
      System.out.println("A particle has been hit");
    }
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  static public void main(String args[]) {
    new Interaction3DApp();
  }
}

/*
 * Open Source Physics software is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License (GPL) as
 * published by the Free Software Foundation; either version 2 of the License,
 * or(at your option) any later version.

 * Code that uses any portion of the code in the org.opensourcephysics package
 * or any subpackage (subdirectory) of this package must must also be be released
 * under the GNU GPL license.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston MA 02111-1307 USA
 * or view the license online at http://www.gnu.org/copyleft/gpl.html
 *
 * For additional information and documentation on Open Source Physics,
 * please see <http://www.opensourcephysics.org/>.
 *
 * Copyright (c) 2007  The Open Source Physics project
 *                     http://www.opensourcephysics.org
 */
