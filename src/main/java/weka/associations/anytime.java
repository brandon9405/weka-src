
package weka.associations;
import java.io.*;
import weka.associations.AbstractAssociator;

// Create a second thread by extending Thread
class NewThread extends Thread {
	String[] args;
   NewThread(String[] arg) {
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
    	AbstractAssociator.runAssociator(new Apriori(), args);
        Thread.sleep(50);
      } catch (InterruptedException e) {
         System.out.println("Child interrupted.");
      }
      System.out.println("Exiting child thread.");
   }
}

public class anytime {
   public static void main(String[] args) {
      new NewThread(args); // create a new thread
      try {
        System.out.println("Main Thread: ");
        Thread.sleep(6000);
        System.exit(0);
      } catch (InterruptedException e) {
         System.out.println("Main thread interrupted.");
      }
      System.out.println("Main thread exiting.");

   }
}