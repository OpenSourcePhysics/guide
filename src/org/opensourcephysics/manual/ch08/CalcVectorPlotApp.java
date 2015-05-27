/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.controls.Calculation;
import org.opensourcephysics.controls.CalculationControl;
import org.opensourcephysics.controls.Control;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.PlottingPanel;
import org.opensourcephysics.display2d.GridPointData;
import org.opensourcephysics.display2d.VectorPlot;
import org.opensourcephysics.numerics.MultiVarFunction;
import org.opensourcephysics.numerics.ParsedMultiVarFunction;
import org.opensourcephysics.numerics.ParserException;

/**
 * CalcVectorPlotApp tests the capabilities of the VectorPlot class by allowing the user
 * to input vector field components, {f(x,y), g(x,y)}.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class CalcVectorPlotApp implements Calculation {
  private Control myControl;
  private PlottingPanel drawingPanel = new PlottingPanel("x", "y", "Scalar Field");
  private DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
  private GridPointData pointdata;
  private VectorPlot field;
  private MultiVarFunction funx;
  private MultiVarFunction funy;
  private boolean normalize = true;

  /**
   * The CalcVectorPlotApp constructor.
   */
  public CalcVectorPlotApp() {
    drawingPanel.setSquareAspect(false);
    drawingPanel.setAutoscaleX(true);
    drawingPanel.setAutoscaleY(true);
    drawingPanel.setSquareAspect(false);
    drawingPanel.setShowCoordinates(true);
    drawingFrame.setVisible(true);
  }

  /**
   * Set the Control for this model.
   */
  public void setControl(Control control) {
    myControl = control;
    resetCalculation();
  }

  public void evaluateFunctionOnGrid() {
    double[][][] data = pointdata.getData();
    for(int i = 0, nx = data.length;i<nx;i++) {
      for(int j = 0, ny = data[0].length;j<ny;j++) {
        double vx = funx.evaluate(new double[] {data[i][j][0], data[i][j][1]});
        double vy = funy.evaluate(new double[] {data[i][j][0], data[i][j][1]});
        double norm = 1;
        double mag = Math.sqrt(vx*vx+vy*vy);
        if(normalize) {
          norm = mag;
        }
        data[i][j][2] = mag;
        if(norm==0) {
          data[i][j][3] = 0;
          data[i][j][4] = 0;
        } else {
          data[i][j][3] = vx/norm;
          data[i][j][4] = vy/norm;
        }
      }
    }
  }

  /**
   * Set the default parameters in the control.
  */
  public void resetCalculation() {
    myControl.setValue("grid pts", 32);
    myControl.setValue("xmin", -1);
    myControl.setValue("xmax", 1);
    myControl.setValue("ymin", -1);
    myControl.setValue("ymax", 1);
    myControl.setValue("f(x,y)", "y");
    myControl.setValue("g(x,y)", "x");
    myControl.setValue("normalize", true);
    myControl.setValue("scale to grid", true);
    myControl.setValue("solid arrowhead", false);
    calculate();
  }

  /**
   * Read parameters from the control start the calculation.
   */
  public void calculate() {
    // read the grid size and create the 2d data-storage object
    int size = myControl.getInt("grid pts");
    pointdata = new GridPointData(size, size, 3); // a grid with one data component
    pointdata.setScale(myControl.getDouble("xmin"), myControl.getDouble("xmax"), myControl.getDouble("ymin"), myControl.getDouble("ymax"));
    // read and parse the x component function
    String fstr = myControl.getString("f(x,y)");
    try {
      funx = new ParsedMultiVarFunction(fstr, new String[] {"x", "y"});
    } catch(ParserException ex) {
      myControl.println("invalid function:"+fstr);
      myControl.println(ex.getMessage());
      return;
    }
    // read and parse the x component function
    String gstr = myControl.getString("g(x,y)");
    try {
      funy = new ParsedMultiVarFunction(gstr, new String[] {"x", "y"});
    } catch(ParserException ex) {
      myControl.println("invalid function:"+gstr);
      myControl.println(ex.getMessage());
      return;
    }
    normalize = myControl.getBoolean("normalize");
    evaluateFunctionOnGrid();
    // create the scale field plot
    field = new VectorPlot(pointdata);
    field.setAutoscaleZ(true, 0, 0);
    field.scaleArrowLenghToGrid(myControl.getBoolean("scale to grid"));
    if(myControl.getBoolean("solid arrowhead")) {
      field.setArrowType(VectorPlot.FILLEDARROW);
    } else {
      field.setArrowType(VectorPlot.STROKEDARROW);
    }
    field.update();
    drawingPanel.clear();            // remove the old field
    drawingPanel.addDrawable(field); // add the new field
    drawingFrame.repaint();
  }

  public static void main(String[] args) {
    Calculation model = new CalcVectorPlotApp();
    Control control = new CalculationControl(model);
    model.setControl(control);
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
