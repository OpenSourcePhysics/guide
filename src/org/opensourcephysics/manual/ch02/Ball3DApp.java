/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch02;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.ejs.control.EjsControl;
import org.opensourcephysics.frames.Display3DFrame;
import java.awt.Color;

/**
 * Ball3DApp creates a bouncing ball simulation by extending AbstractSimulation
 * and implementing the doStep method.
 *
 * @author W. Christian
 * @version 1.0
 */
public class Ball3DApp extends AbstractSimulation {
  EjsControl gui;
  Display3DFrame frame = new Display3DFrame("3D Ball");
  Element ball = new ElementEllipsoid();
  double time = 0, dt = 0.05;
  double vz = 0;

  /**
   * Constructs Ball3dApp and in initializes the drawing and the plot.
   */
  public Ball3DApp() {
    frame.setPreferredMinMax(-5.0, 5.0, -5.0, 5.0, 0.0, 10.0);
    ball.setZ(9);
    ball.setSizeXYZ(1, 1, 1);
    frame.addElement(ball);
    Element block = new ElementBox();
    block.setXYZ(0, 0, 0);
    block.setSizeXYZ(4, 4, 1);
    block.getStyle().setFillColor(Color.RED);
    block.getStyle().setResolution(new Resolution(5, 5, 2)); // divide the block in subblocks.
    frame.addElement(block);
    buildUserInterface();
  }

  /**
   * Does an animation step.
   */
  protected void doStep() {
    time += dt;
    double z = ball.getZ()+vz*dt-4.9*dt*dt;
    vz -= 9.8*dt;
    if(vz<0&&z<1.0) {
      vz = -vz;
    }
    ball.setZ(z);
    frame.setMessage("t="+decimalFormat.format(time));
  }

  public void set3d() {
    if(gui.getBoolean("3d")) {
      frame.setProjectionMode(org.opensourcephysics.display3d.core.Camera.MODE_PERSPECTIVE);
    } else {
      frame.setProjectionMode(org.opensourcephysics.display3d.core.Camera.MODE_PLANAR_YZ);
    }
    frame.repaint();
  }

  /**
 * Builds the user interface.
 */
  void buildUserInterface() {
    gui = new EjsControl(this); // use Easy Java Simulation components to build a user interface
    gui.addObject(frame, "Frame", "name=controlFrame;title=Bouncing Ball;location=400,0;exit=true;size=pack");
    gui.add("Panel", "name=inputPanel; parent= controlFrame; layout=hbox; position=south");
    gui.add("Button", "parent=inputPanel; text=Start; action=startAnimation;");
    gui.add("Button", "parent=inputPanel; text=Stop; action=stopAnimation;");
    gui.add("Button", "parent=inputPanel; text=Step; action=stepAnimation;");
    gui.add("CheckBox", "parent=inputPanel;variable=3d;text= 3D;selected=true;action=set3d;");
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new Ball3DApp();
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
