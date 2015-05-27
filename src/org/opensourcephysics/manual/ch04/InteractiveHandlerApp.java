/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.*;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 * InteractiveHandlerApp demonstrates how to handle actions from an interactive drawing panel.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class InteractiveHandlerApp implements InteractiveMouseHandler {
  InteractivePanel panel = new PlottingPanel("x", "y", "Interactive Handler");
  DrawingFrame frame = new DrawingFrame(panel);

  /**
   * Constructs the InteractiveHandlerApp.
   *
   */
  public InteractiveHandlerApp() {
    panel.setInteractiveMouseHandler(this);
    panel.setPreferredMinMax(-10, 10, -10, 10);
    panel.addDrawable(new InteractiveCircle(0, 0));
    panel.addDrawable(new InteractiveArrow(1, 1, 3, 4));
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Handles mouse actions in the plotting panel.
   *
   * @param panel
   * @param evt
   */
  public void handleMouseAction(InteractivePanel panel, MouseEvent evt) {
    panel.handleMouseAction(panel, evt);
    switch(panel.getMouseAction()) {
    case InteractivePanel.MOUSE_DRAGGED :
      panel.setMessage("Dragged");
      break;
    case InteractivePanel.MOUSE_PRESSED :
      Interactive iad = panel.getInteractive(); // identify the interactive object
      if(iad!=null) {
        panel.setMessage("Object="+iad.getClass().toString());
      } else {
        panel.setMessage("Pressed");
      }
      break;
    case InteractivePanel.MOUSE_RELEASED :
      panel.setMessage(null);
      break;
    }
  }

  /**
   * Starts the program.
   *
   * @param args
   */
  public static void main(String[] args) {
    new InteractiveHandlerApp();
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
