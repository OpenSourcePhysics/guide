/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch12;
import org.opensourcephysics.controls.XML;
import org.opensourcephysics.controls.XMLControl;

/**
 * An xml loader for the ParticleApp class.
 *
 * @author W. Christian
 */
public class ParticleAppLoader implements XML.ObjectLoader {

  /**
   * Creates a new ParticleApp.
   *
   * @param control XMLControl
   * @return Object
   */
  public Object createObject(XMLControl control) {
    ParticleApp model = new ParticleApp();
    return model;
  }

  /**
   * Saves the ParticleApp's data in the xml control.
   *
   * @param control XMLControl
   * @param obj Object
   */
  public void saveObject(XMLControl control, Object obj) {
    ParticleApp model = (ParticleApp) obj;
    control.setValue("frame", model.frame);
  }

  /**
   * Loads the ParticleApp with data from the xml control.
   *
   * @param control XMLControl
   * @param obj Object
   * @return Object
   */
  public Object loadObject(XMLControl control, Object obj) {
    ParticleApp model = (ParticleApp) obj;
    XMLControl childControl = control.getChildControl("frame");
    if(childControl==null) {
      return obj;
    }
    childControl.loadObject(model.frame);
    model.calculate(); // calculate with the new values
    model.frame.repaint();
    model.frame.setVisible(true);
    return obj;
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
