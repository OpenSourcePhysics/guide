/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.display.*;
import org.opensourcephysics.numerics.*;
import java.awt.Color;

/**
 * AdaptiveStepApp demonstrates the advantage of an adaptive step size ODESolver by integrating
 * a particle's response to short-duration force.
 *
 * @author W. Christian
 * @version 1.0
 */
public class AdaptiveStepApp {
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("time", "velocity", "Impulse Response");
    DrawingFrame frame = new DrawingFrame(panel);
    panel.setSquareAspect(false);
    panel.setPreferredMinMaxY(0.8, 3.5);
    Dataset dataset = new Dataset();
    dataset.setMarkerShape(Dataset.SQUARE);
    dataset.setMarkerColor(new Color(255, 128, 128, 128), Color.red);
    panel.addDrawable(dataset);
    ODE ode = new Impulse();
    ODEAdaptiveSolver ode_solver = new RK45(ode);
    ode_solver.initialize(0.1); // the initial step size
    ode_solver.setTolerance(1.0e-4);
    while(ode.getState()[0]<12) {
      dataset.append(ode.getState()[2], ode.getState()[1]);
      ode_solver.step();
    }
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    panel.repaint();
  }
}

// The test rate equation.
class Impulse implements ODE {
  double epsilon = 0.01;
  double[] state = new double[] {-3.0, 1.0, 0.0}; // x,v,t

  public double[] getState() {
    return state;
  }

  public void getRate(double[] state, double[] rate) {
    rate[0] = state[1];
    rate[1] = epsilon/(epsilon*epsilon+state[0]*state[0]);
    rate[2] = 1;
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
