/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display2d.BinaryLattice;

/**
 * IsingApp demonstrates how to build a Graphical User Interface for the Ising
 * model using EJS.  Note that the Physics has been removed for clarity.
 */
public class IsingApp extends AbstractAnimation {
  int size = 32;
  double temperature = 2;
  DrawingPanel drawingPanel = new DrawingPanel();
  BinaryLattice lattice = new BinaryLattice(size, size);

  /**
   * Constructs the IsingApp.
   */
  public IsingApp() {
    drawingPanel.addDrawable(lattice);
  }

  /**
   * Sets the temparature in response to a slider action.
   */
  public void sliderMoved() {
    boolean isRunning = animationThread!=null;
    stopAnimation();
    temperature = control.getDouble("T");
    // physics has been removed for clarity
    if(isRunning) {
      startAnimation();
    }
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    // physics has been removed for clarity
    lattice.randomize();
    drawingPanel.repaint();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    IsingApp model = new IsingApp();
    Control control = new IsingControl(model);
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
