/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.*;

/**
 * MessageApp tests message boxes and adds a component to the glass pane.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class MessageApp {
  public static void main(String[] args) {
    // create a drawing frame and a drawing panel
    DrawingPanel panel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(panel);
    panel.setMessage("time="+0);
    panel.setMessage("energy="+0, 2); // top right
    MessagePanel messagePanel = new MessagePanel();
    panel.getGlassPanel().add(messagePanel, OSPLayout.CENTERED);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
  }
}

/**
 * A simple custom component.
 */
class MessagePanel extends javax.swing.JPanel {
  MessagePanel() {
    setPreferredSize(new java.awt.Dimension(100, 100));
    this.setBackground(java.awt.Color.RED);
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
