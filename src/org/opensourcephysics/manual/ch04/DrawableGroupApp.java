/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.*;
import javax.swing.JFrame;

/**
 * DrawableGroupApp tests the DrawableGroup class.
 *
 * The DrawableGroup provides a convenient  way to manipulate multiple drawable objects as a single object.
 * For example, the circle and arrow show in Figure~\ref{fig:1/ball} can be combined into a drawable
 * component, translated, and rotated as shown below. Objects added to a drawable group are, of course,
 * positioned relative to the group's origin.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class DrawableGroupApp {
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("x", "y", "Drawable Group");
    DrawingFrame frame = new DrawingFrame(panel);
    DrawableGroup group = new DrawableGroup();
    group.setTheta(Math.PI/2);
    group.setXY(3, 3);
    panel.addDrawable(group);
    panel.setSquareAspect(false);
    panel.setPreferredMinMax(-15, 15, -20, 20);
    Circle circle = new Circle(0, 0); // create a circle
    group.addDrawable(circle);
    Arrow arrow = new Arrow(0, 0, 4, 3); // create an arrow
    group.addDrawable(arrow);
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
