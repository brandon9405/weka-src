
package weka.associations;
import java.io.*;

import weka.associations.AbstractAssociator;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;

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
   public static void main(String[] args) throws Throwable {
      new NewThread(args); // create a new thread
      try {
        System.out.println("Main Thread: ");
        Thread.sleep(15000);
        
        /*String trainFileString = Utils.getOption('t', args);
        DataSource loader = new DataSource(trainFileString);
        Instances m_instances =  new Instances(loader.getDataSet());
        String numRulesString = Utils.getOption('N', args);
        int m_numRules = 0;
        if (numRulesString.length() != 0) {
			m_numRules = Integer.parseInt(numRulesString);
		}
        String serFileName = "output/"+m_instances.relationName() +"_anytime";
        FastVector[] m_allTheRules = new FastVector[6];
		StringBuffer text = new StringBuffer();
        for(int for_j=0;for_j<6;for_j++)
        	m_allTheRules[for_j] = new FastVector();
        try {
			FileInputStream fileIn = 
					new FileInputStream(serFileName+".out");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			int countRules = 0;
			while(true){
				System.out.println(countRules);
				try {
					for(int for_i=0;for_i<6;for_i++)
						m_allTheRules[for_i].removeAllElements();
					m_allTheRules = (FastVector[]) in.readObject();
					for (int i = 0; i < m_allTheRules[0].size(); i++,countRules++) {

						String outerDelim;
						String innerDelim;

						String stop;
						String implies;

						String confOpen;
						String confClose;

						outerDelim = " ";
						innerDelim = " ";

						stop = ". ";
						implies = " ==> ";

						confOpen = "    " +"<" 	+ "conf:(";
						confClose = ")" + ">";

						char odc = outerDelim.charAt(0);
						char idc = innerDelim.charAt(0);

						String n = Utils.doubleToString((double) countRules + 1,
								(int) (Math.log(m_numRules) / Math.log(10) + 1), 0);

						String premise = ((AprioriItemSet) m_allTheRules[0].elementAt(i))
								.toString(m_instances, odc, idc);
						String consequence = ((AprioriItemSet) m_allTheRules[1].elementAt(i))
								.toString(m_instances, odc, idc);

						String confidence = Utils.doubleToString(
								((Double) m_allTheRules[2].elementAt(i)).doubleValue(), 2);

						int leverageT = (int) (((Double) m_allTheRules[4].elementAt(i))
								.doubleValue() * m_instances.numInstances());

						text.append(n).append(stop);
						text.append(premise).append(implies).append(consequence);
						text.append(confOpen).append(confidence).append(confClose);
						text.append('\n');
					}
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException e) {
			;
		}
        try
        {
           FileWriter out =
           new FileWriter("output/anytime123.out",true);
           out.write(text.toString());
           out.close();
        }catch(IOException ioex)
        {
            ioex.printStackTrace();
        }*/
        System.exit(0);
      } catch (InterruptedException e) {
         System.out.println("Main thread interrupted.");
      }
      System.out.println("Main thread exiting.");

   }
}