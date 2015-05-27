/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.numerics.RK45MultiStep;
import java.awt.Color;

/**
 * A clasical model of the Helium atom.
 * @author Wolfgang Christian
 * @version 1.0
 */
public class HeliumApp extends AbstractAnimation {
  PlottingPanel plottingPanel = new PlottingPanel("X", "Y", "Helium");
  DrawingFrame drawingFrame = new DrawingFrame(plottingPanel);
  RK45MultiStep ode_method;
  Helium system;
  Trail trail_1;
  Trail trail_2;
  double[] state; // x1, vx1, y1, vy1, x2, vx2, y2, vy2, t

  /**
   * HeliumApp constructor.
   */
  public HeliumApp() {
    system = new Helium();
    ode_method = new RK45MultiStep(system);
    state = system.getState();
    plottingPanel.setPreferredMinMax(-4, 4, -4, 4);
    trail_1 = new Trail();
    trail_1.color = new Color(255, 192, 192);
    trail_2 = new Trail();
    trail_2.color = new Color(192, 155, 192);
    plottingPanel.addDrawable(trail_1);
    plottingPanel.addDrawable(trail_2);
    plottingPanel.addDrawable(system);
  }

  /**
 * Sets the default parameters.
 */
  public void resetAnimation() {
    stopAnimation();
    control.setValue("r1", new double[] {3, 0});
    control.setValue("v1", new double[] {0, 0.4});
    control.setValue("r2", new double[] {1, 0});
    control.setValue("v2", new double[] {0, -1});
    control.setValue("dt", 0.2);
    control.clearMessages();
    control.println("Custom clears old trajectories.");
    clear();
    initializeAnimation(); // initialze the calculation
  }

  /**
   * Reads parameters from the control.
   */
  public void initializeAnimation() {
    // state[]: x1, vx1, y1, vy1, x2, vx2, y2, vy2, t
    double[] r1 = (double[]) control.getObject("r1");
    double[] v1 = (double[]) control.getObject("v1");
    double[] r2 = (double[]) control.getObject("r2");
    double[] v2 = (double[]) control.getObject("v2");
    state[0] = r1[0]; // x1
    state[1] = v1[0]; // vx1
    state[2] = r1[1]; // y1
    state[3] = v1[1]; // vy1
    state[4] = r2[0]; // x2
    state[5] = v2[0]; // vx2
    state[6] = r2[1]; // y2
    state[7] = v2[1]; // vy2
    state[8] = 0;     // time
    ode_method.setStepSize(control.getDouble("dt"));
    clear();
  }

  /**
   * Stores the current values in the control.
   */
  private void storeCurrentValues() {
    control.setLockValues(true);
    // state[]: x1, vx1, y1, vy1, x2, vx2, y2, vy2, t
    control.setValue("r1", new double[] {state[0], state[2]});
    control.setValue("v1", new double[] {state[1], state[3]});
    control.setValue("r2", new double[] {state[4], state[6]});
    control.setValue("v2", new double[] {state[5], state[7]});
    control.setLockValues(false);
  }

  /**
   * Remove the trails.
   *
  * SIP projects use the custom method to perform project specific functions.
  */
  public void clear() {
    trail_1.clear();
    trail_1.addPoint(state[0], state[2]);
    trail_2.clear();
    trail_2.addPoint(state[4], state[6]);
    plottingPanel.repaint();
  }

  /**
   * Does an animation step;
   */
  protected void doStep() {
    ode_method.step();
    trail_1.addPoint(state[0], state[2]);
    trail_2.addPoint(state[4], state[6]);
    plottingPanel.render();
    storeCurrentValues();
  }

  public static void main(String[] args) {
    Animation model = new HeliumApp();
    AnimationControl control = new AnimationControl(model);
    control.addButton("clear", "Clear Trails"); // optional custom action
    model.setControl(control);
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
