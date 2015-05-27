/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch11;
import org.opensourcephysics.numerics.Transformation;

/**
 * Translation moves points by adding an offset to each coordinate value.
 *
 * @author W. Christian
 * @version 1.0
 */
public class Translation implements Transformation {
  double a, b, c;

  /**
   * Constructs a Translation with the given offset (a,b,c).
   *
   * @param a double x offset
   * @param b double y offset
   * @param c double z offset
   */
  public Translation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  /**
   * Clones (creates) a new Translation with this translations offsets.
   * @return Object
   */
  public Object clone() {
    return new Translation(a, b, c);
  }

  /**
   * Translates the point by adding the offsets.
   *
   * @param point double[]
   * @return double[]
   */
  public double[] direct(double[] point) { // assumes point is double[3]
    point[0] += a;
    point[1] += b;
    point[2] += c;
    return point;
  }

  /**
   * Reverses the effect of translate by subtracting the offsets.
   *
   * @param point double[]
   * @return double[]
   * @throws UnsupportedOperationException
   */
  public double[] inverse(double[] point) throws UnsupportedOperationException {
    point[0] -= a;
    point[1] -= b;
    point[2] -= c;
    return point;
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
