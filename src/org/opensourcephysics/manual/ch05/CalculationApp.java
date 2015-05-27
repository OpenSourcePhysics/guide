/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;

/**
 * A demonstrtion of a Calculation.
 * Edit this example to build a new Calculation.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class CalculationApp implements Calculation {
  private Control control; // the control
  private double x0;       // a parameter

  /**
   * Sets object that controls this calculation.
   *
   * The calculation should use this method to register its parameters with the control.
   * This insures that the control displays the program's parameters when it appears onscreen.
   *
   * @param control
   */
  public void setControl(Control control) {
    this.control = control;
    resetCalculation();
  }

  /**
   * Does the calculation.
   */
  public void calculate() {
    x0 = control.getDouble("x0"); // read a parameter value
    // do some calculations here.
    control.println("The value of x*x ="+(x0*x0));
  }

  /**
   * Resets the calculation to a predefined state.
   */
  public void resetCalculation() {
    control.clearMessages();
    control.setValue("x0", 2); // set a parameter value
    control.setValue("y0", 3); // set a parameter value
    control.setValue("m", 1);  // set a parameter value
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    CalculationControl.createApp(new CalculationApp());
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
