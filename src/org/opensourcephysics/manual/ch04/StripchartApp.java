/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;

/**
 * StripchartApp demonstrates how to create a strip chart.
 *
 * @author W. Christian
 * @version 1.0
 */
public class StripchartApp extends AbstractAnimation {
  DrawingPanel panel = new PlottingPanel("x", "y", "Strip Chart");
  DrawingFrame frame = new DrawingFrame(panel);
  Stripchart stripchart = new Stripchart();
  double time = 0;

  /**
   * Constructs a StripchartApp.
   *
   */
  public StripchartApp() {
    panel.addDrawable(stripchart);
    panel.limitAutoscaleY(-1.2, 1.2);
    frame.setVisible(true);
  }

  /**
   * Does an animation step by adding points to the strip chart.
   */
  protected void doStep() {
    stripchart.append(time, Math.sin(time));
    time += 0.1;
    panel.repaint();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    Animation animation = new StripchartApp();
    Control control = new AnimationControl(animation);
    animation.setControl(control);
  }
}

class Stripchart extends Dataset {
  double range = 10;

  public double getXMin() {
    return xmax-range;
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
