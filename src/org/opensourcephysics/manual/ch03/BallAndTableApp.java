/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch03;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.DisplayFrame;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * BallAndTableApp creates a visualization of a ball and a table using a Display\-Frame.

 * @author W. Christian
 * @version 1.0
 */
public class BallAndTableApp {
  public static void main(String[] args) {
    DisplayFrame frame = new DisplayFrame("x", "y", "Plot Frame Example");
    // DisplayFrame frame=new DisplayFrame("Plot Frame Example");
    DrawableShape circle = DrawableShape.createCircle(0.0, 5.0, 2);
    circle.setMarkerColor(new Color(128, 128, 255), Color.BLUE);
    frame.addDrawable(circle);
    DrawableShape rectangle = DrawableShape.createRectangle(-4, -8.0, 0.5, 4);
    frame.addDrawable(rectangle); // left leg
    rectangle = DrawableShape.createRectangle(4, -8.0, 0.5, 4);
    frame.addDrawable(rectangle); // right leg
    rectangle = DrawableShape.createRectangle(0.0, -5.0, 12, 2);
    frame.addDrawable(rectangle); // table top
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
