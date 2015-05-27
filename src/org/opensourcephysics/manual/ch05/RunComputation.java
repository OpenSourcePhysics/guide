/*
 * Open Source Physics software is free software as described near the bottom of this code file.
 *
 * For additional information and documentation on Open Source Physics please see: 
 * <http://www.opensourcephysics.org/>
 */

package org.opensourcephysics.manual.ch05;

/**
 * RunComputation demonstrates how to start and stop a computation using a thread.
 *
 * @author W. Christian
 * @version 1.0
 */
public class RunComputation implements Runnable {
  private volatile Thread thread;

  /**
   * Starts the thread running.
   */
  public synchronized void startRunning() {
    if(thread!=null) {
      return; // thread is already running
    }
    thread = new Thread(this);
    thread.start(); // start the thread
  }

  /**
   * Stops the running thread.
   */
  public synchronized void stopRunning() {
    Thread tempThread = thread; // temporary reference
    thread = null; // signal the thread to die
    // return if thread already stopped; cannot join with current thread
    if(tempThread==null||tempThread==Thread.currentThread()) {
      return;
    }
    try {
      tempThread.interrupt(); // get out of sleep state
      tempThread.join(1000);  // wait up to one second for the thread to die
    } catch(InterruptedException e) {}
  }

  /**
   * Runs the computation using a thread.  Do not invoke this method directly.
   */
  public void run() {
    while(thread==Thread.currentThread()) {
      // add code for computation here
      System.out.println("still running");
      try {
        Thread.sleep(100);
      } catch(InterruptedException ie) {}
    }
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
