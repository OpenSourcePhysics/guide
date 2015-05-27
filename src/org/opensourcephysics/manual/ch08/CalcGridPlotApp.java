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
import org.opensourcephysics.display.InteractiveMouseHandler;
import org.opensourcephysics.display.InteractivePanel;
import org.opensourcephysics.display.PlottingPanel;
import org.opensourcephysics.display2d.ColorMapper;
import org.opensourcephysics.display2d.GridPlot;
import org.opensourcephysics.display2d.GridPointData;
import org.opensourcephysics.numerics.MultiVarFunction;
import org.opensourcephysics.numerics.ParsedMultiVarFunction;
import org.opensourcephysics.numerics.ParserException;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

/**
 * CalcGridPlotApp tests the capabilities of the GridPlot class by allowing the user
 * to input a function, f(x,y).
 * Copyright:    Copyright (c) 2005  Gould, Christian, and Tobochnik
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class CalcGridPlotApp implements Calculation, InteractiveMouseHandler {
  private Control myControl;
  private PlottingPanel drawingPanel = new PlottingPanel("x", "y", "Grid Plot");
  private DrawingFrame drawingFrame = new DrawingFrame(drawingPanel);
  private GridPointData pointdata;
  private GridPlot field;
  private MultiVarFunction fun;
  private DecimalFormat decimalFormat = new DecimalFormat("0.00"); // display format for message box.

  /**
   * The CheckerFieldModel constructor.
   */
  public CalcGridPlotApp() {
    drawingPanel.setInteractiveMouseHandler(this);
    drawingFrame.setVisible(true);
  }

  /**
   * Sets the Control for this model.
   */
  public void setControl(Control control) {
    myControl = control;
    resetCalculation();
  }

  /**
   * Evaluates the function at the grid points.
   */
  public void evaluateFunctionOnGrid() {
    double[][][] data = pointdata.getData();
    for(int i = 0, ny = data.length;i<ny;i++) {
      for(int j = 0, nx = data[0].length;j<nx;j++) {
        data[i][j][2] = fun.evaluate(new double[] {data[i][j][0], data[i][j][1]});
      }
    }
  }

  /**
   * Set the default parameters in the control.
  */
  public void resetCalculation() {
    myControl.setValue("grid size", 32);
    myControl.setValue("show grid", true);
    myControl.setValue("xmin", -1);
    myControl.setValue("xmax", 1);
    myControl.setValue("ymin", -1);
    myControl.setValue("ymax", 1);
    myControl.setValue("f(x,y)", "sin(x)*cos(y)");
    myControl.setValue("palette type", ColorMapper.SPECTRUM);
    calculate();
  }

  /**
   * Read parameters from the control start the calculation.
   */
  public void calculate() {
    // read the grid size and create the 2d data-storage object
    int size = myControl.getInt("grid size");
    pointdata = new GridPointData(size, size, 1); // a grid with one data component
    pointdata.setScale(myControl.getDouble("xmin"), myControl.getDouble("xmax"), myControl.getDouble("ymin"), myControl.getDouble("ymax"));
    // read and parse the function
    String fstr = myControl.getString("f(x,y)");
    try {
      fun = new ParsedMultiVarFunction(fstr, new String[] {"x", "y"});
    } catch(ParserException ex) {
      myControl.println("invalid function:"+fstr);
      myControl.println(ex.getMessage());
      return;
    }
    evaluateFunctionOnGrid();
    // create the scale field plot
    field = new GridPlot(pointdata);
    field.setAutoscaleZ(true, 0, 0);
    field.setPaletteType(myControl.getInt("palette type"));
    field.setShowGridLines(myControl.getBoolean("show grid"));
    field.update();
    drawingPanel.clear();            // remove the old field
    drawingPanel.addDrawable(field); // add the new field
    drawingFrame.repaint();
  }

  /**
 * Handles mouse actions by dragging the current interactive drawable object.
 *
 * @param panel
 * @param evt
 */
  public void handleMouseAction(InteractivePanel panel, MouseEvent evt) {
    double x = panel.getMouseX();
    double y = panel.getMouseY();
    switch(panel.getMouseAction()) {
    case InteractivePanel.MOUSE_DRAGGED :
      panel.setMessage("V="+decimalFormat.format(pointdata.getVertex(x, y)[2]));
      break;
    case InteractivePanel.MOUSE_PRESSED :
      panel.setMessage("V="+decimalFormat.format(pointdata.getVertex(x, y)[2]));
      break;
    case InteractivePanel.MOUSE_RELEASED :
      panel.setMessage(null);
      break;
    }
  }

  public static void main(String[] args) {
    Calculation model = new CalcGridPlotApp();
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
