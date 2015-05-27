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
 * ComplexSurfacePlotApp displays a gaussian quantum wave function with a
 * phase modulation (momentum boost) in the x direction.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class ComplexSurfacePlotApp {
  final static int SIZE = 32;

  /**
   * The main method that starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    DrawingPanel drawingPanel = new DrawingPanel();
    drawingPanel.setShowCoordinates(false);
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    double[][][] data = new double[2][32][32];
    double[][] xdata = data[0];
    double[][] ydata = data[1];
    ComplexSurfacePlot plot = new ComplexSurfacePlot();
    plot.setAll(data, -1.5, 1.5, -1.5, 1.5);
    for(int i = 0;i<SIZE;i++) {
      double x = plot.indexToX(i);           // the x location
      for(int j = 0;j<SIZE;j++) {
        double y = plot.indexToY(j);         // the y location
        double amp = Math.exp(-2*(x*x+y*y)); // magnitude
        xdata[i][j] = amp*Math.cos(5*x);     // real component
        ydata[i][j] = amp*Math.sin(5*x);     // imaginary component
      }
    }
    plot.setAutoscaleZ(false, 0, 1);
    plot.setAll(data);
    drawingPanel.addDrawable(plot);
    drawingPanel.repaint();
    SurfacePlotMouseController mouseController = new SurfacePlotMouseController(drawingPanel, plot);
    drawingPanel.addMouseListener(mouseController);
    drawingPanel.addMouseMotionListener(mouseController);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
