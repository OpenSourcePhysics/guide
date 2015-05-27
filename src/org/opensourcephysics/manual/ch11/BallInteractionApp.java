/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.display3d.core.interaction.*;
import org.opensourcephysics.display3d.simple3d.*;

/**
 * BallInteractionApp uses the InteractionTarget API to enable changes in position.
 *
 * @author W. Christian
 * @version 1.0
 */
public class BallInteractionApp implements InteractionListener {
  DrawingPanel3D panel = new DrawingPanel3D();
  DrawingFrame3D frame = new DrawingFrame3D(panel);
  Element ball = new ElementEllipsoid();

  public BallInteractionApp() {
    ball.addInteractionListener(this); // sends interactions to this object
    // enables interactions that change positions
    ball.getInteractionTarget(org.opensourcephysics.display3d.core.Element.TARGET_POSITION).setEnabled(true);
    panel.addElement(ball);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void interactionPerformed(InteractionEvent _event) {
    switch(_event.getID()) {
    case InteractionEvent.MOUSE_DRAGGED :
      System.out.println("Ball x="+ball.getX());
      System.out.println("Ball y="+ball.getY());
      ball.setZ(0); // always set z to zero
      break;
    default :
      break;
    }
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new BallInteractionApp();
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
