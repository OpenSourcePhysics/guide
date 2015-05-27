/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch02;
import org.opensourcephysics.display3d.core.interaction.*;
import org.opensourcephysics.display3d.simple3d.*;

/**
 *
 * @author F. Esquembre
 * @version 1.0
 */
public class InteractionApp implements InteractionListener {
  // Graphical elements
  private DrawingPanel3D panel;
  private ElementCircle ball;
  private double ballRadius = 0.1;
  private double x, y, z;
  private double min = -1.0, max = 1.0;

  /**
   * Constructor
   */
  public InteractionApp() {
    x = (max-min)*(Math.random()-0.5);
    y = (max-min)*(Math.random()-0.5);
    z = (max-min)*(Math.random()-0.5);
    panel = new DrawingPanel3D();
    panel.setPreferredMinMax(min, max, min, max, min, max);
    ball = new ElementCircle();
    ball.setXYZ(x, y, z);
    ball.setSizeXYZ(2*ballRadius, 2*ballRadius, 2*ballRadius);
    ball.getInteractionTarget(org.opensourcephysics.display3d.core.Element.TARGET_POSITION).setEnabled(true);
    ball.addInteractionListener(this);
    panel.addElement(ball);
    panel.getInteractionTarget(0).setEnabled(true);
    panel.addInteractionListener(this); // Make this class receive interaction events
    panel.repaint();
    DrawingFrame3D frame = new DrawingFrame3D();
    frame.setDrawingPanel3D(panel);
    frame.getJFrame().setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void interactionPerformed(InteractionEvent _event) {
    Object source = _event.getSource();
    Object info = _event.getInfo();
    if(source==panel) { // The panel is being interacted
      double[] point = null;
      if(info!=null) {
        point = (double[]) info;
      }
      switch(_event.getID()) {
      case InteractionEvent.MOUSE_ENTERED :
        System.out.println("Panel: mouse entered");
        break;
      case InteractionEvent.MOUSE_EXITED :
        System.out.println("Panel: mouse exited");
        break;
        // case InteractionEvent.MOUSE_MOVED    : System.out.println ("Panel: mouse moved");   break;
      case InteractionEvent.MOUSE_PRESSED :
        if(point!=null) {
          System.out.println("Panel: mouse pressed at "+point[0]+","+point[1]+","+point[2]);
        } else {
          System.out.println("Panel: mouse pressed");
        }
        break;
      case InteractionEvent.MOUSE_DRAGGED :
        if(point!=null) {
          System.out.println("Panel: mouse dragged at "+point[0]+","+point[1]+","+point[2]);
        } else {
          System.out.println("Panel: mouse dragged to rotate, pan, or zoom");
        }
        break;
      case InteractionEvent.MOUSE_RELEASED :
        if(point!=null) {
          System.out.println("Panel: mouse released at "+point[0]+","+point[1]+","+point[2]);
        } else {
          System.out.println("Panel: mouse released");
        }
        break;
      }
    } else if(source==ball) { // The ball is being interacted
      switch(_event.getID()) {
      case InteractionEvent.MOUSE_RELEASED :
        System.out.println("Ball: mouse released");
        break;
      case InteractionEvent.MOUSE_ENTERED :
        System.out.println("Ball: mouse entered");
        break;
      case InteractionEvent.MOUSE_EXITED :
        System.out.println("Ball: mouse exited");
        break;
      case InteractionEvent.MOUSE_PRESSED :
        System.out.println("Ball: mouse pressed");
        break;
      case InteractionEvent.MOUSE_DRAGGED :
        System.out.println("Ball: mouse dragged");
        break;
      }
    }
  }

  public static void main(String[] args) {
    new InteractionApp();
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
