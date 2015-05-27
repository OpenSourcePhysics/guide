/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.frames.*;
import org.opensourcephysics.numerics.*;

/**
 * FFTRealApp computes the Fourier coefficients of a real-valued function.
 *
 * @author W. Christian
 * @version 1.0
 */
public class FFTRealApp {

  /**
   * Main method for FFTApp program.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    TableFrame frame = new TableFrame("Real FFT");
    frame.setColumnNames(0, "frequency");
    frame.setColumnNames(1, "cos");
    frame.setColumnFormat(1, "#0.00");
    frame.setColumnNames(2, "sin");
    frame.setColumnFormat(2, "#0.00");
    int n = 16;
    double[] data = new double[n];
    double x = 0, dx = 1.0/n;
    for(int i = 0;i<n;i++) {
      data[i] = Math.sin(2*Math.PI*x);
      x += dx;
    }
    FFTReal realFFT = new FFTReal(n);
    realFFT.transform(data);
    double[] f = realFFT.getNaturalFreq(dx);
    for(int i = 0;i<n;i += 2) {
      frame.appendRow(new double[] {f[i/2], data[i], data[i+1]});
    }
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
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
