/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.frames.*;
import org.opensourcephysics.numerics.*;

/**
 * SineIntegralApp creates a table of values by integrating the function sin(x)/x.
 *
 * @author W. Christian
 * @version 1.0
 */
public class SineIntegralApp implements ODE {
  double[] state = new double[] {0.0, 0.0};

  public double[] getState() {
    return state;
  }

  public void getRate(double[] state, double[] rates) {
    rates[0] = 1;                   // independent variable
    rates[1] = integrand(state[0]); // function to integrate
  }

  public double integrand(double x) {
    if(x==0) {
      return 1;
    }
	return Math.sin(state[0])/state[0];
  }

  public static void main(String[] args) {
    TableFrame frame = new TableFrame("Sine Integral: Si(x)");
    frame.setColumnNames(0, "x");
    frame.setColumnNames(1, "Si(x)");
    ODE ode = new SineIntegralApp();
    // RK45MultiStep hides the adaptive step size from the user
    ODEAdaptiveSolver ode_method = new RK45MultiStep(ode);
    ode_method.setTolerance(1.0e-5);
    ode_method.setStepSize(1.0);
    for(int i = 0;i<10;i++) {
      frame.appendRow(new double[] {ode.getState()[0], ode.getState()[1]});
      ode_method.step();
    }
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.refreshTable();
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
