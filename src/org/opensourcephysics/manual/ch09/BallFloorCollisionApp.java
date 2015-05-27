/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;

/**
 * BallFloorCollisionApp models a particle colliding with the floor using a StateEvent.
 *
 * @author W. Christian and F. Esquembre
 */
public class BallFloorCollisionApp extends AbstractSimulation {
  DisplayFrame frame = new DisplayFrame("Constrained Ball");
  BallFloorCollision ball = new BallFloorCollision();

  /**
   * Constructs the FloorCollisionApp.
   */
  public BallFloorCollisionApp() {
    frame.addDrawable(ball);
    frame.setPreferredMinMax(-10, 10, -1, 11);
  }

  /**
   * Initializes the simulation.
   */
  public void initialize() {
    ball.stopAtCollision = false;
    frame.setMessage(null, org.opensourcephysics.display.DrawingPanel.TOP_RIGHT);
  }

  /**
   * Resets the simulation.
   */
  public void reset() {
    control.setValue("y coef. of restitution", 0.8);
    initialize();
  }

  /**
   * Does a simulation step.
   */
  public void doStep() {
    ball.doStep();
    if(ball.stopAtCollision==true) { // stops the simulation thread at the collision
      control.calculationDone("collision t="+decimalFormat.format(ball.state[2]));
      frame.setMessage("collision", org.opensourcephysics.display.DrawingPanel.TOP_RIGHT);
    }
    frame.setMessage("t="+decimalFormat.format(ball.state[2]));
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new BallFloorCollisionApp(), args);
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
