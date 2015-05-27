/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.numerics.ODE;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Title:        Helium
 * Description:  Two interacting electrons in an inverse square law force field.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class Helium implements Drawable, ODE {
  int pixRadius = 6;
  double[] state = new double[9]; // x1, vx1, y1, vy1, x2, vx2, y2, vy2, t

  /**
   * The Helium constructor.
   */
  public Helium() {}

  /**
   * Get the state of the two particles.
   *
   * The state[] array contains: x1, vx1, y1, vy1, x2, vx2, y2, vy2, t
   *
   * @return  the state
   */
  public double[] getState() {
    return state;
  }

  /**
   * Gets the rate for the Helium model.
   *
   * @param state double[]
   * @param rate double[]
   */
  public void getRate(double[] state, double[] rate) {
    // state[]: x1, vx1, y1, vy1, x2, vx2, y2, vy2, t
    double deltaX = (state[4]-state[0]);         // x12 separation
    double deltaY = (state[6]-state[2]);         // y12 separation
    double dr_2 = (deltaX*deltaX+deltaY*deltaY); // r12 squared
    double dr_3 = Math.sqrt(dr_2)*dr_2;          // r12 cubed
    rate[0] = state[1]; // x1 rate
    rate[2] = state[3]; // y1 rate
    double r_2 = state[0]*state[0]+state[2]*state[2]; // r1 squared
    double r_3 = r_2*Math.sqrt(r_2);                  // r1 cubed
    rate[1] = -2*state[0]/r_3-deltaX/dr_3;     // vx1 rate
    rate[3] = -2*state[2]/r_3-deltaY/dr_3;     // vy1 rate
    rate[4] = state[5];                        // x2 rate
    rate[6] = state[7];                        // y2 rate
    r_2 = state[4]*state[4]+state[6]*state[6]; // r2 squared
    r_3 = r_2*Math.sqrt(r_2);                  // r2 cubed
    rate[5] = -2*state[4]/r_3+deltaX/dr_3;     // vx2 rate
    rate[7] = -2*state[6]/r_3+deltaY/dr_3;     // vy2 rate
    rate[8] = 1;                               // time rate
  }

  /**
   * Draw the two electrons.
   *
   * @param world  the panel that will draw the two bodies.
   * @param g the graphics context
   */
  public void draw(DrawingPanel panel, Graphics g) {
    int xpix = panel.xToPix(state[0])-pixRadius;
    int ypix = panel.yToPix(state[2])-pixRadius;
    g.setColor(Color.red);
    g.fillOval(xpix, ypix, 2*pixRadius, 2*pixRadius); // draw the particle onto the screen
    xpix = panel.xToPix(state[4])-pixRadius;
    ypix = panel.yToPix(state[6])-pixRadius;
    g.setColor(Color.green);
    g.fillOval(xpix, ypix, 2*pixRadius, 2*pixRadius); // draw the particle onto the screen
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
