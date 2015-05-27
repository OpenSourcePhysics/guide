/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch14;
import org.opensourcephysics.display.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * CustomHelpApp demonstrates how to add a menu item to the Help menu.
 *
 * @author W. Christian
 * @version 1.0
 */
public class CustomHelpApp {
  DrawingFrame frame = new DrawingFrame(new DrawingPanel());

  public CustomHelpApp() {
    // custom help added to Help menu
    JMenu menu = frame.getMenu("Help");
    JMenuItem helpItem = new JMenuItem("Custom Help");
    helpItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showHelp();
      }
    });
    menu.addSeparator();
    menu.add(helpItem);
    // custom help added to pop-up menu
    JPopupMenu popup = frame.getDrawingPanel().getPopupMenu();
    helpItem = new JMenuItem("Custom Help");
    helpItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showHelp();
      }
    });
    popup.add(helpItem);
    frame.setVisible(true); // shows the DrawingFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Shows a help dialog for this program.
   */
  public void showHelp() {
    JOptionPane.showMessageDialog(frame, "This space for rent.", "Program Help", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
  */
  public static void main(String[] args) {
    new CustomHelpApp();
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
