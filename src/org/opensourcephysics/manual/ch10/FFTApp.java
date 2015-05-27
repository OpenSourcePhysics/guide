/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.numerics.*;

/**
 * FFTApp tests the fast Fourier transform (fft).
 *
 * @author W. Christian
 */
public class FFTApp {

  /**
   * Main method for FFTApp program.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    int numpts = 10;
    double[] data = new double[2*numpts];
    FFT fft = new FFT(numpts);
    double x = 0, dx = 1.0/numpts;
    double cycles = 1;
    for(int i = 0;i<numpts;i++) {
      data[2*i] = Math.cos(cycles*2*Math.PI*x);
      data[2*i+1] = Math.sin(cycles*2*Math.PI*x);
      x += dx;
    }
    System.out.println("Data before FFT.");
    printData(data);
    fft.transform(data);
    System.out.println("Data after FFT.");
    printData(data);
    fft.inverse(data);
    System.out.println("Data after FFT inverse.");
    printData(data);
  }

  static void printData(double[] data) {
    for(int i = 0, n = data.length/2;i<n;i++) {
      String re = Util.f4(data[2*i]);
      String im = Util.f4(data[2*i+1]);
      System.out.println("i="+i+"\t re="+re+"\t im="+im);
    }
    System.out.println(); // blank line separator
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
