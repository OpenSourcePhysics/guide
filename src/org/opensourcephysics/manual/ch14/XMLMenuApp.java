/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch14;
import org.opensourcephysics.display.*;

/**
 * XMLMenuApp adds a menu to a frame's menu bar using data in an XML document.
 *
 * @author W. Christian
 * @version 1.0
 */
public class XMLMenuApp {

  /**
   * Constructs XMLMenuApp by creating a frame and adding a menu.
   */
  public XMLMenuApp() {
    DrawingFrame frame = new DrawingFrame("XML Menus", null);
    // "custom_menu.xml" is an xml file that was created using LaunchBuilder.
    // parseXMLMenu uses the ResourceLoader so that we can do the following.
    frame.parseXMLMenu("custom_menu.xml", XMLMenuApp.class);
    frame.setSize(300, 150);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Starts the Java applicaiton.
   * @param args String[] command line parameters
   */
  public static void main(String[] args) {
    new XMLMenuApp();
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
