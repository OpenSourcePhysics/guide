/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.*;

/**
 * VectorPlotApp creates and tests a VectorField plot.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class VectorPlotApp {
  static final int SIZE = 24;

  /**
   * The main method that starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    DrawingPanel drawingPanel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    drawingPanel.setSquareAspect(true);
    double[][][] data = new double[2][SIZE][SIZE];
    VectorPlot plot = new VectorPlot();
    plot.setAll(data, -1, 1, -1, 1);
    for(int i = 0;i<SIZE;i++) {
      double x = plot.indexToX(i);   // the x location
      for(int j = 0;j<SIZE;j++) {
        double y = plot.indexToY(j); // the y location
        data[0][i][j] = -y;
        data[1][i][j] = x;
      }
    }
    plot.setAll(data);
    drawingPanel.addDrawable(plot);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    plot.showLegend();
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
