/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;
import org.opensourcephysics.ejs.control.EjsControlFrame;

/**
 * IsingControl defines a custom control for the Ising model.
 *
 * The slider adjusts the model's temperature parameter.
 * The buttons start and stop the animation thread.
 *
 * @author W. Christian
 * @version 1.0
 */
public class IsingControl extends EjsControlFrame {
  public IsingControl(IsingApp model) {
    super(model, "name=controlFrame;title=Control Frame;size=400,400;layout=border;exit=true");
    addObject(model.drawingPanel, "Panel", "name=drawingPanel; parent=controlFrame; layout=border; position=center");
    add("Panel", "name=controlPanel; parent=controlFrame; layout=border; position=south");
    add("Panel", "name=sliderPanel; position=north; parent=controlPanel; layout=border");
    add("Slider", "position=center; parent=sliderPanel; variable=T; minimum=0; maximum=5; ticks=11; ticksFormat=0; action=sliderMoved()");
    add("Label", "position=north; parent=sliderPanel; text=Temperature; font=italic,12; foreground=blue; horizontalAlignment=center");
    add("Panel", "name=buttonPanel; position=south; parent=controlPanel; layout=flow");
    add("Button", "parent=buttonPanel; text=Run; action=startAnimation()");
    add("Button", "parent=buttonPanel; text=Stop; action=stopAnimation()");
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
