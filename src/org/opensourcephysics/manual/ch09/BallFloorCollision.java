/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see:
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.display.*;
import org.opensourcephysics.numerics.*;
import java.awt.*;

public class BallFloorCollision implements ODE, Drawable {
  static final double TOL = 0.001;
  static final double g = 9.8;              // acceleration of gravity
  double[] state = new double[] {10, 0, 0}; // y,v,t
  double radius = 1, dt = 0.1;
  boolean stopAtCollision = false;
  DrawableShape box = DrawableShape.createRectangle(0, -0.5, 10, 1);
  DrawableShape ball = DrawableShape.createCircle(0, 0, 2*radius);
  // a solver that supports state events
  ODEBisectionEventSolver solver = new ODEBisectionEventSolver(this, RK4.class);

  /**
   * FloorBallCollision models a floor-ball collision using a StateEvent.
   */
  public BallFloorCollision() {
    // choose one of the following two StateEvents
     solver.addEvent(new ElasticCollision());
    //solver.addEvent(new InelasticCollision());
    solver.initialize(dt);
    ball.setMarkerColor(new Color(128, 128, 255), Color.BLUE);
  }

  /**
   * Advances the time by solving the differential equation.
   */
  void doStep() {
    solver.step();
  }

  /**
   * Gets the state array.
   * @return double[]
   */
  public double[] getState() {
    return state;
  }

  /**
   * Gets the rate array using the given state.
   *
   * @param state double[]
   * @param rate double[]
   */
  public void getRate(double[] state, double[] rate) {
    rate[0] = state[1]; // dy/dt = v
    rate[1] = -g;       // fails with inelastic collisions due to Zeno effect
    /*
    rate[1] = (state[0]-radius>0) // change the physics if ball is resting on the floor
              ? -g      // dv/dt = -g when falling
              : 0;      // dv/dt = 0 when resting
    */
    rate[2] = 1.0; // dt/dt = 1
  }

  /**
   * Draws the ball and the floor.
   *
   * @param panel DrawingPanel
   * @param g Graphics
   */
  public void draw(DrawingPanel panel, Graphics g) {
    ball.setXY(0, state[0]);
    ball.draw(panel, g);
    box.draw(panel, g);
  }

  /**
   * ElasticCollision with floor StateEvent
   */
  private class ElasticCollision implements StateEvent {
    public double getTolerance() {
      return TOL;
    }

    /**
     * The event function that determines the illegal state.
     *
     * @param state double[]
     * @return double
     */
    public double evaluate(double[] state) {
      return(state[1]<0)      // v must be negative
            ? state[0]-radius // y should be >= radius
            : TOL;            // return a legal state
    }

    public boolean action() {
      state[1] = -state[1];   // make vy positive
      stopAtCollision = true; // change this to false to conintinue
      return stopAtCollision; // stop the integration step at the collision
    }
  } // end of inner class ElasticCollision

  /**
   * InelasticCollision with floor StateEvent.
   *
   * This StateEvent fails due to "Zeno" effect.  See narrative in OSP Guide
   */

protected class InelasticCollision implements StateEvent {
    double r = 0.8; // coefficient of restitution

    public double getTolerance() {
      return TOL;
    }

    /**
     * The event function that determines the illegal state.
     *
     * @param state double[]
     * @return double
     */
    public double evaluate(double[] state) {
      return(state[1]<0)      // v must be negative
            ? state[0]-radius // y should be >= radius
            : TOL;            // return a legal state
    }

    public boolean action() {
      state[1] = -r*state[1];  // make vy positive and reduce its value
      stopAtCollision = false; // change this to true to stop
      return stopAtCollision; // stop the integration step at the collision
    }
  } // end of inner class InelasticCollision
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
