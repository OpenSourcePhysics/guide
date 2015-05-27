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
import java.awt.event.*;
import java.util.*;

/**
 * NBodyApp models and arbitrary number of particles interacting through a 1/r^2 force.
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class NBodyApp extends AbstractSimulation implements InteractiveMouseHandler {
  ArrayList<NBodyParticle> particles = new ArrayList<NBodyParticle>();
  double a = 4; // viewing side length
  DisplayFrame frame = new DisplayFrame("x", "y", "Orbits");
  NBody nbody = new NBody();

  /**
   * NBodyApp constructor.
   */
  public NBodyApp() {
    frame.setPreferredMinMax(-a/2, a/2, -a/2, a/2);
    frame.addDrawable(nbody);
    frame.setSquareAspect(true);
    frame.setInteractiveMouseHandler(this);
  }

  protected void doStep() {
    nbody.advanceTime();
    saveState();
    frame.setMessage("t="+decimalFormat.format(nbody.getTime()));
  }

  /**
   * Adds a new particle.
   */
  public void initialize() {
    setState();
    frame.setMessage("t="+decimalFormat.format(nbody.getTime()));
    nbody.initialize(control.getDouble("dt"));
  }

  /**
   * Removes the masses and recalculates the state vector
   */
  public void reset() {
    frame.removeObjectsOfClass(NBodyParticle.class);
    control.setValue("x", 1);
    control.setValue("vx", 0);
    control.setValue("y", 0);
    control.setValue("vy", 0.5);
    control.setValue("m", 1);
    control.setValue("dt", 0.1);
    enableStepsPerDisplay(true);
    initialize();
    frame.setVisible(true);
  }

  public void addMass() {
    double x = control.getDouble("x");
    double vx = control.getDouble("vx");
    double y = control.getDouble("y");
    double vy = control.getDouble("vy");
    double m = control.getDouble("m");
    NBodyParticle p = new NBodyParticle(x, vx, y, vy, m);
    frame.addDrawable(p);
    initialize();
    frame.repaint();
  }

  void setState() {
    particles = frame.getDrawables(NBodyParticle.class);
    int n = particles.size();
    nbody.setNBodies(n);
    double[] state = nbody.state;
    for(int i = 0;i<n;i++) {
      NBodyParticle p = particles.get(i);
      state[4*i] = p.x;
      state[4*i+1] = p.vx;
      state[4*i+2] = p.y;
      state[4*i+3] = p.vy;
      nbody.masses[i].m = p.m;
      nbody.masses[i].clear();
    }
    nbody.updateForce();
  }

  void saveState() {
    int n = particles.size();
    if((nbody==null)||(nbody.masses==null)||(n!=nbody.masses.length)) {
      return;
    }
    double[] state = nbody.state;
    for(int i = 0;i<n;i++) {
      NBodyParticle p = particles.get(i);
      p.x = state[4*i];
      p.vx = state[4*i+1];
      p.y = state[4*i+2];
      p.vy = state[4*i+3];
    }
  }

  public void handleMouseAction(InteractivePanel panel, MouseEvent evt) {
    panel.handleMouseAction(panel, evt); // panel moves the charge
    if(panel.getMouseAction()==InteractivePanel.MOUSE_DRAGGED) {
      setState();
      panel.repaint();
    }
  }

  /**
   * Returns an XML.ObjectLoader to save and load data for this program.
   *
   * @return the object loader
   */
  public static XML.ObjectLoader getLoader() {
    return new NBodyAppLoader();
  }

  /**
   * The main method starts the Java application.
   * @param args[]  the input parameters
   */
  public static void main(String[] args) {
    OSPControl control = SimulationControl.createApp(new NBodyApp(), args);
    control.addButton("addMass", "Add Mass", "Adds a mass with the given intial conditions");
  }
}

/**
 * An XML loader for the NBodyApp
 */
class NBodyAppLoader implements XML.ObjectLoader {

  /**
   * createObject
   *
   * @param element XMLControl
   * @return Object
   */
  public Object createObject(XMLControl element) {
    Simulation model = new NBodyApp();
    SimulationControl control = new SimulationControl(model);
    control.addButton("addMass", "Add Mass", "Adds a mass with the given intial conditions");
    model.setControl(control);
    return model;
  }

  /**
   * saveObject
   *
   * @param element XMLControl
   * @param obj Object
   */
  public void saveObject(XMLControl control, Object obj) {
    NBodyApp app = (NBodyApp) obj;
    // control.setValue("drawingPanel",app.frame.getDrawingPanel());
    control.setValue("particles", app.frame.getDrawingPanel().getDrawables(NBodyParticle.class));
  }

  /**
   * loadObject
   *
   * @param element XMLControl
   * @param obj Object
   * @return Object
   */
  public Object loadObject(XMLControl control, Object obj) {
    NBodyApp app = (NBodyApp) obj;
    ArrayList<?> particles = (ArrayList<?>) control.getObject("particles");
    app.frame.removeObjectsOfClass(NBodyParticle.class);
    int n = particles.size();
    for(int i = 0;i<n;i++) {
      app.frame.addDrawable((Drawable) particles.get(i));
    }
    app.initialize();
    app.frame.repaint();
    return obj;
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
