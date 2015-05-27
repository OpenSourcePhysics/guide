/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display2d.ComplexGridPlot;

/**
 * ComplexGridPlotApp tests complex scalar field plotting routines.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class ComplexGridPlotApp {
  static final int SIZE = 32;

  /**
   * Starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    // create the frame, the panel, and the field
    DrawingPanel drawingPanel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    double[][][] data = new double[2][SIZE][SIZE];
    ComplexGridPlot plot = new ComplexGridPlot();
    plot.setAll(data, -1, 1, -1, 1);
    for(int i = 0;i<SIZE;i++) {
      double x = plot.indexToX(i);   // real component
      for(int j = 0;j<SIZE;j++) {
        double y = plot.indexToY(j); // imaginary component
        double r = Math.sqrt(x*x+y*y);
        data[0][i][j] = (r==0) ? 0 : Math.exp(-r*r*2)*x/r;
        data[1][i][j] = (r==0) ? 0 : Math.exp(-r*r*2)*y/r;
      }
    }
    plot.setAll(data);
    drawingPanel.addDrawable(plot);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
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
