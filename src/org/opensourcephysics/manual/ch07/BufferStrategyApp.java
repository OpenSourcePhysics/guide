/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch07;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;

/**
 * BufferStrategyApp tests the frame rate using a Java BufferStrategy for drawing.
 *
 * @author W. Christian
 * @version 1.0
 */
public class BufferStrategyApp extends AbstractAnimation {
  DrawingPanel panel = new DrawingPanel();
  DrawingFrame frame = new DrawingFrame("Buffer Strategy Drawing", panel);
  Spiral spiral = new Spiral();
  double dtheta = 0.1;
  int counter = 0;
  long startTime = 0;

  /**
   * Constructs a BufferStrategyApp.
   */
  public BufferStrategyApp() {
    panel.setPreferredMinMax(-5, 5, -5, 5);
    panel.addDrawable(spiral);
    frame.setVisible(true);
  }

  /**
   * Starts the animation thread.
   */
  public void startAnimation() {
    panel.setIgnoreRepaint(true);
    delayTime = control.getInt("delay time (ms)");
    super.startAnimation();
    counter = 0;
    startTime = System.currentTimeMillis();
  }

  /**
   * Stops the animation thread.
   */
  public void stopAnimation() {
    float rate = counter/(float) (System.currentTimeMillis()-startTime);
    super.stopAnimation();
    panel.setIgnoreRepaint(false);
    panel.repaint();
    control.println("frames per second="+1000*rate);
    control.println("ms per frame="+(1.0/rate));
  }

  /**
   * Resets the program by setting the delay between screen redrawing.
   */
  public void resetAnimation() {
    control.setValue("delay time (ms)", 100);
    frame.createBufferStrategy(); // creates the screen buffers
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    spiral.theta += dtheta;
    frame.bufferStrategyShow();
    counter++;
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    AnimationControl.createApp(new BufferStrategyApp());
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
