/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.controls.*;

/**
 * AnimationApp tests the Animation interface.  Edit this example to build
 * new animations.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class AnimationApp extends AbstractAnimation {

  /**
   * Initializes the animation by reading a parameter from the control.
   */
  public void initializeAnimation() {
    double x0 = control.getDouble("x0"); // read a parameter value
    control.println("initializeAnimation method invoked.");
    control.println("reading x0: "+x0);
  }

  /**
   * Stops the animation.
   *
   * Sets the animationThread to null and waits for a join.
   */
  public void stopAnimation() {
    control.println("stopAnimation method invoked");
    super.stopAnimation();
  }

  /**
   * Steps the animation.
   */
  public void stepAnimation() {
    control.println("step method invoked");
    super.stepAnimation();
  }

  /**
   * Starts the animation.
   *
   * Use this method to start a timer or a thread.
   */
  public void startAnimation() {
    control.println("startAnimation method invoked");
    super.startAnimation();
  }

  /**
   * Resets the animation to a predefined state.
   */
  public void resetAnimation() {
    control.println("resetAnimation method invoked");
    super.resetAnimation();
    control.setValue("x0", 2.0); // initialize a parameter value
  }

  /**
   * Does an animation step.
   */
  public void doStep() {
    control.println("a step");
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    AnimationControl.createApp(new AnimationApp());
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
