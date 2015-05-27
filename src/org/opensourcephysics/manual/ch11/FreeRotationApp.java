/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display3d.simple3d.*;
import org.opensourcephysics.frames.Display3DFrame;
import org.opensourcephysics.numerics.*;

/**
 * FreeRotationApp models the torque free rotation of a spinning object.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class FreeRotationApp extends AbstractSimulation {
  Display3DFrame frame = new Display3DFrame("Torque Free Rotation");
  Element disk = new ElementCylinder();
  Element arrowOmega = new ElementArrow(); // angular velocity
  Element arrowL = new ElementArrow();     // angular momentum
  Element bodyX = new ElementArrow();      // x axis in body frame
  Element bodyY = new ElementArrow();      // y axis in body frame
  ElementTrail trailX = new ElementTrail();
  ElementTrail trailY = new ElementTrail();
  Group bodyGroup = new Group();
  double dt = 0.1;
  double q0 = 1, q1 = 0, q2 = 0, q3 = 0;
  double q00 = 1, q01 = 0, q02 = 0, q03 = 0;
  double qdot0 = 0, qdot1 = 0, qdot2 = 0, qdot3 = 0;
  double[] spaceL = new double[] {0, 0, 1};
  double Ixx = 0.5, Iyy = 0.5, Izz = 1.0;
  double vecScale = 1.0;
  Quaternion transformation = new Quaternion(1, 0, 0, 0);

  public FreeRotationApp() {
    frame.setDecorationType(org.opensourcephysics.display3d.core.VisualizationHints.DECORATION_AXES);
    disk.setSizeXYZ(1.5, 1.5, 0.1);
    bodyX.setSizeXYZ(1.5, 0, 0);
    bodyY.setSizeXYZ(0, 1.5, 0);
    disk.getStyle().setFillColor(java.awt.Color.LIGHT_GRAY);
    arrowOmega.getStyle().setLineColor(java.awt.Color.GREEN);
    arrowL.getStyle().setLineColor(java.awt.Color.RED);
    bodyX.getStyle().setLineColor(java.awt.Color.YELLOW);
    bodyY.getStyle().setLineColor(java.awt.Color.CYAN);
    // attach axes to body
    bodyGroup.addElement(disk);
    bodyGroup.addElement(bodyX);
    bodyGroup.addElement(bodyY);
    bodyGroup.addElement(trailX);
    bodyGroup.addElement(trailY);
    frame.addElement(bodyGroup);
    // components will be plotted in space frame
    frame.addElement(arrowOmega);
    frame.addElement(arrowL);
  }

  void computeVectors() {
    double[] bodyL = spaceL.clone();
    bodyGroup.toBodyFrame(bodyL); // convert angular momentum to body frame
    double[] omega = new double[] {bodyL[0]/Ixx, bodyL[1]/Iyy, bodyL[2]/Izz}; // omega in body frame
    // computes current quaternion rate of change
    qdot0 = 0.5*(-q1*omega[0]-q2*omega[1]-q3*omega[2]);
    qdot1 = 0.5*(q0*omega[0]-q3*omega[1]+q2*omega[2]);
    qdot2 = 0.5*(q3*omega[0]+q0*omega[1]-q1*omega[2]);
    qdot3 = 0.5*(-q2*omega[0]+q1*omega[1]+q0*omega[2]);
    // save the old quaternion
    q00 = q0;
    q01 = q1;
    q02 = q2;
    q03 = q3;
    bodyGroup.toSpaceFrame(omega); // convert to space frame for plotting
    arrowOmega.setSizeXYZ(vecScale*omega[0], vecScale*omega[1], vecScale*omega[2]);
    double[] xpt = new double[] {1.5, 0, 0};
    bodyGroup.toBodyFrame(xpt);
    trailX.addPoint(xpt[0], xpt[1], xpt[2]);
    double[] ypt = new double[] {0, 1.5, 0};
    bodyGroup.toBodyFrame(ypt);
    trailY.addPoint(ypt[0], ypt[1], ypt[2]);
  }

  protected void doStep() {
    // compute new values
    q0 = q00+dt*qdot0;
    q1 = q01+dt*qdot1;
    q2 = q02+dt*qdot2;
    q3 = q03+dt*qdot3;
    // renormalize new values
    double norm = Math.sqrt(q0*q0+q1*q1+q2*q2+q3*q3);
    q0 /= norm;
    q1 /= norm;
    q2 /= norm;
    q3 /= norm;
    transformation.setCoordinates(q0, q1, q2, q3);
    bodyGroup.setTransformation(transformation);
    computeVectors(); // computes qdot and update q
  }

  public void initialize() {
    trailX.clear();
    trailY.clear();
    spaceL = (double[]) control.getObject("angular momentum");
    arrowL.setSizeXYZ(vecScale*spaceL[0], vecScale*spaceL[1], vecScale*spaceL[2]);
    computeVectors();
    frame.render();
  }

  public void reset() {
    q0 = 1;
    q1 = 0;
    q2 = 0;
    q3 = 0;
    control.setValue("angular momentum", new double[] {0, 0.02, 1});
    control.setAdjustableValue("dt", 0.1);
    enableStepsPerDisplay(true);
  }

  public void startRunning() {
    dt = control.getDouble("dt");
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new FreeRotationApp());
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
