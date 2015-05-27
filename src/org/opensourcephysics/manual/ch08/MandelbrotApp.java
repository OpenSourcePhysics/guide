/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.ByteRaster;

/**
 * MandelbrotApp creates an image of the Mandelbrot set.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class MandelbrotApp implements Runnable {
  static final int SIZE = 300; // the image size in pixels
  PlottingPanel panel = new PlottingPanel("re", "im", "Mandelbrot Set");
  DrawingFrame frame = new DrawingFrame(panel);
  ByteRaster byteRaster = new ByteRaster(SIZE, SIZE);
  byte[][] data = new byte[SIZE][SIZE];
  double reMin = -2, reMax = 1, imMin = -1.5, imMax = 1.5;

  /**
   * Constructs MandelbrotApp.
   */
  MandelbrotApp() {
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    panel.addDrawable(byteRaster);
    frame.setVisible(true);
    new Thread(this).start(); // creates and starts the thread
  }

  /**
   * Iterates the complex number to determine if it is in the Mandelbrot set.
   *
   * @param re
   * @param im
   *
   * @return the number of iterations
   */
  int iteration(double re, double im) {
    // algorithm: take z, square it, add the number (re,im)
    int count = 0;
    double a = 0, b = 0, temp = 0;
    while(count<255) {
      temp = a;
      a = a*a-b*b+re;
      b = 2*temp*b+im;
      if(a*a+b*b>4.0) {
        break;
      }
      count++;
    }
    return(10*count); // expand the scale by ten
  }

  /**
   * Runs the thread.
   */
  public void run() {
    for(int iy = 0;iy<SIZE;iy++) {
      double im = imMin+(imMax-imMin)*iy/SIZE;
      for(int ix = 0;ix<SIZE;ix++) {
        double re = reMin+(reMax-reMin)*ix/ SIZE;
        data[ix][iy] = (byte) (-128+iteration(re, im));
      }
      try { // yield after every row to give other threads a chance
        Thread.yield();
      } catch(Exception e) {}
    }
    byteRaster.setAll(data, reMin, reMax, imMin, imMax);
    panel.repaint();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new MandelbrotApp();
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
