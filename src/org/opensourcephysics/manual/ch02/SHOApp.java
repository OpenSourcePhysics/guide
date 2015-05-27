/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch02;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display.axes.XAxis;

/**
 * SHOApp creates a simple harmonic oscillator simulation by extending AbstractAnimation
 * and implementing the doStep method.
 *
 * @author W. Christian
 * @version 1.0
 */
public class SHOApp extends AbstractAnimation {
  PlottingPanel plot = new PlottingPanel("time", "x", "Simple Harmonic Oscillator");
  DrawingFrame plottingFrame = new DrawingFrame("SHO Data", plot);
  DrawingPanel drawing = new InteractivePanel();
  DrawingFrame drawingFrame = new DrawingFrame("SHO Simulation", drawing);
  Dataset stripChart = new Stripchart(20, 10); // chart of x(t)
  SHO sho = new SHO();                         // simple harmonic oscillator

  /**
   * Constructs SHOApp and in initializes the drawing and the plot.
   */
  public SHOApp() {
    drawing.setPreferredMinMax(-5, 5, -1, 1);
    drawing.addDrawable(new XAxis("Drag me!"));
    drawing.addDrawable(sho);
    drawingFrame.setSize(300, 150);
    drawingFrame.setVisible(true);
    drawingFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    plot.addDrawable(stripChart);
    plottingFrame.setLocation(400, 300);
    plottingFrame.setVisible(true);
    plottingFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    sho.stepTime();
    stripChart.append(sho.getTime(), sho.getX());
    drawing.setMessage("t="+decimalFormat.format(sho.getTime()));
    drawing.repaint();
    plot.repaint();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new SHOApp().startAnimation();
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
