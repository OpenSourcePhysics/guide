/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch14;
import org.opensourcephysics.display.*;
import org.opensourcephysics.ejs.control.GroupControl;
import java.awt.Color;
import javax.swing.*;

/**
 * CustomInspectorApp demonstrates how to create a custom inspector for a drawing panel.
 *
 * @author W. Christian
 * @version 1.0
 */
public class CustomInspectorApp {
  DrawingPanel panel = new DrawingPanel();
  DrawingFrame frame = new DrawingFrame(panel);
  GroupControl control; // reference to the inspector

  /**
   * Constructs CustomInspectorApp and replaces the default drawing panel inspector
   * with a custom inspector.
   */
  public CustomInspectorApp() {
    panel.setPopupMenu(null); // disable the popup on right click
    JDialog inspector = getCustomInspector();
    panel.setCustomInspector(inspector); // inspector for right click
    frame.setCustomInspector(inspector); // inspector for file menu
    frame.setVisible(true);              // shows the DrawingFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Sets the color in response to a changed in the inspector's check box.
   *
   * This method is invoked by user actions within the EJS control.
   */
  public void setColor() {
    if(control.getBoolean("isRed")) {
      panel.setBackground(Color.RED);
    } else {
      panel.setBackground(Color.BLUE);
    }
  }

  /**
   * Creates the custom inspector using EJS.
   *
   * @return JDialog
   */
  JDialog getCustomInspector() {
    control = new GroupControl(this);
    JDialog dialog = (JDialog) control.add("Dialog", "name=inspector; title=Inspector; visible=visible; location=300,300; size=100,50").getComponent();
    control.add("Panel", "name=controlPanel; position=center; parent=inspector");
    control.add("CheckBox", "parent=controlPanel; variable=isRed; text=Red; selected=false; action=setColor");
    return dialog;
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
  */
  public static void main(String[] args) {
    new CustomInspectorApp();
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
