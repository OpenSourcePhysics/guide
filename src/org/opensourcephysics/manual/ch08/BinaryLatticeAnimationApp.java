/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.controls.Animation;
import org.opensourcephysics.controls.AnimationControl;
import org.opensourcephysics.controls.Control;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display2d.BinaryLattice;

/**
 * BinaryLatticeAnimationApp test a binary lattice by rapidly assigning random cell values using
 * an animation thread.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class BinaryLatticeAnimationApp implements Animation, Runnable {
  int nx = 32;
  int ny = 32;
  Control myControl; // the model's control
  Thread animationThread;

  // create the frame, the panel, and the lattice
  DrawingPanel drawingPanel = new DrawingPanel();
  DrawingFrame frame = new DrawingFrame(drawingPanel);
  BinaryLattice lattice = new BinaryLattice(ny, nx);

  /**
   * AnimationTestApp constructor.
   */
  public BinaryLatticeAnimationApp() {
    drawingPanel.addDrawable(lattice);
    drawingPanel.setSquareAspect(true);
    frame.setVisible(true);
  }

  /**
   * Sets the Control for this model and initializes the control's values.
   *
   * @param control
   */
  public void setControl(Control control) {
    myControl = control;
    resetAnimation();
  }

  /**
   * Initializes the animation by reading parameters from the control.
   */
  public void initializeAnimation() {
    if(animationThread!=null) {
      stopAnimation(); // the animation is running sto stop it first
    }
  }

  /**
   * Stops the calculation.
   *
   * Sets the animationThread to null and waits for a join.
   */
  public void stopAnimation() {
    Thread tempThread = animationThread; // temporary reference
    animationThread = null; // signal the animation to stop
    if(tempThread!=null) {
      try {
        tempThread.interrupt(); // get out of the sleep state
        tempThread.join();
      } catch(InterruptedException e) {}
    }
  }

  /**
   * Steps the animation.
   */
  public void stepAnimation() {
    if(animationThread!=null) {
      stopAnimation();
    }
    step();
  }

  void step() {
    for(int count = 0;count<nx*ny;count++) {
      int i = (int) (nx*Math.random());
      int j = (int) (ny*Math.random());
      int val = (int) (2*Math.random());
      lattice.setValue(j, i, val);
    }
    drawingPanel.repaint();
  }

  /**
   * Starts the animation.
   *
   * Use this method to start a timer or a thread.
   */
  public void startAnimation() {
    myControl.println("startAnimation method invoked.");
    if(animationThread!=null) {
      return; // the animation is running
    }
    animationThread = new Thread(this);
    animationThread.start(); // start the animation
  }

  /**
   * Resets the animation to a predefined state.
   */
  public void resetAnimation() {
    if(animationThread!=null) {
      stopAnimation(); // the animation is running sto stop it first
    }
    myControl.clearMessages();
  }

  /**
   * Implementation of Runnable interface.  DO NOT access this method directly.
   */
  public void run() {
    while(animationThread==Thread.currentThread()) {
      myControl.println("running.");
      try {
        step();
        Thread.sleep(100);
      } catch(InterruptedException ie) {}
    }
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    Animation animation = new BinaryLatticeAnimationApp();
    Control control = new AnimationControl(animation);
    animation.setControl(control);
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
