/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.BinaryLattice;
import org.opensourcephysics.frames.*;

/**
 * Simple random walk model.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
import java.awt.Color;

/**
 * Random walk on a lattice.
 */
public class RandomWalkApp extends AbstractSimulation {
  DisplayFrame drawingFrame = new DisplayFrame("Random Walk");
  Dataset dataset = new Dataset();
  BinaryLattice lattice;
  int walkerX;
  int walkerY;
  int size = 32;

  /**
   * The SimpleRandomWalkModel constructor.
   */
  public RandomWalkApp() {
    dataset.setLineColor(Color.black);
    dataset.setConnected(false);
    dataset.setMarkerShape(Dataset.CIRCLE);
    dataset.setMarkerSize(3);
    dataset.setMarkerColor(Color.red);
  }

  /**
   * Read parameters from the control start the calculation.
   */
  public void initialize() {
    size = control.getInt("lattice size");
    drawingFrame.setPreferredMinMax(0, size, size, 0);
    drawingFrame.clearDrawables();
    dataset.clear();
    lattice = new BinaryLattice(size, size);
    lattice.setColorPalette(new Color[] {new Color(128, 128, 255), Color.white});
    lattice.setGridLineColor(Color.black);
    drawingFrame.addDrawable(lattice);
    drawingFrame.addDrawable(dataset);
    for(int i = 0;i<size;i++) {
      for(int j = 0;j<size;j++) {
        lattice.setValue(i, j, 0);
      }
    }
    walkerX = size/2;
    walkerY = size/2;
    lattice.setValue(walkerX, walkerY, 1);
    dataset.append(walkerX+0.5, walkerY+0.5);
  }

  /**
   * Set the default parameters in the control.
   */
  public void reset() {
    control.setValue("lattice size", 32);
    initialize();
  }

  /**
   * Step the Lattice by one generation.
   */
  protected void doStep() {
    if(lattice==null) {
      return;
    }
    lattice.setValue(walkerX, walkerY, 0);
    switch((int) (4*Math.random())) {
    case 0 :
      walkerX++;
      walkerX = walkerX%size;
      break;
    case 1 :
      walkerX--;
      walkerX = (walkerX+size)%size;
      break;
    case 2 :
      walkerY++;
      walkerY = walkerY%size;
      break;
    case 3 :
      walkerY--;
      walkerY = (walkerY+size)%size;
      break;
    }
    lattice.setValue(walkerX, walkerY, 1);
    dataset.append(walkerX+0.5, walkerY+0.5);
  }

  /* --------------- application target  --------------- */
  public static void main(String[] args) {
    SimulationControl.createApp(new RandomWalkApp());
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
