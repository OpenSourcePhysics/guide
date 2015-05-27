/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.frames.Scalar2DFrame;
import javax.swing.JFrame;

/**
 * Scalar2DFrameApp tests the Scalar2DFrame by plotting a scalar field f(x,y)=x*y.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class Scalar2DFrameApp {
  public static void main(String[] args) {
    Scalar2DFrame frame = new Scalar2DFrame("x", "y", "Scalar Field");
    // generate sample data
    double[][] data = new double[32][32];
    frame.setAll(data, -10, 10, -10, 10); // initialize field and scale
    for(int i = 0, nx = data.length;i<nx;i++) {
      double x = frame.indexToX(i);
      double ax = (x==0) ? 1 : Math.abs(Math.sin(x)/x);
      for(int j = 0, ny = data[0].length;j<ny;j++) {
        double y = frame.indexToY(j);
        double ay = (x==0) ? 1 : Math.abs(Math.sin(y)/y);
        data[i][j] = ax*ay; // square root of intensity
      }
    }
    frame.setAll(data); // set new values
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
