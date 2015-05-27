/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch08;
import org.opensourcephysics.display.*;
import org.opensourcephysics.display2d.ByteRaster;
import java.awt.Color;

/**
 * DLA models diffusion limited aggregation using a byte raster,
 *
 * @author       Wolfgang Christian
 * @version 1.0
 */
public class DLAApp implements Runnable {
  static final int SIZE = 300;        // one half the DLA image size in pixels
  static final int HALFSIZE = SIZE/2; // one half the DLA image size in pixels
  static final int HALFSIZE2 = HALFSIZE*HALFSIZE;
  DrawingFrame drawingFrame;
  DrawingPanel drawingPanel;
  ByteRaster byteRaster;
  byte[][] data;
  Thread thread;
  int startRadius;
  byte dlaVal;
  int counter = 0;

  /**
   * Constructs the DLA.
   */
  DLAApp() {
    drawingPanel = new DrawingPanel();
    drawingFrame = new DrawingFrame(drawingPanel);
    drawingFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    byteRaster = new ByteRaster(SIZE, SIZE);
    data = new byte[SIZE][SIZE];
    data[HALFSIZE][HALFSIZE] = Byte.MAX_VALUE;
    byteRaster.setBlock(0, 0, data);
    byteRaster.setIndexedColor(0, Color.black);
    drawingPanel.addDrawable(byteRaster);
    drawingFrame.setVisible(true);
    startRadius = 3;
    do {
      dlaVal = (byte) ((255)*Math.random());
    } while(dlaVal==0);
    thread = new Thread(this);
    thread.start();
  }

  /**
   * Grows the DLA by using a random walker.
   */
  boolean growDLA() {
    boolean attached = false;
    double theta = 2*Math.PI*Math.random(); // a random angle
    int row = HALFSIZE+(int) (startRadius*Math.sin(theta));
    int col = HALFSIZE+(int) (startRadius*Math.cos(theta));
    long r2 = startRadius*startRadius;
    long maxR2 = 3*r2;
    do {
      switch((int) (4*Math.random())) {
      case 0 :
        row++;
        break;
      case 1 :
        row--;
        break;
      case 2 :
        col++;
        break;
      case 3 :
        col--;
      }
      r2 = (row-HALFSIZE)*(row-HALFSIZE)+(col-HALFSIZE)*(col-HALFSIZE);
      if(data[row+1][col]!=0) {
        attached = true;
      } else if(data[row-1][col]!=0) {
        attached = true;
      } else if(data[row][col+1]!=0) {
        attached = true;
      } else if(data[row][col-1]!=0) {
        attached = true;
      }
    } while(!attached&&r2<maxR2&&r2<HALFSIZE2);
    if(attached) {
      data[row][col] = dlaVal;
      byteRaster.setValue(row, col, dlaVal);
      startRadius = Math.max((int) Math.sqrt(r2)*2+1, startRadius);
      counter++;
      if(counter%(3*HALFSIZE)==0) { // choose a new color every so often
        do {
          dlaVal = (byte) ((255)*Math.random());
        } while(dlaVal==0);
      }
    }
    if(startRadius>=HALFSIZE) {
      thread = null; // stop the thread since we are at the edge of the raster.
    }
    return attached;
  }

  /**
   * The run method for the thread.  DO NOT invoke this method.
   */
  public void run() {
    while(Thread.currentThread()==thread) {
      try {
        if(growDLA()&counter%20==0) { // repaint the screen after every 20th particle
          drawingPanel.repaint();
          Thread.sleep(100);
        } else {
          Thread.yield();
        }
      } catch(Exception e) {}
    }
    drawingPanel.repaint();
    drawingPanel.setMessage("Done");
  }

  /**
   * Start the Java application.
   * @param args  command line parameters
   */
  public static void main(String[] args) {
    new DLAApp();
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
