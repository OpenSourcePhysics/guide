/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.numerics.*;

/**
 * SHOApp tests the ODESolver interface using a simple harmonic oscillator.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class SHOApp {
  public static void main(String[] args) {
    double time = 10; // solution range
    double dt = 0.1;  // ode step size
    ODE ode = new SHO();
    ODESolver ode_solver = new RK45(ode);
    ode_solver.initialize(dt);
    double[] state = ode.getState();
    while(time>0) {
      String xStr = "x = "+state[0];
      String vStr = "  v = "+state[1];
      String tStr = "  t = "+state[2];
      System.out.print(xStr+vStr+tStr+"\n");
      time -= ode_solver.step();
    }
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
