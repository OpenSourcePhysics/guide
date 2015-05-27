/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.*;
import org.opensourcephysics.tools.ResourceLoader;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * LoadImageApp loads an image as a resource and adds it to a DrawingPanel
 * using the MeasuredImage class.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class LoadImageApp {

  /**
   * Starts the Java application.
   * @param args
   */
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("x", "y", "Load Image");
    DrawingFrame frame = new DrawingFrame(panel);
    String s = "org/opensourcephysics/manual/ch04/earthmap.gif";
    BufferedImage earth = ResourceLoader.getBufferedImage(s);
    MeasuredImage mi = new MeasuredImage(earth, -180, 180, -90, 90);
    panel.addDrawable(mi);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setSize(500, 400);
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
