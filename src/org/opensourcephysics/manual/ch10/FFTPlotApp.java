/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.display.*;
import org.opensourcephysics.numerics.FFT;

/**
 * FFTPlotApp plots the fast Fourier transform (fft) of the Heaviside step function.
 *
 * @author W. Christian
 */
public class FFTPlotApp {

  /**
   * Main method for FFTPlotApp program.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("f", "amp", null);
    DrawingFrame frame = new DrawingFrame("FFT", panel);
    ComplexDataset cdataset = new ComplexDataset();
    panel.addDrawable(cdataset);
    int numpts = 100;
    double[] data = new double[2*numpts];
    double xmin = -5, xmax = 5;
    double x = xmin, dx = (xmax-xmin)/numpts;
    for(int i = 0;i<numpts;i++) {
      data[2*i] = (x<Math.abs(2.5)) ? 1 : -1; // real
      data[2*i+1] = 0;                        // imaginary
      x += dx;
    }
    FFT fft = new FFT();
    fft.transform(data);
    fft.toNaturalOrder(data);
    cdataset.append(fft.getNaturalFreq(dx), data);
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
