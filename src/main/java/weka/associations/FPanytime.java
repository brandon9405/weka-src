
package weka.associations;
import java.io.*;

import weka.associations.AbstractAssociator;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

// Create a second thread by extending Thread
class NewThreadFP extends Thread {
	String[] args;
   NewThreadFP(String[] arg) {
      // Create a new, second thread
      super("Demo Thread");
      args = arg;
      System.out.println("Child thread: " + this);
      start(); // Start the thread
   }

   // This is the entry point for the second thread.
   public void run() {
      try {
            System.out.println("Child Thread: ");
        	 
			// Let the thread sleep for a while.
    	AbstractAssociator.runAssociator(new FPGrowth(), args);
        Thread.sleep(50);
      } catch (InterruptedException e) {
         System.out.println("Child interrupted.");
      }
      System.out.println("Exiting child thread.");
   }
}

public class FPanytime {
   public static void main(String[] args) throws Throwable {
      final Thread anytimeApriori = new NewThreadFP(args); // create a new thread
      try {
    	final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		        FPGrowth.keepRunning = false;
		        try {
		        	System.out.println("joining...");
		        	anytimeApriori.join();
		        	System.out.println("joined.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
    	System.out.println("Main Thread: ");
        Thread.sleep(2000);
    	System.out.println("stopping...");
        System.exit(0);
      } catch (InterruptedException e) {
         System.out.println("Main thread interrupted.");
      }
      System.out.println("Main thread exiting.");

   }
}