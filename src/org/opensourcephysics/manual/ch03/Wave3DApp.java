/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.frames.Display3DFrame;
import javax.swing.JFrame;

/**
 * Wave3DApp use 3D arrows to display an E&M wave.
 *
 * @author W. Christian and F. Esquembre
 * @version 1.0
 */
public class Wave3DApp {
  public static void main(String[] args) {
    // Data for the wave
    int n = 48; // number of arrows
    double time = 0.0, dt = 0.1, E0 = 100.0, Vy = 40.0, B0 = E0;
    double period = 5.0, omega = 2.0*Math.PI/period, k = omega/Vy;
    double[] y = new double[n];
    Display3DFrame frame = new Display3DFrame("E&M Wave");
    frame.setPreferredMinMax(-100, 100, 0, 400, -100, 100);
    frame.setDecorationType(org.opensourcephysics.display3d.core.VisualizationHints.DECORATION_AXES);
    ElementArrow[] fieldE = new ElementArrow[n];
    ElementArrow[] fieldB = new ElementArrow[n];
    for(int i = 0;i<n;i++) {
      y[i] = i*400.0/n;
      fieldE[i] = new ElementArrow();
      fieldE[i].getStyle().setFillColor(java.awt.Color.RED);
      fieldE[i].setXYZ(0, y[i], 0);
      fieldE[i].setSizeXYZ(0, 0, 0);
      frame.addElement(fieldE[i]);
      fieldB[i] = new ElementArrow();
      fieldB[i].getStyle().setFillColor(java.awt.Color.BLUE);
      fieldB[i].setXYZ(0, y[i], 0);
      fieldB[i].setSizeXYZ(0, 0, 0);
      frame.addElement(fieldB[i]);
    }
    frame.setSquareAspect(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while(true) { // animate until the program exits
      try {
        Thread.sleep(100);
      } catch(InterruptedException ex) {}
      time += dt;
      for(int i = 0;i<n;i++) {
        fieldE[i].setSizeZ(E0*Math.sin(k*(y[i]-Vy*time)));
        fieldB[i].setSizeX(B0*Math.sin(k*(y[i]-Vy*time)));
      }
      frame.render();
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
