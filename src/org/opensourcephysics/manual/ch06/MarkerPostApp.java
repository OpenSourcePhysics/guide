/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch06;
import org.opensourcephysics.display.Dataset;
import org.opensourcephysics.display.DrawingFrame;
import org.opensourcephysics.display.PlottingPanel;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * MarkerPostApp demonstrates how to use a dataset with POST markers.
 *
 * @author Wolfgang Christian
 * @version 1.0
 */
public class MarkerPostApp {
  public static void main(String[] args) {
    PlottingPanel panel = new PlottingPanel("x", "y", "Post Marker");
    DrawingFrame frame = new DrawingFrame(panel);
    panel.setSquareAspect(false);
    panel.setAutoscaleX(true);
    panel.setPreferredMinMaxY(0, 1.0);
    Dataset dataset = new Dataset();
    dataset.setMarkerShape(Dataset.POST);
    dataset.setMarkerColor(new Color(255, 128, 128, 128), new Color(255, 0, 0));
    dataset.setConnected(true);
    for(int i = 0;i<16;i++) {
      double x = (-8+i)*0.2;
      dataset.append(x, 0.5+x*Math.exp(-x*x*4.0));
    }
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
