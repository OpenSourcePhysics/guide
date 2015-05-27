/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch12;
import org.opensourcephysics.controls.*;

/**
 * A 'toy' model of an ideal gas that demonstrates how to define an XML.ObjectLoader.
 *
 * @author W. Christian
 * @version 1.0
 */
public class IdealGas {
  static final double R = 8.31; // gas constant J/mole/K
  double p, v, t;               // pressure, volume, and temperature in MKS units

  /**
   * Creates an ideal gas model containing one mole at standard temperature and pressure.
   */
  public IdealGas() {
    this(101.3e3, 22.4e-3, 273);
  }

  /**
   * Creates an ideal gas model with the given pressure, volume, and temperature.
   *
   * @param pressure double
   * @param volume double
   * @param temperature double
   */
  public IdealGas(double pressure, double volume, double temperature) {
    p = pressure;
    v = volume;
    t = temperature;
  }

  /**
   * Gets the number of moles in the gas.
   *
   * @return number of moles
   */
  public double getMoles() {
    return p*v/R/t;
  }

  /**
   *  Returns a string representation of the ideal gas.
   *
   * @return String the string
   */
  public String toString() {
    return "ideal gas model: P="+p+"  V="+v+"  T="+t;
  }

  /**
   * Sets the loader for the IdealGas class.
   */
  static {
    XML.setLoader(IdealGas.class, new XML.ObjectLoader() {
      public void saveObject(XMLControl control, Object obj) {
        IdealGas gas = (IdealGas) obj;
        control.setValue("pressure", gas.p);
        control.setValue("volume", gas.v);
        control.setValue("temperature", gas.t);
      }
      public Object createObject(XMLControl control) {
        return new IdealGas(); // creates a gas model at STP
      }
      public Object loadObject(XMLControl control, Object obj) {
        IdealGas gas = (IdealGas) obj;
        gas.p = control.getDouble("pressure");
        gas.v = control.getDouble("volume");
        gas.t = control.getDouble("temperature");
        return gas;
      }
    });
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
