/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.display.*;
import org.opensourcephysics.ejs.control.GroupControl;

/**
 * EJSSliderApp creates an EJS control containing a button and a slider.
 *
 * @author W. Christian and F. Esquembre
 * @version 1.0
 */
public class EJSSliderApp {
  DrawingPanel plottingPanel = new PlottingPanel("x", "y", "EJS Controls Test");
  GroupControl control;
  Dataset dataset = new Dataset();

  public EJSSliderApp() {
    control = new GroupControl(this);
    control.add("Frame", "name=plottingFrame;title=EJS Example;exit=true; size=300,300");
    control.addObject(plottingPanel, "Panel", "name=plottingPanel; parent=plottingFrame; position=center");
    control.add("Panel", "name=controlPanel; parent=plottingFrame; position=south; layout=hbox");
    control.add("Slider", "parent=controlPanel; minimum=-1; maximum=1;variable=x; dragaction=sliderMoved()");
    control.add("Button", "parent=controlPanel; text=Clear; action=clearPlot()");
    plottingPanel.addDrawable(dataset);
    control.update();
  }

  public void clearPlot() {
    dataset.clear();
    plottingPanel.repaint();
  }

  public void sliderMoved() {
    double x = control.getDouble("x");
    dataset.append(x, x*x);
    plottingPanel.repaint();
  }

  static public void main(String[] args) {
    new EJSSliderApp();
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
