/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch07;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;

/**
 * RenderApp tests the frame rate using the render method.  An AbstractSimulation
 * renders the drawing within the animation thread by after invoking the doStep method.
 *
 * @author W. Christian
 * @version 1.0
 */
public class RenderApp extends AbstractSimulation {
  DisplayFrame frame = new DisplayFrame("Direct Drawing");
  Spiral spiral = new Spiral();
  double dtheta = 0.1;
  int counter = 0;
  long startTime = 0;

  /**
   * Constructs a RenderApp.
   */
  public RenderApp() {
    frame.setPreferredMinMax(-5, 5, -5, 5);
    frame.addDrawable(spiral);
    frame.setVisible(true);
  }

  public void startRunning() {
    delayTime = control.getInt("delay time (ms)");
    counter = 0;
    startTime = System.currentTimeMillis();
  }

  public void stopRunning() {
    float rate = counter/(float) (System.currentTimeMillis()-startTime);
    control.println("frames per second="+1000*rate);
    control.println("ms per frame="+(1.0/rate));
  }

  /**
   * Resets the program by setting the delay between screen redrawing.
   */
  public void reset() {
    control.setAdjustableValue("delay time (ms)", 100);
  }

  /**
   * Does a simulation step.
   */
  protected void doStep() {
    spiral.theta += dtheta;
    counter++;
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new RenderApp());
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
