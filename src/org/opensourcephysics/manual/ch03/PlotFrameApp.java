/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.display.Dataset;
import org.opensourcephysics.frames.PlotFrame;

/**
 * PlotFrameApp simulates a best fit to a Gaussian spectral line using a PlotFrame with
 * two datasets.  These datasets use different drawing styles.
 *
 * @author W. Christian
 * @version 1.0
 */
public class PlotFrameApp {
  public static void main(String[] args) {
    PlotFrame frame = new PlotFrame("$\\Delta$f", "intensity", "Gaussian Lineshape");
    frame.setConnected(0, true);
    frame.setMarkerShape(0, Dataset.NO_MARKER);
    for(double x = -10;x<10;x += 0.2) {
      double y = Math.exp(-x*x/4);
      frame.append(0, x, y);                   // datum
      frame.append(1, x, y+0.1*Math.random()); // datum + noise
    }
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setXPointsLinked(true); // default is true
    frame.setXYColumnNames(0, "frequency", "theory");
    frame.setXYColumnNames(1, "frequency", "experiment");
    frame.setRowNumberVisible(true);
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
