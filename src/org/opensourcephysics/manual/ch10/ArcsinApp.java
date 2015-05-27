/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.display.*;

/**
 * Compares the interpolated inverse sine function to the exact result.
 *
 * @author W. Christian
 * @version 1.0
 */
public class ArcsinApp {

  /**
   * Starts the Java applicaiton.
   * @param args String[] command line parameters
   */
  public static void main(String[] args) {
    PlottingPanel drawingPanel = new PlottingPanel("x", "arcsin(x)", "Inverse Function");
    DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
    DatasetManager dataset = new DatasetManager();
    dataset.setConnected(true);
    dataset.setMarkerShape(0, Dataset.NO_MARKER);
    dataset.setMarkerShape(1, Dataset.NO_MARKER);
    int n = 100;
    double x = -1, dx = 2.0/(n-1);
    for(int i = 0;i<n;i++) {
      dataset.append(0, x, Arcsin.evaluate(x));
      dataset.append(1, x, Math.asin(x));
      x += dx;
    }
    drawingPanel.addDrawable(dataset);
    drawingFrame.setVisible(true);
    drawingFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
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
