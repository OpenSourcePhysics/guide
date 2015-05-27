/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.frames.FFT2DFrame;
import javax.swing.JFrame;

/**
 *
 * FFT2DFrameApp tests the FFT2DFrame class by taking the two-dimensional Fast Fourier Transform (FFT) of a harmonic function.
 *
 * @author W. Christian
 * @version 1.0
 */
public class FFT2DFrameApp {
  public static void main(String[] args) {
    FFT2DFrame frame = new FFT2DFrame("k_x", "k_y", "2D FFT");
    double xmin = 0, xmax = 2*Math.PI, ymin = 0, ymax = 2*Math.PI;
    // generate data on a grid of size (nx,ny)
    int nx = 5, ny = 5;
    int xMode = -1, yMode = -1;
    double[] zdata = new double[2*nx*ny]; // field stored in row-major format with array size 2*nx*ny
    // test function is e^(i*xmode*x)e^(i*ymode*y) where where x and y limits are [0,2pi].
    double y = 0, yDelta = 2*Math.PI/ny;
    for(int iy = 0;iy<ny;iy++) {
      int offset = 2*iy*nx; // offset to beginning of a row;  each row is nx long
      double x = 0, xDelta = 2*Math.PI/nx;
      for(int ix = 0;ix<nx;ix++) {
        zdata[offset+2*ix] = // real part
          Math.cos(xMode*x)*Math.cos(yMode*y)-Math.sin(xMode*x)*Math.sin(yMode*y);
        zdata[offset+2*ix+1] = // imaginary part
          Math.sin(xMode*x)*Math.cos(yMode*y)+Math.cos(xMode*x)*Math.sin(yMode*y);
        x += xDelta;
      }
      y += yDelta;
    }
    frame.doFFT(zdata, nx, xmin, xmax, ymin, ymax);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
