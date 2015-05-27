/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.numerics.*;

/**
 * IntegralApp tests numerical algorithms in the Integral class.
 *
 * @author W. Christian
 * @version 1.0
 */
public class IntegralApp {
  static final double LN2 = Math.log(2);

  public static void main(String[] Args) {
    Function f = new IntegralTestFunction();
    double a = 1, b = 2;
    double tol = 1.0e-10; // double has 16 significant digits
    double area = Integral.ode(f, a, b, tol);
    System.out.println("ODE area="+area+"  err="+(area-LN2));
    System.out.println("counter="+IntegralTestFunction.c);
    IntegralTestFunction.c = 0;
    area = Integral.trapezoidal(f, a, b, 2, tol);
    System.out.println("Trapezoidal area="+area+"  err="+(area-LN2));
    System.out.println("counter="+IntegralTestFunction.c);
    IntegralTestFunction.c = 0;
    area = Integral.simpson(f, a, b, 2, tol);
    System.out.println("Simpson area="+area+"  err="+(area-LN2));
    System.out.println("counter="+IntegralTestFunction.c);
    IntegralTestFunction.c = 0;
    area = Integral.romberg(f, a, b, 2, tol);
    System.out.println("Romberg area="+area+"  err="+(area-LN2));
    System.out.println("counter="+IntegralTestFunction.c);
    IntegralTestFunction.c = 0;
  }
}

class IntegralTestFunction implements Function {
  static long c = 0;

  public double evaluate(double x) {
    c++;
    return 1.0/x;
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
