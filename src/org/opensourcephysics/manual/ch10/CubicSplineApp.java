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
 * CubicSplineApp implements a visualization of cubic spline interpolation.
 *
 * @author  W. Christian
 * @version 1.0
 */
public class CubicSplineApp extends AbstractCalculation {
  PlottingPanel drawingPanel = new PlottingPanel("x", "f(x)", "Cubic Spline Fit");
  DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
  Dataset dataset = new Dataset();

  /**
   * Constructs a CubicSplineApp.
   */
  public CubicSplineApp() {
    dataset.setConnected(false);
  }

  /**
   * Resets the calculations's parameters and does the calculation.
   */
  public void resetCalculation() {
    control.setValue("f(x)", "exp(-x*x)");
    control.setValue("sample start", -2);
    control.setValue("sample stop", 2);
    control.setValue("n", 5);
    control.setValue("random y-error", 0);
    calculate();
  }

  /**
   * Calculates and displays a cubic spline fit.
   */
  public void calculate() {
    String fstring = control.getString("f(x)");
    double a = control.getDouble("sample start");
    double b = control.getDouble("sample stop");
    int n = control.getInt("n"); // number of intervals
    double err = control.getDouble("random y-error");
    double dx = (n>1) ? (b-a)/(n-1) : 0;
    Function f;
    try {
      f = new ParsedFunction(fstring);
    } catch(ParserException ex) {
      control.println(ex.getMessage());
      return;
    }
    drawingPanel.clear();
    dataset.clear();
    double[] range = Util.getRange(f, a, b, 100);
    drawingPanel.setPreferredMinMax(a-(b-a)/4, b+(b-a)/4, range[0], range[1]);
    drawingPanel.addDrawable(dataset);
    FunctionDrawer func = new FunctionDrawer(f);
    func.color = java.awt.Color.RED;
    drawingPanel.addDrawable(func);
    double x = a;
    for(int i = 0;i<n;i++) {
      dataset.append(x, f.evaluate(x)*(1+err*(-0.5+Math.random())));
      x += dx;
    }
    drawingPanel.addDrawable(new FunctionDrawer(new CubicSpline(dataset.getXPoints(), dataset.getYPoints())));
    drawingPanel.repaint();
    drawingFrame.setVisible(true);
  }

  /**
   * Starts the Java applicaiton.
   * @param args String[] command line parameters
   */
  public static void main(String[] args) {
    CalculationControl.createApp(new CubicSplineApp(), args);
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
