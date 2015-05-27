/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch12;
import org.opensourcephysics.controls.XMLControl;
import org.opensourcephysics.controls.XMLControlElement;

/**
 * ReadNestedXMLApp reads structured data (objects) from an xml file.
 *
 * @author W. Christian and D. Brown
 * @version 1.0
 */
public class ReadNestedXMLApp {
  static String fileName = "nested_data.xml";

  public static void main(String[] args) {
    XMLControl xml = new XMLControlElement();
    xml.read(fileName);
    System.out.println(xml.getString("comment"));
    System.out.println(xml.getDouble("temperature"));
    System.out.println(xml.getInt("number"));
    double[] array = (double[]) xml.getObject("data array");
    for(int i = 0, n = array.length;i<n;i++) {
      System.out.println(array[i]);
    }
    System.out.println(xml.getObject("shape"));
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
