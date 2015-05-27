/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch07;
import org.opensourcephysics.display.*;
import java.awt.*;

/**
 * Spiral tests various animation techniques by drawing a spiral.
 *
 * Use DELTA to adjust the number of lines that will be drawn.
 */
public class Spiral implements Drawable {
  double theta;                     // starting angle for the spiral
  static final double DELTA = 0.05; // radius increment

  /**
   * Draws the spiral.
   *
   * @param panel DrawingPanel
   * @param g Graphics
   */
  public void draw(DrawingPanel panel, Graphics g) {
    double theta = this.theta;   // local copy for thead safety
    int x0 = panel.getWidth()/2; // spiral starts in center of panel
    int y0 = panel.getHeight()/2;
    int x1 = x0, y1 = y0;
    double r = 0, delta = DELTA;
    int max = Math.min(x0, y0);
    while(r<max) {
      int x2 = (int) (r*Math.cos(r*0.2+theta))+x0;
      int y2 = (int) (r*Math.sin(r*0.2+theta))+y0;
      g.drawLine(x1, y1, x2, y2);
      x1 = x2;
      y1 = y2;
      r += delta;
    }
    g.setColor(Color.RED);
    g.fillOval(x1-2, y1-2, 4, 4);
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
