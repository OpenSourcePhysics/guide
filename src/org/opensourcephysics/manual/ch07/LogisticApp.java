/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch07;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.DataRaster;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * LogisticApp tests the DataRaster class by creating a return map for the logistic function.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class LogisticApp {

  /**
   * Starts the Java application.
   * @param args
   */
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("x", "y", "Logistic Equation Return Map");
    DrawingFrame frame = new DrawingFrame(panel);
    DataRaster dataRaster = new DataRaster(panel, 0.7, 1, 0, 1);
    panel.addDrawable(dataRaster);
    int nplot = 400;
    dataRaster.setColor(0, new Color(128, 0, 0, 128)); // first dataset is dark red
    dataRaster.setColor(1, new Color(0, 128, 0, 128)); // second dataset is dark green
    for(double r = 0.7;r<1.0;r += 0.0005) {
      double x = 0.5;               // starting value for x
      for(int i = 1;i<=nplot;i++) { // nplot values not plotted
        x = 4*r*x*(1-x);
      }
      for(int i = 1;i<=nplot/2;i++) {
        x = 4*r*x*(1-x);
        dataRaster.append(0, r, x); // show x-values for current value of r
      }
      for(int i = 1;i<=nplot/2;i++) {
        x = 4*r*x*(1-x);
        dataRaster.append(1, r, x); // note different data
      }
    }
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
