/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display2d.ArrayData;
import org.opensourcephysics.display2d.SurfacePlot;
import org.opensourcephysics.display2d.SurfacePlotMouseController;
import javax.swing.JFrame;

/**
 * SurfacePlotApp creates a surface plot.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class SurfaceArrayApp {

  /**
   * Starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    DrawingPanel drawingPanel = new DrawingPanel();
    drawingPanel.setShowCoordinates(false);
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    ArrayData arraydata = new ArrayData(32, 32, 1);
    arraydata.setScale(-1, 1, -1, 1);
    double[][] data = arraydata.getData()[0];
    double x = arraydata.getLeft(), dx = arraydata.getDx();
    for(int i = 0, nx = data.length;i<nx;i++) {
      double y = arraydata.getTop(), dy = arraydata.getDy();
      for(int j = 0, ny = data[0].length;j<ny;j++) {
        data[i][j] = y*x; // magnitude
        y += dy;
      }
      x += dx;
    }
    SurfacePlot plot = new SurfacePlot(arraydata);
    drawingPanel.addDrawable(plot);
    SurfacePlotMouseController mouseController = new SurfacePlotMouseController(drawingPanel, plot);
    drawingPanel.addMouseListener(mouseController);
    drawingPanel.addMouseMotionListener(mouseController);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    // GUIUtils.timingTest(plot);
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
