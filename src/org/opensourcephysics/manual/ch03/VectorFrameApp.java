/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.frames.Vector2DFrame;
import javax.swing.JFrame;

/**
 * VectorFrameApp plots a 1/(r*r) vector field using a Vector2DFrame.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class VectorFrameApp {

  /**
   * The main method that starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    Vector2DFrame frame = new Vector2DFrame("x", "y", "Vector Field");
    double a = 2; // world units for vector field
    int nx = 15, ny = 15;
    // generate the data
    double[][][] data = new double[2][nx][ny]; // vector field
    frame.setAll(data, -a, a, -a, a); // initialize field and scale
    for(int i = 0;i<nx;i++) {
      double x = frame.indexToX(i);
      for(int j = 0;j<ny;j++) {
        double y = frame.indexToY(j);
        double r2 = x*x+y*y;                // distance squared
        double r3 = Math.sqrt(r2)*r2;       // distance cubed
        data[0][i][j] = (r2==0) ? 0 : x/r3; // x-component
        data[1][i][j] = (r2==0) ? 0 : y/r3; // y-component
      }
    }
    frame.setAll(data); // vector field displays new data
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
