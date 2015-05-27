/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.*;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * MarkerCircleApp demonstrates how to use a dataset with the CIRCLE marker style.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class MarkerCircleApp {

  /**
   * Starts the program.
   * @param args
   */
  public static void main(String[] args) {
    // create a drawing frame and a drawing panel
    PlottingPanel panel = new PlottingPanel("x", "y", "Square Marker");
    DrawingFrame frame = new DrawingFrame(panel);
    panel.setSquareAspect(false);
    panel.setAutoscaleX(true);
    panel.setPreferredMinMaxY(0, 1.2);
    Dataset dataset = new Dataset();
    dataset.setMarkerShape(Dataset.CIRCLE);
    dataset.setMarkerColor(new Color(255, 128, 128, 128), new Color(255, 0, 0));
    dataset.setConnected(false);
    for(int i = 0;i<40;i++) {
      double x = Math.PI*(-20+i)*0.02;
      dataset.append(x, Math.sin(x)*Math.sin(x)+0.2*Math.random());
    }
    // add the drawable objects to the drawing panel
    panel.addDrawable(dataset);
    org.opensourcephysics.display.GUIUtils.showDrawingAndTableFrames();
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
