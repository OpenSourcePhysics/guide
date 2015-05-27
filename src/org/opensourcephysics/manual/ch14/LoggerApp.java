/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch14;
import java.util.logging.Level;

import org.opensourcephysics.controls.OSPLog;

/**
 * LoggerApp demonstrates the use of the OSPLog class.
 *
 * @author W. Christian
 * @version 1.0
 */
public class LoggerApp {

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    OSPLog.getOSPLog().setVisible(true); // displays the output window
    OSPLog.setLevel(Level.ALL);
    OSPLog.severe("Test SEVERE.");
    OSPLog.warning("Test WARNING.");
    OSPLog.info("Test INFO.");
    System.err.println("Test ERR_CONSOLE.");
    System.out.println("Test OUT_CONSOLE.");
    OSPLog.config("Test CONFIG.");
    OSPLog.fine("Test FINE.");
    OSPLog.finer("Test FINER.");
    OSPLog.finest("Test FINEST.");
    // Caution: program will keep running after Dialog window is closed
    // cannot set JFrame to EXIT_ON_CLOSE because OSPLog is a dialog.
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
