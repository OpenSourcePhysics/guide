/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch12;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.InteractiveShape;
import org.opensourcephysics.display.OSPFrame;
import javax.swing.JFrame;

/**
 *  WriteShowNestedXMLApp writes an xml document and displays the document in an xml tree view.
 *
 * @author W. Christian and D. Brown
 * @version 1.0
 */
public class WriteShowNestedXMLApp {
  public static void main(String[] args) {
    // create the XMLControl
    XMLControl xml = new XMLControlElement();
    xml.setValue("name", "Test Object");
    xml.setValue("comment", "A test of XML.");
    xml.setValue("temperature", 273.4);
    xml.setValue("number", 200);
    xml.setValue("shape", InteractiveShape.createRectangle(0.0, 1.0, 2.0, 5.0));
    xml.setValue("data array", new double[] {1, 2, 3});
    // write the xml document
    xml.write("nested_data.xml"); // save to file
    // display the xml document in a tree view
    JFrame frame = new OSPFrame(new XMLTreePanel(xml));
    frame.setSize(650, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
