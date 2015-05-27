/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

/**
 * MainThreadApp pauses the main thread using the sleep method to produce a simple animation.
 *
 * @author W. Christian
 * @version 1.0
 */
public class MainThreadApp {
  public static void main(String[] args) {
    DisplayFrame frame = new DisplayFrame("x", "y", "Main Tread App");
    InteractiveShape rect = InteractiveShape.createRectangle(4, 3, 8, 10);
    double theta = 0, dtheta = 0.1;
    frame.addDrawable(rect);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    while(!Thread.currentThread().isInterrupted()) {
      rect.setTheta(theta);
      theta += dtheta; // increment theta
      frame.repaint(); // repaint with new theta
      try {            // sleep for 100 milliseconds
        Thread.sleep(100);
      } catch(InterruptedException ex) {}
    }
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
