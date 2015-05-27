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

/**
 * BallBoxCollision models a bouncing ball in a box using StateEvents.
 *
 * @author W. Christian and F. Esquembre
 * @version 1.0
 */
public class BallBoxCollision implements ODE, Drawable {
  static final double TOL = 0.001;
  static final double g = 9.8;                     // acceleration of gravity
  double xmin = -1, xmax = 1, ymin = 0, ymax = 2;
  double diameter = 0.2;
  DrawableShape ball = DrawableShape.createCircle(0, 0, diameter);
  DrawableShape box = DrawableShape.createRectangle(0, 0, 0, 0);
  double dt = 0.05;
  double k = 0.9;                                  // coefficient of restitution
  double[] state = new double[] {0, 1, 1.5, 0, 0}; // x,vx,y,vy,t
  // Use a solver that supports state events

  ODEBisectionEventSolver solver = new ODEBisectionEventSolver(this, RK4.class);

  /**
   * Constructs a ConstrainedBall and adds the events to the ODESolver.
   */
  public BallBoxCollision() {
    solver.addEvent(new FloorEvent());
    solver.addEvent(new LeftWallEvent());
    solver.addEvent(new RightWallEvent());
    solver.initialize(dt);
    ball.setMarkerColor(new Color(128, 128, 255), Color.BLUE);
  }

  /**
   * Sets the box to the given size.
   *
   * @param x double
   * @param y double
   * @param w double
   * @param h double
   */
  void setBoxSize(double x, double y, double w, double h) {
    this.xmin = x-w/2;
    this.xmax = x+w/2;
    this.ymin = y-h/2;
    this.ymax = y+h/2;
    box = DrawableShape.createRectangle(x, y, w+diameter, h+diameter);
  }

  /**
   * Advances the time by solving the differnetial equation.
   */
  void doStep() {
    solver.step();
  }

  /**
   * Gets the current state of the system.
   * Implementation of the ODE interface.
   *
   * @return double[]
   */
  public double[] getState() {
    return state;
  }

  /**
   * Gets the rate of the system using the given state.
   * Implementation of the ODE interface.
   *
   * The given state is usually not the current state of the system becuase ODE solvers
   * evuluate the rate at multuple points as they advance the time.
   *
   * @param state double[]
   * @param rate double[]
   */
  public void getRate(double[] state, double[] rate) {
    rate[0] = state[1];                  // d  x/dt = vx
    rate[1] = 0;                         // d vx/dt = 0
    rate[2] = state[3];                  // d  y/dt = vy
    rate[3] = (state[2]<=ymin) ? 0 : -g; // d vy/dt = -g if ball is above floor
    rate[4] = 1.0;                       // d t/dt  = 1
  }

  /**
   * Gets the enery of the ball.
   * @return double
   */
  double getEnergy() {
    return(state[2]-ymin)*g+0.5*(state[1]*state[1]+state[3]*state[3]);
  }

  /**
   * Gets the time.
   *
   * @return double
   */
  double getTime() {
    return state[4];
  }

  /**
   * Draws the ball and the box constraining the ball.
   * @param panel DrawingPanel
   * @param g Graphics
   */
  public void draw(DrawingPanel panel, Graphics g) {
    box.draw(panel, g);
    ball.setXY(state[0], state[2]);
    ball.draw(panel, g);
  }

  /**
   * Bouncing of the ball StateEvent
   */
  private class FloorEvent implements StateEvent {
    public double getTolerance() {
      return TOL;
    }

    public double evaluate(double[] state) {
      // y should be >= ymin+radius
      // vy must be negative
      return(state[3]<0) ? state[2]-ymin : TOL;
    }

    public boolean action() {
      state[3] = -k*state[3]; // make vy positive
      return false; // Complete the integration step
    }
  } // End of inner class FloorEvent

  /**
   * Left wall StateEvent
   */
  private class LeftWallEvent implements StateEvent {
    public double getTolerance() {
      return TOL;
    }

    public double evaluate(double[] state) {
      // x should be >= xmin and vx must be <0
      return(state[1]<0) ? state[0]-xmin : TOL;
    }

    public boolean action() {
      state[1] = -k*state[1]; // invert vx
      return false; // Complete the integration step
    }
  } // End of inner class LeftWallEvent

  /**
   * Right wall StateEvent
   */
  private class RightWallEvent implements StateEvent {
    public double getTolerance() {
      return TOL;
    }

    public double evaluate(double[] state) {
      // x should be >= xmax and vx must be >0
      return(state[1]>0) ? xmax-state[0] : TOL;
    }

    public boolean action() {
      state[1] = -k*state[1]; // invert vx
      return false; // Complete the integration step
    }
  } // End of inner class RightWallEvent
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
