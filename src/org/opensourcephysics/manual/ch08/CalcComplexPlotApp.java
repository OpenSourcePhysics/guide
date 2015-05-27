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
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display2d.ColorMapper;
import org.opensourcephysics.display2d.ComplexSurfacePlot;
import org.opensourcephysics.display2d.GridPointData;
import org.opensourcephysics.display2d.SurfacePlotMouseController;
import org.opensourcephysics.numerics.MultiVarFunction;
import org.opensourcephysics.numerics.ParsedMultiVarFunction;
import org.opensourcephysics.numerics.ParserException;

/**
 * CalcSurfacePlotApp tests the capabilities of  the SurfacePlot class by allowing the user
 * to input a function, f(x,y).
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class CalcComplexPlotApp implements Calculation {
  private Control myControl;
  private DrawingPanel drawingPanel = new DrawingPanel();
  private DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
  private GridPointData pointdata;
  private ComplexSurfacePlot plot;
  private MultiVarFunction reFun, imFun;

  /**
   * The CheckerFieldModel constructor.
   */
  public CalcComplexPlotApp() {
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
    for(int i = 0, mx = data.length;i<mx;i++) {
      for(int j = 0, my = data[0].length;j<my;j++) {
        double re = reFun.evaluate(new double[] {data[i][j][0], data[i][j][1]});
        data[i][j][3] = re;
        double im = imFun.evaluate(new double[] {data[i][j][0], data[i][j][1]});
        data[i][j][4] = im;
        data[i][j][2] = Math.sqrt(re*re+im*im);
      }
    }
  }

  /**
   * Set the default parameters in the control.
  */
  public void resetCalculation() {
    myControl.setValue("grid size", 32);
    myControl.setValue("xmin", -3);
    myControl.setValue("xmax", 3);
    myControl.setValue("ymin", -3);
    myControl.setValue("ymax", 3);
    myControl.setValue("zmin", 0);
    myControl.setValue("zmax", 2);
    myControl.setValue("re(x,y)", "cos(x*y)");
    myControl.setValue("im(x,y)", "sin(x*y)");
    myControl.setValue("palette type", ColorMapper.SPECTRUM);
    calculate();
  }

  /**
   * Read parameters from the control start the calculation.
   */
  public void calculate() {
    // read the grid size and create the 2d data-storage object
    int size = myControl.getInt("grid size");
    pointdata = new GridPointData(size, size, 3); // a grid with amp, re, and im data components
    pointdata.setScale(myControl.getDouble("xmin"), myControl.getDouble("xmax"), myControl.getDouble("ymin"), myControl.getDouble("ymax"));
    // read and parse the function
    String fstr = myControl.getString("re(x,y)");
    try {
      reFun = new ParsedMultiVarFunction(fstr, new String[] {"x", "y"});
    } catch(ParserException ex) {
      myControl.println("invalid function:"+fstr);
      myControl.println(ex.getMessage());
      return;
    }
    fstr = myControl.getString("im(x,y)");
    try {
      imFun = new ParsedMultiVarFunction(fstr, new String[] {"x", "y"});
    } catch(ParserException ex) {
      myControl.println("invalid function:"+fstr);
      myControl.println(ex.getMessage());
      return;
    }
    evaluateFunctionOnGrid();
    // create the scale field plot
    plot = new ComplexSurfacePlot(pointdata);
    plot.setAutoscaleZ(true, 0, 0);
    plot.setPaletteType(myControl.getInt("palette type"));
    plot.setAutoscaleZ(false, myControl.getDouble("zmin"), myControl.getDouble("zmax"));
    plot.update();
    drawingPanel.clear();           // remove the old field
    drawingPanel.addDrawable(plot); // add the new field
    drawingFrame.repaint();
    SurfacePlotMouseController mouseController = new SurfacePlotMouseController(drawingPanel, plot);
    drawingPanel.addMouseListener(mouseController);
    drawingPanel.addMouseMotionListener(mouseController);
  }

  public static void main(String[] args) {
    Calculation model = new CalcComplexPlotApp();
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
