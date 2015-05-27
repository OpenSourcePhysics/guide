/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.PlottingPanel;
import org.opensourcephysics.display2d.ComplexContourPlot;
import org.opensourcephysics.display2d.GridPointData;
import javax.swing.JFrame;

/**
 * GradientApp tests the gradient utility.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class ComplexContourApp {

  /**
   * The main method that starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    PlottingPanel drawingPanel = new PlottingPanel("x", "y", "Complex Overlay");
    drawingPanel.setSquareAspect(false);
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    GridPointData pointdata = new GridPointData(32, 32, 3); // a 32 by 32 grid with one data component
    pointdata.setScale(-1.0, 1.0, -1.0, 1.0);
    // create test data for the complex field.
    double[][][] data = pointdata.getData();
    int row = data.length;
    int col = data[0].length;
    for(int i = 0;i<col;i++) {
      for(int j = 0;j<row;j++) {
        double x = data[i][j][0];                    // the x location
        double y = data[i][j][1];                    // the y location
        double r = Math.sqrt(x*x+y*y);
        data[i][j][2] = Math.exp(-r*r*2);            // magnitude
        data[i][j][3] = data[i][j][2]*Math.cos(5*x); // real component
        data[i][j][4] = data[i][j][2]*Math.sin(5*x); // imaginary component
      }
    }
    ComplexContourPlot field = new ComplexContourPlot(pointdata);
    drawingPanel.addDrawable(field); // add the scalar field
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
