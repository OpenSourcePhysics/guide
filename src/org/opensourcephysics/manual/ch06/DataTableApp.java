/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.DataTable;
import org.opensourcephysics.display.DataTableFrame;
import org.opensourcephysics.display.DatasetManager;
import javax.swing.JFrame;

/**
 * DataTableApp demonstrates how to create a data table using a dataset manager.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class DataTableApp {

  /**
   * The main method starts the Java application.
   *
   * @param args
   */
  public static void main(String[] args) {
    DatasetManager datasets = new DatasetManager();
    datasets.setXPointsLinked(true); // x points only for 0th dataset
    double[] xpoints = {1.45, 3.99, 5, 7};
    double[] ypoints = {2, 4, 6, 8};
    datasets.append(0, xpoints, ypoints);
    double[] ypoints2 = {10.0, 12, 14, 45.2};
    datasets.append(1, xpoints, ypoints2); // same x values
    datasets.setXYColumnNames(0, "x1", "y1");
    datasets.setXYColumnNames(1, "x2", "y2");
    DataTable dataTable = new DataTable();
    DataTableFrame frame = new DataTableFrame("Data Table", dataTable);
    dataTable.add(datasets);
    datasets.setStride(1); // the default
    dataTable.setRowNumberVisible(true);
    dataTable.refreshTable();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
