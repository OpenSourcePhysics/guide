/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display.axes.XAxis;
import org.opensourcephysics.display.axes.YAxis;
import javax.swing.JFrame;

/**
 * DatasetApp demonstrates how to create a dataset, set options, and append data.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class DatasetApp {

  /**
   * Starts the Java application.
   * @param arg
   */
  public static void main(String[] args) {
    // create a drawing frame and a drawing panel
    DrawingPanel panel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(panel);
    panel.setSquareAspect(false);
    panel.setAutoscaleX(true);
    panel.limitAutoscaleY(-5, 15);
    // create data and append it to a dataset
    double[] x = {-2, -1, 0, 1, 2};
    double[] y = {-2, 2, 6, 10, 14};
    Dataset dataset = new Dataset();
    dataset.setConnected(true);
    dataset.setSorted(true);  // sort points as needed
    dataset.append(x, y);
    dataset.append(5, 11);    // a single point
    dataset.append(3.5, 2.5); // this point will be sorted
    // add the drawable objects to the drawing panel
    panel.addDrawable(dataset);
    XAxis xaxis = new XAxis("x");
    panel.addDrawable(xaxis);
    YAxis yaxis = new YAxis("y");
    panel.addDrawable(yaxis);
    panel.repaint();
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
