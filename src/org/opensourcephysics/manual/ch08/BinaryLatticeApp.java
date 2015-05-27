/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.BinaryLattice;
import java.awt.Color;

/**
 * BinaryLatticeApp demonstrates how to create and use a binary lattice.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class BinaryLatticeApp {

  /**
   * Starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    PlottingPanel plottingPanel = new PlottingPanel("nx", "ny", "Binary Lattice Demo");
    DrawingFrame frame = new DrawingFrame(plottingPanel);
    BinaryLattice lattice = new BinaryLattice(32, 32);
    lattice.randomize(); // randomize all cells
    // zero a 16 x 16 block starting at x index 4 and y index 8
    lattice.setBlock(4, 8, new byte[16][16]);
    lattice.setColorPalette(new Color[] {Color.blue, Color.white});
    lattice.setGridLineColor(Color.black);
    plottingPanel.addDrawable(lattice);
    plottingPanel.setSquareAspect(false);
    plottingPanel.repaint();
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
