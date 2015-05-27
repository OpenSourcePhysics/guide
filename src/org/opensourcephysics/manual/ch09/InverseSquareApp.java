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
 * InverseSquareApp models a particle orbiting under the influence of an inverse square force law.
 *
 * This program demonstrates:
 * <ol>
 *   <li>how to use the SimulationControl to run and single step a time dependent model;</li>
 *   <li>and how to test the validity of a model using a conservation law.</li>
 * </ol>
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class InverseSquareApp extends AbstractSimulation {
  DisplayFrame orbitFrame = new DisplayFrame("x", "y", "Particle Orbit");
  PlotFrame energyFrame = new PlotFrame("time", "$\\Delta$ E", "$\\Delta$ Energy");
  InverseSquare particle = new InverseSquare();
  double initialEnergy;

  /**
   * Constructs the InverseSquareApp.
   */
  public InverseSquareApp() {
    orbitFrame.addDrawable(particle);
    orbitFrame.setPreferredMinMax(-2.5, 2.5, -2.5, 2.5);
    orbitFrame.setSquareAspect(true);
    energyFrame.setConnected(true);
    energyFrame.setMarkerShape(0, Dataset.NO_MARKER);
  }

  public void stopRunning(){
    control.println("rate evals="+particle.counter);
  }

  /**
   * Steps the time.
   */
  public void doStep() {
    particle.doStep(); // advances time
    double energy = particle.getEnergy();
    energyFrame.append(0, particle.getTime(), initialEnergy-energy);
    orbitFrame.setMessage("t = "+decimalFormat.format(particle.state[4]));
    energyFrame.setMessage("E="+energy, DrawingPanel.TOP_RIGHT);
    energyFrame.setMessage("steps="+particle.counter, DrawingPanel.BOTTOM_LEFT);
  }

  /**
   * Initializes the simulation using the values in the control.
   */
  public void initialize() {
    String solverName = control.getString("ODE Solver").toLowerCase().trim();
    ODESolver odeSolver= ODESolverFactory.createODESolver(particle, solverName);
    if(odeSolver==null){
      control.println("Solver not found. Valid solvers are:");
      control.println("Adams4, Adams5, Adams6, Butcher5");
      control.println("CashKarp45, DormandPrince45, Euler, EulerRichardson");
      control.println("Fehlberg8, Huen3, Ralston2, Verlet");
      OSPLog.fine("AbstractODESolver cannot create solver of type: "+solverName);
      return;
    }
	particle.odeSolver =odeSolver;
      orbitFrame.setTitle("Particle Orbit: "+solverName);
    if(odeSolver instanceof ODEAdaptiveSolver){
      ((ODEAdaptiveSolver)odeSolver).setTolerance(control.getDouble("adaptive solver tolerance"));
    }
    particle.odeSolver.setStepSize(control.getDouble("dt"));
    double x = control.getDouble("x");
    double vx = control.getDouble("vx");
    double y = control.getDouble("y");
    double vy = control.getDouble("vy");
    particle.initialize(new double[] {x, vx, y, vy, 0});
    orbitFrame.setMessage("t = 0");
    initialEnergy = particle.getEnergy();
    energyFrame.setMessage("E="+initialEnergy, DrawingPanel.TOP_RIGHT);
    energyFrame.setMessage("steps="+particle.counter, DrawingPanel.BOTTOM_LEFT);
  }

  /**
   * Resets simulation to a predefined state.
   */
  public void reset() {
    control.setValue("ODE Solver", "CashKarp45");
    control.setValue("x", 1);
    control.setValue("vx", 0);
    control.setValue("y", 0);
    control.setValue("vy", "2*pi");
    control.setValue("dt", 0.01);
    control.setValue("adaptive solver tolerance", 1e-3);
    enableStepsPerDisplay(true);
    initialize();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new InverseSquareApp(), args);
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
