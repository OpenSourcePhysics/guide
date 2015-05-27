/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.PlotFrame;

/**
 * LissajousApp demonstrates how to create a Simulation by animating Lissajous figures using a PlotFrame.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class LissajousApp extends AbstractSimulation {
  PlotFrame frame = new PlotFrame("x", "y", "Lissajous");
  double time = 0, dt = 0.1;
  double w1, w2;
  Circle circle = new Circle(0, 0, 3);

  /**
   * Resets the simulation.
   */
  public void reset() {
    time = 0.0;
    frame.setPreferredMinMax(-1.5, 1.5, -1.5, 1.5);
    frame.setConnected(true);
    frame.setMarkerShape(0, Dataset.NO_MARKER);
    frame.addDrawable(circle);
    control.setValue("omega 1", "0.2*pi");
    control.setValue("omega 2", "0.3*pi");
    initialize();
  }

  /**
   * Initializes the simulation.
   */
  public void initialize() {
    w1 = control.getDouble("omega 1");
    w2 = control.getDouble("omega 2");
    time = 0.0;
    frame.append(0, 0, 0);
    circle.setXY(0, 0);
    frame.setMessage("theta="+decimalFormat.format(time), 1);
  }

  /**
   * Does a simulation step.
   */
  protected void doStep() {
    time += dt;
    double x = Math.sin(w1*time), y = Math.sin(w2*time);
    frame.append(0, x, y);
    circle.setXY(x, y);
    frame.setMessage("theta="+decimalFormat.format(time), 1);
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new LissajousApp());
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
