/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;
import org.opensourcephysics.numerics.*;

/**
 * BallsInBoxApp simulates interacting (colliding) hard disks constrained within a box and falling under
 * the influence of gravity.
 *
 * @author F. Esquembre and W. Christian
 * @version 1.0
 */
public class BallsInBoxApp extends AbstractSimulation implements ODE {
  InteractiveShape balls[];
  DisplayFrame frame = new DisplayFrame("Colliding Balls");
  ODEBisectionEventSolver solver;
  int nBalls = 10;
  double radius = 0.25;
  double xmin = 0, xmax = 10, ymin = 0, ymax = 10;
  double g = -9.8;
  double mass[];
  double[] state; // x_i, vx_i, y_i, vy_i and time

  public BallsInBoxApp() {
    frame.setAutoscaleX(true);
    frame.setPreferredMinMax(xmin, xmax, ymin, ymax);
  }

  private void createSolver() {
    // Use a solver that supports state events
    solver = new ODEBisectionEventSolver(this, org.opensourcephysics.numerics.RK4.class);
    for(int i = 0;i<nBalls;i++) {
      solver.addEvent(new BounceEvent(i, BounceEvent.BOTTOM)); // Bouncing of ball #i with the floor
      solver.addEvent(new BounceEvent(i, BounceEvent.TOP)); // Bouncing of ball #i with the ceiling
      solver.addEvent(new BounceEvent(i, BounceEvent.LEFT)); // Bouncing of ball #i with left wall
      solver.addEvent(new BounceEvent(i, BounceEvent.RIGHT)); // Bouncing of ball #i with right wall
      for(int j = i+1;j<nBalls;j++) {
        solver.addEvent(new CollisionEvent(i, j));
      }
    }
  }

  public void initialize() {
    g = control.getDouble("gravity");
    frame.clearDrawables();
    mass = new double[nBalls];
    state = new double[4*nBalls+1]; // x_i, vx_i, y_i, vy_i and time
    balls = new InteractiveShape[nBalls];
    for(int i = 0;i<nBalls;i++) {
      mass[i] = 1.0;
    }
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      balls[i] = InteractiveShape.createCircle(state[j], state[j+2], 2*radius);
      frame.addDrawable(balls[i]);
    }
    createSolver();
    solver.initialize(control.getDouble("dt"));
    for(int i = 0;i<nBalls;i++) {
      mass[i] = 1.0;
    }
    int exp = control.getInt("experiment #");
    switch(exp) {
    default :
    case 0 :
      randomize();
      break;
    case 1 :
      experiment1();
      break;
    case 2 :
      experiment2();
      break;
    case 3 :
      experiment3();
      break;
    case 4 :
      experiment4();
      break;
    }
    frame.setMessage("Energy = "+getEnergy(), DrawingPanel.TOP_LEFT);
  }

  public void reset() {
    control.setValue("experiment #", 0);
    control.setValue("gravity", -9.8);
    control.setValue("dt", 0.05);
    initialize();
  }

  public void doStep() {
    solver.step();
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      balls[i].setXY(state[j], state[j+2]);
    }
    frame.setMessage("Energy = "+getEnergy(), DrawingPanel.TOP_LEFT);
  }

  public double getEnergy() {
    double ke = 0.0, pe = 0.0;
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      ke = ke+0.5*(state[j+1]*state[j+1]+state[j+3]*state[j+3]);
      pe = pe-g*state[j+2];
    }
    return ke+pe;
  }

  public double[] getState() {
    return state;
  }

  public void getRate(double[] state, double[] rate) {
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      rate[j] = state[j+1];   // dx/dt = vx
      rate[j+1] = 0;          // d vx/dt = 0 i.e. Uniform motion
      rate[j+2] = state[j+3]; // dy/dt = vy
      rate[j+3] = g;          // d vy/dt = g
    }
    rate[rate.length-1] = 1; // time
  }

  /**
   * Bounce of the ball StateEvent with the floor
   */
  private class BounceEvent implements StateEvent {
    static final int BOTTOM = 0;
    static final int TOP = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    private int index, whichSide;

    BounceEvent(int i, int side) {
      index = 4*i;
      whichSide = side;
    }

    public double getTolerance() {
      return 0.001;
    }

    public double evaluate(double[] state) {
      switch(whichSide) {
      default :
      case BOTTOM :
        return state[index+2]-ymin-radius; // y_i must be > ymin+radius
      case TOP :
        return ymax-radius-state[index+2]; // y_i must be < ymax-radius
      case LEFT :
        return state[index]-xmin-radius;   // x_i must be > xmin+radius
      case RIGHT :
        return xmax-radius-state[index];   // x_i must be < xmax-radius
      }
    }

    public boolean action() {
      switch(whichSide) {
      default :
      case TOP :  // same as following
      case BOTTOM :
        state[index+3] = -state[index+3];
        break;    // Invert vy
      case LEFT : // same as following
      case RIGHT :
        state[index+1] = -state[index+1];
        break;    // Invert vx
      }
      return false; // return exactly at the event point
    }
  } // End of inner class BounceEvent

  /**
   * Collision between balls
   */
  private class CollisionEvent implements StateEvent {
    private int b1, b2, index1, index2;
    private final double R = 2*radius*2*radius;

    CollisionEvent(int ball1, int ball2) {
      b1 = ball1;
      b2 = ball2;
      index1 = 4*ball1;
      index2 = 4*ball2;
    }

    public double getTolerance() {
      return 0.001;
    }

    public double evaluate(double[] state) {
      double deltax = state[index2]-state[index1];     // x2-x1
      double deltay = state[index2+2]-state[index1+2]; // y2 - y1
      double result = deltax*deltax+deltay*deltay-R;
      return result; //
    }

    public boolean action() {
      double deltax = state[index2]-state[index1];
      double deltay = state[index2+2]-state[index1+2];
      double distance = Math.sqrt(deltax*deltax+deltay*deltay);
      double rx = deltax/distance, ry = deltay/distance; // Unit vector joining centers
      double sx = -ry, sy = rx;                                                                            // Vector ortogonal to the previous one
      double vr1 = (state[index1+1]*rx+state[index1+3]*ry), vs1 = (state[index1+1]*sx+state[index1+3]*sy); // Projections for disk 1
      double vr2 = (state[index2+1]*rx+state[index2+3]*ry), vs2 = (state[index2+1]*sx+state[index2+3]*sy); // Projections for disk 2
      double vr1d = (2*mass[b2]*vr2+(mass[b1]-mass[b2])*vr1)/(mass[b1]+mass[b2]); // New velocity for disk 1
      double vr2d = (2*mass[b1]*vr1+(mass[b2]-mass[b1])*vr2)/(mass[b1]+mass[b2]); // New velocity for disk 2
      // Undo the projections
      state[index1+1] = vr1d*rx+vs1*sx;
      state[index1+3] = vr1d*ry+vs1*sy;
      state[index2+1] = vr2d*rx+vs2*sx;
      state[index2+3] = vr2d*ry+vs2*sy;
      return false; // return exactly at the event point
    }
  } // End of inner class CollisionEvent

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new BallsInBoxApp());
  }

  // ---------------------------------
  // Different initial configurations
  // ---------------------------------
  public void experiment1() {
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      state[j] = (xmax+xmin)/2+(nBalls-2*i-1)*radius;
      state[j+1] = 0.0; // vx_i
      state[j+2] = (ymin+ymax)*0.5;
      state[j+3] = 0.0; // vy_i
      balls[i].setXY(state[j], state[j+2]);
    }
    state[state.length-1] = 0.0; // time
    state[0] = xmin+2*radius;
    state[1] = 1.0;
    state[state.length-5] = xmax-2*radius;
    state[state.length-4] = -1.0;
  }

  public void experiment2() {
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      state[j] = xmin+radius+2*i*radius;
      state[j+1] = 0.0; // vx_i
      state[j+2] = (ymin+ymax)*0.5;
      state[j+3] = 0.0; // vy_i
      balls[i].setXY(state[j], state[j+2]);
    }
    state[state.length-1] = 0.0; // time
    state[state.length-5] = xmax-2*radius;
    state[state.length-4] = -1.0;
  }

  public void experiment3() {
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      state[j] = (xmax+xmin)/2+(nBalls-2*i-1)*radius;
      state[j+1] = 0.0; // vx_i
      state[j+2] = (ymin+ymax)*0.5;
      state[j+3] = 0.0; // vy_i
      balls[i].setXY(state[j], state[j+2]);
    }
    state[state.length-1] = 0.0; // time
    state[state.length-5] = xmax-2*radius;
    state[state.length-4] = -1.0;
  }

  public void experiment4() {
    double pos = xmin+radius*6;
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      state[j] = (xmax+xmin)/2;
      state[j+1] = 0.0; // vx_i
      state[j+2] = pos+radius;
      state[j+3] = 0.0; // vy_i
      pos += 2*radius;
      balls[i].setXY(state[j], state[j+2]);
    }
    state[state.length-1] = 0.0; // time
  }

  public void randomize() {
    for(int i = 0, j = 0;i<nBalls;i++, j += 4) {
      state[j] = xmin+radius+(xmax-xmin-2*radius)*Math.random();   // x_i
      state[j+1] = (xmax-xmin)*(Math.random()-0.5);                // vx_i
      state[j+2] = ymin+radius+(ymax-ymin-2*radius)*Math.random(); // y_i
      state[j+3] = (ymax-ymin)*(Math.random()-0.5);                // vy_i
      balls[i].setXY(state[j], state[j+2]);
    }
    state[state.length-1] = 0.0; // time
    // make sure the state is valid
    for(int i = 0;i<nBalls;i++) {
      for(int j = i+1;j<nBalls;j++) {
        double d1 = state[j*4]-state[i*4];     // x2-x1
        double d2 = state[j*4+2]-state[i*4+2]; // y2 - y1
        double result = d1*d1+d2*d2-2*radius*2*radius;
        if(result<0) {
          randomize();
          return;
        }
      }
    }
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
