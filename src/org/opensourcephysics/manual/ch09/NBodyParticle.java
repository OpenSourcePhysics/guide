/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch09;
import org.opensourcephysics.controls.XML;
import org.opensourcephysics.controls.XMLControl;
import org.opensourcephysics.controls.XMLLoader;
import org.opensourcephysics.display.*;
import java.awt.*;

public class NBodyParticle extends InteractiveCircle {
  double x, y, vx, vy, m;
  int r = 5;

  public NBodyParticle(double x, double vx, double y, double vy, double m) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.m = m;
    r = pixRadius;
    color = new Color(255, 0, 0, 156);
  }

  public void draw(DrawingPanel panel, Graphics g) {
    super.setXY(x, y);
    pixRadius = (int) Math.max(1, Math.sqrt(m)*r);
    super.draw(panel, g);
  }

  public static XML.ObjectLoader getLoader() {
    return new OrbitParticleLoader();
  }

  /**
   * Sets the x and y coordinates.
   *
   * @param x
   * @param y
   */
  public void setXY(double x, double y) {
    this.x = x;
    this.y = y;
    super.setXY(x, y);
  }

  /**
   * A class to save and load Dataset data in an XMLControl.
   */
  private static class OrbitParticleLoader extends XMLLoader {
    public void saveObject(XMLControl control, Object obj) {
      NBodyParticle circle = (NBodyParticle) obj;
      control.setValue("x", circle.x);
      control.setValue("vx", circle.vx);
      control.setValue("y", circle.y);
      control.setValue("vy", circle.vy);
      control.setValue("m", circle.m);
      control.setValue("interaction_enabled", circle.isEnabled());
    }

    public Object createObject(XMLControl control) {
      return new NBodyParticle(0, 0, 0, 0, 0);
    }

    public Object loadObject(XMLControl control, Object obj) {
      NBodyParticle circle = (NBodyParticle) obj;
      circle.x = control.getDouble("x");
      circle.vx = control.getDouble("vx");
      circle.y = control.getDouble("y");
      circle.vy = control.getDouble("vy");
      circle.m = control.getDouble("m");
      circle.setEnabled(control.getBoolean("interaction_enabled"));
      return obj;
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
