/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.InteractiveShape;
import java.awt.geom.PathIterator;

/**
 * PathIntegralApp uses a PathInterator to calculate the perimeter of a shape.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class PathIntegralApp {

  /**
   * Starts the PathIntegralApp program.
   *
   * @param args
   */
  public static void main(String[] args) {
    InteractiveShape circle = InteractiveShape.createCircle(-2, 2, 1);
    // get an iterator with line flatness less than 0.001
    PathIterator it = circle.getShape().getPathIterator(null, 0.001);
    double[] coord = new double[6];
    double sum = 0, x1 = 0, y1 = 0, xstart = 0, ystart = 0;
    while(!it.isDone()) {
      switch(it.currentSegment(coord)) {
      case PathIterator.SEG_LINETO :
        sum += Math.sqrt((x1-coord[0])*(x1-coord[0])+(y1-coord[1])*(y1-coord[1]));
        x1 = coord[0];
        y1 = coord[1];
        break;
      case PathIterator.SEG_MOVETO :
        x1 = coord[0];
        y1 = coord[1];
        xstart = x1; // start of the path
        ystart = y1;
        break;
      case PathIterator.SEG_CLOSE :
        sum += Math.sqrt((x1-xstart)*(x1-xstart)+(y1-ystart)*(y1-ystart));
        xstart = x1;
        ystart = y1;
        break;
      default :
        System.out.println("Segment Type not supported. Type="+it.currentSegment(coord));
      }
      it.next();
    }
    System.out.println("path integral="+sum);
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
