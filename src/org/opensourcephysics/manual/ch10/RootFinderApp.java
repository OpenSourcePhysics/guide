/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch10;
import org.opensourcephysics.display.*;
import org.opensourcephysics.ejs.control.*;
import org.opensourcephysics.numerics.*;

/**
 * RootFinderApp plots a function and computes a root using either Newton's method or the bisection method.
 * @author W. Christian
 * @version 1.0
 */
public class RootFinderApp {
  PlottingPanel plot = new PlottingPanel("x", "f(x)", "y = f(x)");
  EjsControl gui;    // graphical user interface
  Function function; // function that will be plotted
  FunctionDrawer functionDrawer;
  Circle circle = new Circle();

  /**
   * Constructs PlotterApp by building the interface and initializing the plot.
   */
  public RootFinderApp() {
    buildUserInterface(); // create the user interface
    calculate();          // plot the default function
  }

  /**
   * Plots the function on the interval -10 to 10.
   */
  public void calculate() {
    try {                         // read user input and parse the string
      function = new ParsedFunction(gui.getString("f[x]"), "x");
    } catch(ParserException ex) { // user input errors are common so handle error
      function = Util.constantFunction(0); // f(x)=0 if there is an input error
    }
    plot.setPreferredMinMaxX(gui.getDouble("xmin"), gui.getDouble("xmax"));
    plot.clear();
    plot.removeDrawable(functionDrawer);
    functionDrawer = new FunctionDrawer(function);
    plot.addDrawable(functionDrawer);
    double root = 0;
    if(gui.getBoolean("newton")) {
      root = Root.newton(function, gui.getDouble("xstart"), 1.0e-3);
    } else {
      root = Root.bisection(function, gui.getDouble("xstart"), gui.getDouble("xstop"), 1.0e-3);
    }
    if(Double.isNaN(root)) {
      plot.setMessage("Root not found.");
    } else {
      plot.setMessage("Root="+Util.f4(root));
      circle.setXY(root, 0);
      plot.addDrawable(circle);
    }
    plot.repaint(); // repaint the screen because we have new data points
  }

  /**
   * Builds the user interface.
   */
  void buildUserInterface() {
    gui = new EjsControl(this); // use Easy Java Simulation components to build a user interface
    gui.add("Frame", "name=controlFrame;title=Find Root;location=400,0;layout=border;exit=true;visible=false");
    gui.addObject(plot, "Panel", "name=contentPanel; parent=controlFrame; position=center");
    gui.add("Panel", "name=inputPanel; parent= controlFrame; layout=vbox; position=south");
    gui.add("Panel", "name=butonPanel; parent= inputPanel; layout=flow");
    gui.add("Label", "parent=butonPanel;text=f(x)=");
    gui.add("TextField", "parent=butonPanel;variable=f[x]; value=1/tan(x)-x; size=80,20");
    gui.add("Label", "parent=butonPanel;text=x min=");
    gui.add("TextField", "parent=butonPanel;variable=xmin; value=-4; size=60,20");
    gui.add("Label", "parent=butonPanel;text=x max=");
    gui.add("TextField", "parent=butonPanel;variable=xmax; value=4; size=60,20");
    gui.add("Panel", "name=minmaxPanel; parent= inputPanel; layout=flow");
    gui.add("Button", "parent=minmaxPanel; text=Find; action=calculate;");
    gui.add("Label", "parent=minmaxPanel;text=x1=");
    gui.add("TextField", "parent=minmaxPanel;variable=xstart; value=0.1; size=40,20");
    gui.add("Label", "parent=minmaxPanel;text=x2=");
    gui.add("TextField", "parent=minmaxPanel;variable=xstop; value=2; size=40,20");
    gui.add("CheckBox", "parent=minmaxPanel;variable=newton;text=Newton/bisection;selected=true;");
    gui.getComponent("controlFrame").setSize(400, 400);
    gui.getComponent("controlFrame").setVisible(true);
  }

  /**
   * Starts the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new RootFinderApp(); // create the program
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
