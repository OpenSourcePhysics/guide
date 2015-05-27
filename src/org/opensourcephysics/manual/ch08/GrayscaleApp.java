/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.*;
import javax.swing.JFrame;

/**
 * GrayscaleApp demonstrates how to display a scalar field using the GrayscalePlot class.
 *
 * A grayscale plot looks similar to a checkerboard plot with a grayscale color palette.
 * However, it uses a different rendering model.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class GrayscaleApp {

  /**
   * Starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    // create the frame, the panel, and the field
    DrawingPanel drawingPanel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    GridPointData pointdata = new GridPointData(16, 16, 1);
    pointdata.setScale(-1, 1, -1, 1);
    // creates test data for the scalar field.
    double[][][] data = pointdata.getData();
    int row = data.length;
    int col = data[0].length;
    for(int i = 0;i<row;i++) {
      for(int j = 0;j<col;j++) {
        double x = data[i][j][0]; // the x location
        double y = data[i][j][1]; // the y location
        data[i][j][2] = y*x;      // magnitude
      }
    }
    // update and repaint because values have changed.
    GrayscalePlot plot = new GrayscalePlot(pointdata);
    drawingPanel.addDrawable(plot);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // GUIUtils.timingTest(field);
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
