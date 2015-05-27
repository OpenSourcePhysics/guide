/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.numerics.*;

/**
 * Lorenz models the Lorenz differential equations using a rate equation and an ODE solver.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class Lorenz implements ODE {
  DrawingPanel3D panel = new DrawingPanel3D();
  DrawingFrame3D frame = new DrawingFrame3D(panel);
  double[] state = new double[4];
  double a = 28.0;
  double b = 2.667;
  double c = 10.0;
  ODESolver ode_solver = new RK45MultiStep(this);
  Element ball = new ElementEllipsoid();
  ElementTrail trace = new ElementTrail();

  /**
   * Lorenz constructor.
   */
  public Lorenz() {
    frame.setTitle("Lorenz Attractor");
    panel.setPreferredMinMax(-15.0, 15.0, -15.0, 15.0, 0.0, 50.0);
    panel.getVisualizationHints().setDecorationType(org.opensourcephysics.display3d.core.VisualizationHints.DECORATION_AXES);
    ball.setSizeXYZ(1, 1, 1);
    ball.getStyle().setFillColor(java.awt.Color.RED);
    panel.addElement(trace);
    panel.addElement(ball);
    ode_solver.setStepSize(0.01);
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    for(int i = 0;i<10;i++) {
      ode_solver.step();
      trace.addPoint(state[0], state[1], state[2]);
      ball.setXYZ(state[0], state[1], state[2]);
    }
    panel.setMessage("t="+Util.f3(state[3]));
  }

  /**
   * The state[] array contains:
   * x,y,z, t
   * @return  the state
   */
  public double[] getState() {
    return state;
  }

  public void initialize(double x, double y, double z) {
    state[0] = x;
    state[1] = y;
    state[2] = z;
    state[3] = 0; // time
    trace.clear();
    trace.addPoint(x, y, z);
    ball.setXYZ(x, y, z);
    frame.setVisible(true);
  }

  public void getRate(double[] state, double[] rate) {
    rate[0] = -(state[0]-state[1])*c;                 // x rate
    rate[1] = -state[1]-state[0]*state[2]+state[0]*a; // y rate
    rate[2] = (state[0]*state[1]-state[2])*b;         // z rate
    rate[3] = 1;                                      // time rate
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
