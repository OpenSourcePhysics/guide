/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.display3d.simple3d.*;

/**
 * BounceApp presents 3D visualization of bouncing ball.
 *
 * @author W. Christian
 * @version 1.0
 */
public class BounceApp extends AbstractSimulation {
  DrawingPanel3D panel = new DrawingPanel3D();
  DrawingFrame3D frame = new DrawingFrame3D(panel);
  Element ball = new ElementEllipsoid();
  Element floor = new ElementBox();
  double velocity = 0;

  public BounceApp() {
    panel.setPreferredMinMax(-5, 5, -5, 5, 0, 10);
    ball.setXYZ(0.0, 0.0, 9.0);
    ball.setSizeXYZ(2, 2, 2);
    ball.getStyle().setFillColor(java.awt.Color.YELLOW);
    // floor
    floor.setXYZ(0.0, 0.0, 0.0);
    floor.setSizeXYZ(5.0, 5.0, 1.0);
    // Add the objects to panel
    panel.addElement(ball);
    panel.addElement(floor);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Does a simulation step by moving the ball and checking for a bounce.
   *
   * Render will be called automatically by the AbstractSimulation after this method completes.
   */
  protected void doStep() {
    double z = ball.getZ(), dt = 0.05;
    z += velocity*dt;   // moves the ball
    velocity -= 9.8*dt; // acceleration changes the velocity
    if(z<=1.0&&velocity<0) {
      velocity = -velocity;
    }
    ball.setZ(z);
  }

  public static void main(String[] args) {
    (new BounceApp()).startSimulation();
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
