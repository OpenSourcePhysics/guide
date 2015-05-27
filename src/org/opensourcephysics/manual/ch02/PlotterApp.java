/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch02;
import org.opensourcephysics.display.*;
import org.opensourcephysics.ejs.control.*;
import org.opensourcephysics.numerics.*;

/**
 * PlotterApp reads a string from a text field, converts this string to a function, and plots the function on the interval -10 to 10.
 *
 * @author W. Christian
 * @version 1.0
 */
public class PlotterApp {
  PlottingPanel plot = new PlottingPanel("x", "f(x)", "y = f(x)");
  Dataset dataset = new Dataset(); // (x,y) data
  EjsControl gui;                  // graphical user interface
  Function function;               // function that will be plotted

  /**
   * Constructs PlotterApp by building the interface and initializing the plot.
   */
  public PlotterApp() {
    dataset.setConnected(true);                // connect points with line segments
    dataset.setMarkerShape(Dataset.NO_MARKER); // do not mark every (x,y) point
    plot.addDrawable(dataset);                 // add the dataset to the plot for display
    buildUserInterface();                      // create the user interface
    doPlot();                                  // plot the default function
  }

  /**
   * Plots the function on the interval -10 to 10.
   */
  public void doPlot() {
    try {                         // read user input and parse the string
      function = new ParsedFunction(gui.getString("f[x]"), "x");
    } catch(ParserException ex) { // user input errors are common so handle error
      function = Util.constantFunction(0); // f(x)=0 if there is an input error
    }
    dataset.clear(); // remove the old data
    double xmin = plot.getXMin(), xmax = plot.getXMax();
    double dx = (xmax-xmin)/plot.getWidth();
    for(double x = xmin;x<=xmax;x += dx) { // loop to generate the data points
      dataset.append(x, function.evaluate(x));
    }
    plot.repaint(); // repaint the screen because we have new data points
  }

  /**
   * Builds the user interface.
   */
  void buildUserInterface() {
    gui = new EjsControl(this); // use Easy Java Simulation components to build a user interface
    gui.add("Frame", "name=controlFrame;title=Function Plotter;location=400,0;layout=border;exit=true;size=pack");
    gui.addObject(plot, "Panel", "name=contentPanel; parent=controlFrame; position=center");
    gui.add("Panel", "name=inputPanel; parent= controlFrame; layout=hbox; position=south");
    gui.add("Button", "parent=inputPanel; text=Plot; action=doPlot;");
    gui.add("TextField", "parent=inputPanel;variable=f[x]; value=sin(x); size=125,15");
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new PlotterApp(); // create the program
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
