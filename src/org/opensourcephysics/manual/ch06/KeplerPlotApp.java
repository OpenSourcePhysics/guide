/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.*;

/**
 * KeplerPlotApp demonstrates how to create log-log plot using data from
 * planetary orbits to demonstrate the validity of Kepler's second law.
 *
 * The program plots the log of the planet's semi-major axis vs. the log of the orbital period.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class KeplerPlotApp {

  /**
   * Command line entry point for the KeplerPlotApp program.
   * @param args
   */
  public static void main(String[] args) {
    PlottingPanel plotPanel = new PlottingPanel("ln(a)", "ln(T)", "Kepler's Second Law");
    // PlottingPanel plotPanel= PlottingPanel.createType2("ln(a)", "ln(T)", "Kepler's Second Law");
    plotPanel.setLogScale(true, true);
    DrawingFrame drawingFrame = new DrawingFrame(plotPanel);
    Dataset dataset = new Dataset();
    dataset.setConnected(false);
    double[] period = {
      0.241, 0.615, 1.0, 1.88, 11.86, 29.50, 84.0, 165, 248
    };
    double[] a = {
      0.387, 0.723, 1.0, 1.523, 5.202, 9.539, 19.18, 30.06, 39.44
    };
    dataset.append(a, period);
    plotPanel.addDrawable(dataset);
    drawingFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    drawingFrame.setVisible(true);
    // create a data table
    DataTable dataTable = new DataTable();
    DataTableFrame tableFrame = new DataTableFrame("Orbital Data", dataTable);
    dataset.setXYColumnNames("T (years)", "a (AU)");
    dataTable.add(dataset);
    dataTable.setRowNumberVisible(true);
    dataTable.refreshTable();
    tableFrame.setVisible(true);
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
