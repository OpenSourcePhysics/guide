/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch12;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.InteractiveShape;
import org.opensourcephysics.frames.DisplayFrame;

/**
 * ParticleApp displays particles at random locations.
 *
 * This program is used to demonstrate how transfer xml data
 * between programs.
 *
 * @author W. Christian
 * @version 1.0
 */
public class ParticleApp extends AbstractCalculation {
  DisplayFrame frame = new DisplayFrame("x", "y", "Particles");

  /**
   * Creates a particles at random locations.
   */
  public void calculate() {
    frame.clearDrawables(); // remove old cirlces
    int n = control.getInt("number of particles");
    double r = control.getDouble("radius");
    for(int i = 0;i<n;i++) {
      double x = -10+20*Math.random();
      double y = -10+20*Math.random();
      frame.addDrawable(InteractiveShape.createCircle(x, y, r));
    }
  }

  /**
   * Resets the program to its default state.
   */
  public void reset() {
    control.setValue("number of particles", 10);
    control.setValue("radius", 0.5);
  }

  /**
   * Gets an XML.ObjectLoader to save and load data for this program.
   *
   * @return the object loader
   */
  public static XML.ObjectLoader getLoader() {
    return null;
    // uncomment the next line to enable the loader
    // return new ParticleAppLoader();
  }

  public static void main(String[] args) {
    // creates the program and reads xml data using command line arguments
    CalculationControl.createApp(new ParticleApp(), args);
    // command line arguments are optional
    // use args[0] = "default_particles.xml" to read the example
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
