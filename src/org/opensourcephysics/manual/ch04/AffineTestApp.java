/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see:
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;

/**
 * AffineTestApp demonstrates how to transform 2D shapes.
 *
 *  @author W. Christian
 *  @version 1.0
 */
public class AffineTestApp extends AbstractCalculation {
  PlottingPanel panel = new PlottingPanel(null, null, null);
  DrawingFrame frame = new DrawingFrame(panel);
  InteractiveShape rect = InteractiveShape.createRectangle(0, 0, 4, 4);

  /**
   * Constructs the Affine2DApp.
   */
  public AffineTestApp() {
    panel.addDrawable(rect);
    frame.setVisible(true);
  }

  /**
   * Performs a calculation by reading the transformation matrix and applying it to the shape.
   */
  public void calculate() {
    double m[][] = new double[3][]; // allocate 3 rows but not the elements
    // set the first row of the matrix
    m[0] =  (double[])control.getObject("r0"); // returned array becomes the first row
    // set the second row
    m[1] = (double[])control.getObject("r1"); // returned array becomes the second row
    // set the third row
    m[2] = (double[])control.getObject("r2"); // returned array becomes the third row
    rect.tranform(m);
    panel.repaint();
  }

  /**
   * Resets the calculation by setting the transformation to the identity and
   * creating a 4 by 4 rectangle at the origin.
   */
  public void resetCalculation() {
    control.clearMessages();
    control.setValue("r0", new double[]{1,0,0});
    control.setValue("r1", new double[]{0,1,0});
    control.setValue("r2", new double[]{0,0,1});
    rect = InteractiveShape.createRectangle(0, 0, 4, 4);
    panel.clear();
    panel.addDrawable(rect);
    calculate();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    Calculation app = new AffineTestApp();
    Control c = new CalculationControl(app);
    app.setControl(c);
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
