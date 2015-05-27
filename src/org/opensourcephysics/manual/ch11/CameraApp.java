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
import java.awt.Color;

/**
 * CameraApp creates a 3D scene and sets the camera's properties.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class CameraApp extends AbstractCalculation implements InteractionListener {
  DrawingPanel3D panel = new DrawingPanel3D();
  Element ball = new ElementEllipsoid();
  Element wallR = new ElementBox();
  Element wallL = new ElementBox();

  public CameraApp() {
    ball.setXYZ(0.0, 0.0, 0.0);
    ball.setSizeXYZ(0.5, 0.5, 0.5);
    ball.getStyle().setFillColor(Color.YELLOW);
    // right wall
    wallR.setXYZ(6.0, 0.0, 0.0);
    wallR.setSizeXYZ(0.2, 4.0, 4.0);
    wallR.getStyle().setFillColor(Color.GREEN);
    // left wall
    wallL.setXYZ(-6.0, 0.0, 0.0);
    wallL.setSizeXYZ(0.2, 4.0, 4.0);
    wallL.getStyle().setFillColor(Color.GREEN);
    // Add the objects
    panel.setPreferredMinMax(-6, 6, -6, 6, -6, 6);
    panel.getInteractionTarget(org.opensourcephysics.display3d.core.DrawingPanel3D.TARGET_PANEL);
    panel.addInteractionListener(this);
    panel.addElement(ball);
    panel.addElement(wallR);
    panel.addElement(wallL);
    DrawingFrame3D frame = new DrawingFrame3D();
    frame.setDrawingPanel3D(panel);
  }

  public void reset() {
    panel.getCamera().reset();
    updateValues();
    calculate();
  }

  public void interactionPerformed(InteractionEvent _event) {
    Object source = _event.getSource();
    if(source.equals(panel)) {
      switch(_event.getID()) {
      case InteractionEvent.MOUSE_DRAGGED :
        updateValues();
        break;
      }
    }
  }

  public void updateValues() {
    Camera camera = (Camera) panel.getCamera();
    control.setValue("camera x", camera.getX());
    control.setValue("camera y", camera.getY());
    control.setValue("camera z", camera.getZ());
    control.setValue("camera focus x", camera.getFocusX());
    control.setValue("camera focus y", camera.getFocusY());
    control.setValue("camera focus z", camera.getFocusZ());
    control.setValue("camera altitude", camera.getAltitude());
    control.setValue("camera azimuth", camera.getAzimuth());
    control.setValue("screen distance", camera.getDistanceToScreen());
    control.setValue("rotation", camera.getRotation());
  }

  public void calculate() {
    double cx = control.getDouble("camera x");
    double cy = control.getDouble("camera y");
    double cz = control.getDouble("camera z");
    double fx = control.getDouble("camera focus x");
    double fy = control.getDouble("camera focus y");
    double fz = control.getDouble("camera focus z");
    double d = control.getDouble("screen distance");
    double r = control.getDouble("rotation");
    Camera camera = (Camera) panel.getCamera();
    if(cx!=camera.getX()||cy!=camera.getY()||cz!=camera.getZ()) {
      camera.setXYZ(cx, cy, cz);
      control.setValue("camera azimuth", camera.getAzimuth());
      control.setValue("camera altitude", camera.getAltitude());
    } else {
      double altitude = control.getDouble("camera altitude");
      double azimuth = control.getDouble("camera azimuth");
      camera.setAzimuthAndAltitude(azimuth, altitude);
      control.setValue("camera x", camera.getX());
      control.setValue("camera y", camera.getY());
      control.setValue("camera z", camera.getZ());
    }
    camera.setFocusXYZ(fx, fy, fz);
    camera.setDistanceToScreen(d);
    camera.setRotation(r);
    panel.render();
  }

  public static void main(String[] args) {
    CalculationControl.createApp(new CameraApp());
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
