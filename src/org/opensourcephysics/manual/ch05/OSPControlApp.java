/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;

/**
 * OSPControlApp creates an OSPControl, adds a button to the control, and
 * reads parameters from the control.
 *
 * @author W. Christian
 * @version 1.0
 */
public class OSPControlApp {
  OSPControl control = new OSPControl(this); // creates the control

  public OSPControlApp() {
    control.addButton("getValues", "Get", "Gets values from the control.");
    reset(); // stores default values
  }

  /**
   * Gets values from the control using accessor methods.
   */
  public void getValues() {
    double val = control.getDouble("value");
    control.println("value="+val);
    boolean newData = control.getBoolean("new data");
    control.println("new data="+newData);
    String str = control.getString("hello");
    control.println("hello="+str);
    int n = control.getInt("number of points");
    control.println("# points="+n);
  }

  /**
   * Resets the control to its default state.
   */
  public void reset() {
    control.setValue("value", "2.0*pi");
    control.setValue("new data", false);
    control.setValue("hello", "hello world");
    control.setValue("number of points", 100);
  }

  public static void main(String[] args) {
    new OSPControlApp();
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
