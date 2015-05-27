/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;

/**
 * SetValueApp tests the setValue method and the setAdjustableValue method in SimulationControl.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class SetValueApp extends AbstractSimulation {
  double a, b;

  /**
   * Does an animation step by incrementing b.
   */
  protected void doStep() {
    control.println("product a*b="+(a*b));
  }

  public void initialize() {
    b = control.getDouble("b");
  }

  /**
   * Performs an action before executing one or more animation steps.
   */
  public void startRunning() {
    a = control.getDouble("a");
  }

  /**
   * Resets the animation to a predefined state.
   */
  public void reset() {
    control.setAdjustableValue("a", 1.0);
    control.setValue("b", 2.0); // fixed after initialization
    control.clearMessages();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new SetValueApp());
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
