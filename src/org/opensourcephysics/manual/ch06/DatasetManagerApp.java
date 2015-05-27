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
 * DatasetManagerApp demonstrates how to create three datasets using a dataset
 * manager and how to append data points to these datasets.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class DatasetManagerApp {

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    int numberOfDatasets = 3;
    int pointsPerDataset = 10;
    DatasetManager datasetManager = new DatasetManager();
    datasetManager.setConnected(true);
    datasetManager.setSorted(true);
    for(int i = 0;i<numberOfDatasets;i++) {
      for(int j = 0;j<pointsPerDataset;j++) {
        datasetManager.append(i, Math.random()*20, Math.random()*20);
      }
    }
    PlottingPanel panel = new PlottingPanel("x", "y", "Three Datasets");
    panel.setAutoscaleX(true);
    panel.setAutoscaleY(true);
    panel.addDrawable(datasetManager);
    DrawingFrame frame = new DrawingFrame(panel);
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
