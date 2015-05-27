/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.PlottingPanel;

/**
 * PlanarNBodyApp models special cases of the gravitational few body problem with closed periodic orbits.
 *
 * @author W. Christian
 * @version 1.0
 */
public class PlanarNBodyApp extends AbstractSimulation {
  static final double sin = Math.sin(Math.PI/3);
  static final double cos = Math.cos(Math.PI/3);
  static final double v = 0.8; // inital speed
  // state= {x1, vx1, y1, vy1, x2, vx2, y2, vy2, x3, vx3, y3, vy3, t}
  // EULER_STATE places masses on a line

  static final double[] EULER_STATE = {
    0, 0, 0, 0, 1, 0, 0, v, -1, 0, 0, -v, 0
  };
  // LAGRANGE_STATE places masses on an equilateral triangle

  static final double[] LAGRANGE_STATE = {
    1, 0, 0, v, -cos, -v*sin, sin, -v*cos, -cos, v*sin, -sin, -v*cos, 0
  };
  static final double[] MONTGOMERY_STATE = {
    0.97000436, 0.93240737/2, -0.24308753, 0.86473146/2, -0.97000436, 0.93240737/2, 0.24308753, 0.86473146/2, 0, -0.93240737, 0, -0.86473146, 0
  };
  static final double[] SIMO_STATE4_1 = {
    0.1382856843618421E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, 0.5848726308148733E+00, 0.0000000000000000E+00, -0.1871935245878676E+01, -0.1570299222812179E+00, 0.0000000000000000E+00, -0.1382856843618421E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, -0.5848726308148733E+00, 0.0000000000000000E+00, 0.1871935245878676E+01, 0.1570299222812179E+00, 0.0000000000000000E+00, 0
  };
  static final double[] SIMO_STATE4_2 = {
    0.1308472291674295E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, 0.4119449647358835E+00, -0.5339857721898453E-01, -0.1090116404238875E+01, -0.4755600203079249E+00, -0.6493199205040803E+00, -0.1201675137236327E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, 0.8866948762722759E+00, -0.5339857721898299E-01, 0.1090116404238874E+01, 0.4755600203079242E+00, -0.6493199205040792E+00, 0
  };
  static final double[] SIMO_STATE4_3 = {
    0.1002314883462055E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, -0.2937902777320299E+00, -0.5286940940236307E+00, -0.1758266190939168E+00, 0.5671259540672383E+00, 0.1023613101650529E+01, 0.5507330458520999E-01, 0.0000000000000000E+00, 0.0000000000000000E+00, -0.1753435925569013E+01, -0.5286940940236340E+00, 0.1758266190939156E+00, -0.5671259540672356E+00, 0.1023613101650515E+01, 0
  };
  static final double[] SIMO_STATE4_4 = {
    0.1207710178581697E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, 0.5905411760872022E+00, -0.5305032275357562E+00, -0.3508653351422321E+01, 0.5154372421761804E-01, 0.2672327117843965E+00, -0.1467037235101761E+00, 0.0000000000000000E+00, 0.0000000000000000E+00, -0.1125006599655840E+01, -0.5305032275357644E+00, 0.3508653351422299E+01, -0.5154372421762668E-01, 0.2672327117842411E+00, 0
  };
  static final double[] SIMO_STATE5_1 = {
    0.1657666012549268E+01, 0.0000000000000000E+00, 0.0000000000000000E+00, 0.5937852779632540E+00, 0.4397750504262926E+00, -0.1822785487627255E+01, -0.1697168623196236E+00, -0.1282480056000500E+00, -0.1268608056700923E+01, -0.1271563936574900E+01, -0.2676507732581840E+00, -0.1686446333815992E+00, -0.1268608056700929E+01, 0.1271563936574898E+01, 0.2676507732581855E+00, -0.1686446333815816E+00, 0.4397750504262912E+00, 0.1822785487627257E+01, 0.1697168623196222E+00, -0.1282480056000230E+00, 0
  };
  double[] initState = MONTGOMERY_STATE;
  PlottingPanel plottingPanel = new PlottingPanel("x", "y", "N-Body Orbits");
  DrawingFrame drawingFrame = new DrawingFrame("Planar N-Body App", plottingPanel);
  PlanarNBody nbody = new PlanarNBody(initState);

  /**
   * Constructs ClassicalApp and initializes the drawing.
   */
  public PlanarNBodyApp() {
    plottingPanel.addDrawable(nbody);
    plottingPanel.setSquareAspect(true);
    drawingFrame.setSize(450, 450);
    drawingFrame.setAnimated(true);
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    for(int i = 0;i<2;i++) {
      nbody.advanceTime(); // gives smoother curves
    }
    plottingPanel.setMessage("t="+decimalFormat.format(nbody.getTime()));
  }

  /**
   * Resets the animation into a predefined state.
   */
  public void reset() {
    control.setValue("example #", 3);
    control.setValue("dt", 0.05);
    initialize();
  }

  /**
   * Initializes the animation;
   */
  public void initialize() {
    nbody.dt = control.getDouble("dt");
    int exp = control.getInt("example #");
    switch(exp) {
    case 1 :
      initState = EULER_STATE;
      drawingFrame.setTitle("Euler 3-Body State");
      break;
    case 2 :
      initState = LAGRANGE_STATE;
      drawingFrame.setTitle("Lagrange 3-Body State");
      break;
    case 3 :
      initState = MONTGOMERY_STATE;
      drawingFrame.setTitle("Montgomery 3-Body State");
      break;
    case 4 :
      initState = SIMO_STATE4_1;
      drawingFrame.setTitle("Simo 4-Body State #1");
      break;
    case 5 :
      initState = SIMO_STATE4_2;
      drawingFrame.setTitle("Simo 4-Body State #2");
      break;
    case 6 :
      initState = SIMO_STATE4_3;
      drawingFrame.setTitle("Simo 4-Body State #3");
      break;
    case 7 :
      initState = SIMO_STATE4_4;
      drawingFrame.setTitle("Simo 4-Body State #4");
      break;
    case 8 :
      initState = SIMO_STATE5_1;
      drawingFrame.setTitle("Simo54-Body State #1");
      break;
    default :
      control.println("Example number must be between 1 and 8.");
    }
    nbody.initialize(initState);
    plottingPanel.setPreferredMinMax(-1.5, 1.5, -1.5, 1.5);
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new PlanarNBodyApp(), args);
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
