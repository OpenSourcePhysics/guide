/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.PlottingPanel;
import org.opensourcephysics.display.axes.PolarType1;
import javax.swing.JFrame;

/**
 * PolarPlottingApp demonstrates how to create a plotting panel with polar coordinate axes.
 */
public class PolarPlottingApp {

  /**
   * Starts the Java application.
   * @param args
   */
  public static void main(String[] args) {
    PlottingPanel plotPanel = new PlottingPanel(null, null, null);
    plotPanel.setSquareAspect(true);
    plotPanel.setPreferredMinMax(10, 10, -10, 10);
    DrawingFrame drawingFrame = new DrawingFrame(plotPanel);
    Circle circle = new Circle(0, 0);
    plotPanel.addDrawable(circle);
    Circle circle2 = new Circle(5.0, 5.0);
    plotPanel.addDrawable(circle2);
    PolarType1 axes = new PolarType1(plotPanel);
    axes.setDeltaR(1);
    axes.setDeltaTheta(Math.PI/8);
    plotPanel.setTitle("PolarPlot");
    org.opensourcephysics.display.GUIUtils.showDrawingAndTableFrames();
    drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    drawingFrame.setVisible(true);
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
