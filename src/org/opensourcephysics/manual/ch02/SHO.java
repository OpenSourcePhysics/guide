/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch02;
import org.opensourcephysics.display.InteractiveCircle;
import org.opensourcephysics.numerics.*;

/**
 * SHO models a damped simple harmonic oscillator using rate equations.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class SHO extends InteractiveCircle implements ODE {
  // initial state values = {x, v, t}
  double[] state = new double[] {0.0, 0.0, 0.0};
  double k = 1;   // spring constant
  double b = 0.2; // damping constant
  ODESolver ode_solver = new RK4(this);

  /**
   * Gets the time.
   * @return
   */
  public double getTime() {
    return state[2];
  }

  /**
   * Gets the state array.
   *
   * @return an array containing {x, v, t}
   */
  public double[] getState() {
    // insure that the state matches the screen position
    state[0] = getX();
    return state;
  }

  /**
   * Calculates the rate array using the given state.
   *
   * Values in the rate array are overwritten.
   *
   * @param state  the state
   * @param rate   the rate
   */
  public void getRate(double[] state, double[] rate) {
    rate[0] = state[1]; // dx/dt = v
    double force = -k*state[0]-b*state[1];
    rate[1] = force; // dv/dt = force
    rate[2] = 1;     // dt/dt = 1
  }

  /**
   * Sets position of this object.
   *
   * The y position is constrained to be along the x axis;
   * @param x
   * @param y
   */
  public void setXY(double x, double y) {
    super.setXY(x, 0); // y is always zero
    state[0] = x;
  }

  /**
   * Steps the time using an ode solver.
   */
  public void stepTime() {
    ode_solver.step();
    setX(state[0]);
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
