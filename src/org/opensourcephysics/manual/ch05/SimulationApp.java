/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.DisplayFrame;

/**
 * SimulationApp creates a rotating square by extending an AbstractSimulation
 * and implementing the doStep method.
 *
 * Because the frame is set to be animated, the render method is invoked automatically after
 * every animation step.
 *
 * @author W. Christian
 * @version 1.0
 */
public class SimulationApp extends AbstractSimulation {
  DrawingFrame frame = new DisplayFrame("x", "y", "Rotating Shape");
  InteractiveShape shape = InteractiveShape.createRectangle(2, 1, 2, 1);
  double theta = 0, dtheta = 0.1;

  /**
   * Constructs a ShapeAnimationApp.
   *
   */
  public SimulationApp() {
    frame.setPreferredMinMax(-5, 5, -5, 5);
    frame.setMessage("theta="+decimalFormat.format(theta), 1);
    frame.addDrawable(shape);
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    theta += dtheta;
    shape.setTheta(theta);
    frame.setMessage("theta="+decimalFormat.format(theta), 1);
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new SimulationApp());
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
