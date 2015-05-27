/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.*;
import javax.swing.JFrame;

/**
 * GaussianGridPlotApp demonstrates how to plot a gaussian
 * scalar field using the GridPlot class.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class GaussianGridPlotApp {
  public static void main(String[] args) {
    int size = 48;
    DrawingPanel drawingPanel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(drawingPanel);
    GridPointData pointdata = new GridPointData(size, size, 1); // creates a 32 by 32 grid with one sample
    pointdata.setScale(-1.5, 1.5, -1.5, 1.5);
    GridPlot field = new GridPlot(pointdata);
    field.setAutoscaleZ(false, 0, 1); // sets the z scale 0 -> 3
    drawingPanel.addDrawable(field);
    // calculate the field
    TestData.gaussianScalarField(pointdata);
    field.update(); // signal the field that the data has changed
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
