/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.controls.*;

/**
 * LorenzApp models the Lorenz attractor by extending AbstractSimulation
 * and implementing the doStep method.
 *
 * @author W. Christian
 * @version 1.0
 */
public class LorenzApp extends AbstractSimulation {
  Lorenz lorenz = new Lorenz();

  /**
   * Initializes the simulation.
   */
  public void initialize() {
    double x = control.getDouble("initial x");
    double y = control.getDouble("initial y");
    double z = control.getDouble("initial z");
    double dt = control.getDouble("dt");
    lorenz.initialize(x, y, z);
    lorenz.ode_solver.initialize(dt);
  }

  /**
   * Resets the simulation.
   */
  public void reset() {
    control.setValue("initial x", 2);
    control.setValue("initial y", 5);
    control.setValue("initial z", 20);
    control.setValue("dt", 0.01);
    initialize();
  }

  /**
   * Does a simulation step.
   *
   * Render will be called automatically by the AbstractSimulation after this method completes.
   */
  protected void doStep() {
    lorenz.doStep();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new LorenzApp());
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
