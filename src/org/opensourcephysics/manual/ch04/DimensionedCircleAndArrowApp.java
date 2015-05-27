/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch04;
import org.opensourcephysics.display.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 * DimensionedCircleAndArrowApp tests the Dimensioned interface.
 *
 * Because not all drawable objects can be resized, we define the
 * Dimensioned interface in the display package. A dimensioned object
 * constrains the drawable area inside a drawing'panel's gutters. Unlike
 * a typical drawing which keeps its gutter size constant, a panel
 * with a dimensioned object keeps its interior constant.
 * The following code defines a class that sets the drawable area to 200 by 200 pixels.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class DimensionedCircleAndArrowApp {
  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel();
    DrawingFrame frame = new DrawingFrame(panel);
    panel.addDrawable(new DimensionedSquare());
    panel.addDrawable(new Circle(0, 0));
    panel.addDrawable(new Arrow(0, 0, 4, 3));
    org.opensourcephysics.display.GUIUtils.showDrawingAndTableFrames();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

class DimensionedSquare implements Drawable, Dimensioned {
  public void draw(DrawingPanel panel, Graphics g) {
    g.setColor(Color.lightGray);
    g.drawRect(panel.getLeftGutter(), panel.getTopGutter(), 199, 199);
  }

  public Dimension getInterior(DrawingPanel panel) {
    return new Dimension(200, 200);
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
