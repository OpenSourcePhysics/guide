/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.numerics.*;

/**
 * PolynomialApp test the Polynomial class.
 *
 * @author W. Christian
 * @version 1.0
 */
public class PolynomialApp extends AbstractCalculation {
  PlottingPanel drawingPanel = new PlottingPanel("x", "f(x)", "Polynomial Visualization");
  DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
  double xmin, xmax;
  Polynomial p;

  /**
   * Resets the default polynomial.
   */
  public void resetCalculation() {
    control.setValue("coefficients", "-2,0,1");
    control.setValue("xmin", -10);
    control.setValue("xmax", 10);
  }

  /**
   * Calculates and displays the polynomial.
   */
  public void calculate() {
    xmin = control.getDouble("xmin");
    xmax = control.getDouble("xmax");
    String[] coef = control.getString("coefficients").split(",");
    p = new Polynomial(coef);
    plotAndCalculateRoots();
  }

  void plotAndCalculateRoots() {
    drawingPanel.clear();
    drawingPanel.addDrawable(new FunctionDrawer(p));
    double[] range = Util.getRange(p, xmin, xmax, 100);
    drawingPanel.setPreferredMinMax(xmin, xmax, range[0], range[1]);
    drawingPanel.repaint();
    double[] roots = p.rootsReal();
    control.clearMessages();
    control.println("polynomial="+p);
    for(int i = 0, n = roots.length;i<n;i++) {
      control.println("root="+roots[i]);
    }
  }

  public void derivative() {
    p = p.derivative();
    plotAndCalculateRoots();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    Calculation app = new PolynomialApp();
    CalculationControl c = new CalculationControl(app);
    c.addButton("derivative", "Derivative", "The derivative of the polynomial.");
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
