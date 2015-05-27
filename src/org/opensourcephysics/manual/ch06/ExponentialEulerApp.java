/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.*;
import javax.swing.JFrame;

/**
 * ExponentialEulerApp shows how to generate a table and a graph by solving a
 * simple differential equation, dy/dt = -1, using Euler's method.
 *
 * @author W. Christian
 */
public class ExponentialEulerApp {
  DatasetManager datasetManager;
  DataTable dataTable;
  DataTableFrame tableFrame;
  PlottingPanel plottingPanel;
  DrawingFrame drawingFrame;
  double t0 = 0.0;   // initial value of t
  double y0 = 1.0;   // initial value of y
  double tmax = 3.0; // t value that stops the calculation
  double dt = 0.10;  // step size

  /**
   * Constructs the ExponentialEulerApp.
   *
   */
  public ExponentialEulerApp() { // ExponentialEulerApp constructor
    // set up the dataset manager
    datasetManager = new DatasetManager();
    datasetManager.setXPointsLinked(true); // X only for 0-th dataset
    datasetManager.setXYColumnNames(0, "n", "t_n");
    datasetManager.setXYColumnNames(1, "t", "Exact y");
    datasetManager.setXYColumnNames(2, "t", "Euler y");
    datasetManager.setXYColumnNames(3, "t", "Error");
    // set up table
    dataTable = new DataTable();
    tableFrame = new DataTableFrame(dataTable);
    dataTable.add(datasetManager); // add datasetManager to table
    tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tableFrame.setVisible(true);
    // set up graph
    plottingPanel = new PlottingPanel("t", "y", "dy/dt = -y");
    drawingFrame = new DrawingFrame(plottingPanel);
    plottingPanel.addDrawable(datasetManager.getDataset(1)); // exact
    plottingPanel.addDrawable(datasetManager.getDataset(2)); // Euler
    drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    drawingFrame.setVisible(true);
  }

  /**
   * Calculates the soltion using Euler's algorithm.
   *
   */
  public void calculate() {
    double t = t0; // current value of x
    double y = y0; // current value of the Euler solution
    int counter = 0;
    while(t<=tmax) {
      datasetManager.append(0, counter, t); // store counter and x
      double exact = Math.exp(-t);
      datasetManager.append(1, t, exact);   // store t and exact x
      datasetManager.append(2, t, y);       // store Euler solution
      datasetManager.append(3, t, exact-y); // store Euler solution
      y = y-y*dt;                           // increase y
      t = t+dt;                             // increase t
      counter++;                            // number of steps
    }
    plottingPanel.repaint();  // repaint the graph
    dataTable.refreshTable(); // redraw the table
  }

  /**
   * Starts the ExponentialEulerApp.
   *
   * @param args
   */
  public static void main(String[] args) {
    ExponentialEulerApp app = new ExponentialEulerApp();
    app.calculate();
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
