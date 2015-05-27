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
 * TorqueApp models Euler's equations to show the torque on an object that is spinning about a fixed axes.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class TorqueApp extends AbstractSimulation {
  Display3DFrame frame = new Display3DFrame(" Rotation Test");
  Element body = new ElementBox();          // shows rigid body
  Element shaft = new ElementCylinder();    // shows shaft
  Element arrowOmega = new ElementArrow();  // shows angular velocity of shaft
  Element arrowL = new ElementArrow();      // shows angular momentum of body
  Element arrowTorque = new ElementArrow(); // shows torque on shaft
  Group shaftGroup = new Group();           // contains shaft and arrowOmega
  Group bodyGroup = new Group();            // contains body, arrowL, arrowTorque
  double theta = 0, omega = 0.1, dt = 0.1;
  double Ixx = 1.0, Iyy = 1.0, Izz = 2.0;   // principal moments of inertia

  public TorqueApp() {
    frame.setDecorationType(org.opensourcephysics.display3d.core.VisualizationHints.DECORATION_AXES);
    body.setSizeXYZ(1.0, 1.0, 0.1); // thin rectangle
    shaft.setSizeXYZ(0.1, 0.1, 0.8);
    arrowL.getStyle().setLineColor(java.awt.Color.MAGENTA);
    arrowTorque.getStyle().setLineColor(java.awt.Color.CYAN);
    bodyGroup.addElement(body);
    bodyGroup.addElement(arrowTorque);
    bodyGroup.addElement(arrowL);
    shaftGroup.addElement(bodyGroup);
    shaftGroup.addElement(arrowOmega);
    shaftGroup.addElement(shaft);
    frame.addElement(shaftGroup);
  }

  void computeVectors() {
    double[] omega = body.toBodyFrame(new double[] {0, 0, this.omega}); // convert omega to body frame
    double[] angularMomentum = new double[] {omega[0]*Ixx, omega[1]*Iyy, omega[2]*Izz}; // L in body frame
    double[] torque = VectorMath.cross3D(omega, angularMomentum); // torque is computed in body frame
    arrowL.setSizeXYZ(angularMomentum);
    arrowTorque.setSizeXYZ(torque);
    // position torque arrow at tip of angular momentum
    arrowTorque.setXYZ(angularMomentum);
  }

  public void initialize() {
    omega = control.getDouble("omega");
    arrowOmega.setSizeXYZ(0, 0, omega);
    double tilt = control.getDouble("tilt");
    bodyGroup.setTransformation(Matrix3DTransformation.rotationX(tilt));
    computeVectors();
  }

  public void reset() {
    control.setValue("omega", "pi/4");
    control.setValue("tilt", "pi/5");
  }

  protected void doStep() {
    theta += omega*dt;
    shaftGroup.setTransformation(Matrix3DTransformation.rotationZ(theta));
    computeVectors();
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    SimulationControl.createApp(new TorqueApp());
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
