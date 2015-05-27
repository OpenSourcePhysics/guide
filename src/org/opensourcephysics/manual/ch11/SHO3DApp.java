/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display3d.core.interaction.*;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.numerics.*;

/**
 * SHO3DApp creates a 3d harmonic oscillator simulation by extending AbstractSimulation
 * and implementing the doStep method.
 *
 * @author W. Christian
 * @author F. Esquembre
 * @version 1.0
 */
public class SHO3DApp extends AbstractSimulation implements ODE, InteractionListener {
  // Graphical elements
  private DrawingPanel3D panel;
  private ElementSpring spring;
  private ElementCircle ball;

  // initial state values = {x, v, t}
  private double[] state = new double[] {0.0, 0.0, 0.0};
  private double k = 1;      // spring constant
  private double b = 0.2;    // damping constant
  private double length = 1; // Spring length at rest
  private double ballSize = 0.4;
  private double blockSize = 0.1;
  private ODESolver ode_solver = new RK4(this);

  /**
   * Constructs SHO3DApp.
   */
  public SHO3DApp() {
    panel = new DrawingPanel3D();
    panel.setPreferredMinMax(-length, length, -length, length, -length, length);
    panel.getCamera().setAzimuth(-Math.PI/2); // This is so that the 3D and 2D views look similar
    panel.getVisualizationHints().setAllowQuickRedraw(false); // The scene is simple, so draw it properly when rotating
    panel.getVisualizationHints().setShowCoordinates(org.opensourcephysics.display3d.core.DrawingPanel3D.TOP_LEFT);
    panel.getVisualizationHints().setYFormat(null);
    panel.getVisualizationHints().setZFormat(null);
    Element wall = new ElementBox();
    wall.setXYZ(-length-blockSize/2, 0, 0);
    wall.setSizeXYZ(blockSize, 0.8, 0.8);
    wall.getStyle().setResolution(new Resolution(1, 4, 4));
    panel.addElement(wall);
    spring = new ElementSpring();
    spring.setXYZ(-length, 0, 0);
    spring.setSizeXYZ(length+state[0]-ballSize/3, 0, 0); // size y=0.0 and size z=0.0 imply that it will remain parallel to the X axis
    spring.setRadius(0.1); // The radius of the spring (normal to its direction). Default is actually 0.1
    panel.addElement(spring);
    ball = new ElementCircle();
    ball.setXYZ(state[0], 0, 0);
    ball.setSizeXYZ(ballSize, ballSize, ballSize);
    ball.getInteractionTarget(org.opensourcephysics.display3d.core.Element.TARGET_POSITION).setEnabled(true);
    ball.addInteractionListener(this);
    panel.addElement(ball);
    panel.setMessage("Drag the ball.");
    DrawingFrame3D frame = new DrawingFrame3D();
    frame.setDrawingPanel3D(panel);
    frame.getJFrame().setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Does a simulation step.
   *
   * Render will be called automatically by the AbstractSimulation after this method completes.
    */
  protected void doStep() {
    ode_solver.step();
    ball.setX(state[0]);
    spring.setSizeX(length+state[0]-ballSize/3); // Force the spring to follow the bob
    panel.setMessage("t="+Util.f2(state[2]), 0);
  }

  // Impementation of InteractionListener
  public void interactionPerformed(InteractionEvent _event) {
    switch(_event.getID()) {
    case InteractionEvent.MOUSE_PRESSED :
      stopSimulation();
      break;
    case InteractionEvent.MOUSE_DRAGGED :
      state[0] = ball.getX();
      state[1] = 0;
      ball.setY(0);
      ball.setZ(0);
      spring.setSizeX(length+state[0]-ballSize/3); // Force the spring to follow the ball
      panel.render();
      break;
    case InteractionEvent.MOUSE_RELEASED :
      startSimulation();
      break;
    }
  }

  // Impementation of ODE
  public double[] getState() {
    return state;
  }

  public void getRate(double[] state, double[] rate) {
    rate[0] = state[1]; // dx/dt = v
    double force = -k*state[0]-b*state[1];
    rate[1] = force; // dv/dt = force
    rate[2] = 1;     // dt/dt = 1
  }

  public static void main(String[] args) {
    (new SHO3DApp()).startSimulation(); // starts simulation after it is instantiated
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
