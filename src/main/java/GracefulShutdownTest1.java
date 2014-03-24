import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GracefulShutdownTest1 {
    final private int N;
    final private File f;
    static volatile boolean keepRunning = true;
    public GracefulShutdownTest1(File f, int N) { this.f=f; this.N = N; }

    public void run()
    {
        PrintWriter pw = null;
        try {
            FileOutputStream fos = new FileOutputStream(this.f);
            pw = new PrintWriter(fos);
            for (int i = 0; i < N && keepRunning; ++i){
            	System.out.println(i);
            	if(i == 4){
            		//System.exit(0);
            		Thread.currentThread().stop();
            	}
                writeBatch(pw, i);
                Thread.sleep(1000);
            }
            pw.close();
        }
        catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
        finally
        {
            pw.close();
        }       
    }

    private void writeBatch(PrintWriter pw, int i) {
        for (int j = 0; j < 10; ++j)
        {
            int k = i*100+j;
            if(keepRunning == false)
            	System.out.println("bookKeeping...");
            /*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            pw.write(Integer.toString(k));
            if ((j+1)%10 == 0)
                pw.write('\n');
            else
                pw.write(' ');
        }
    }

    static public void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("args = [file] [N] "
                    +"where file = output filename, N=batch count");
        }
        else
        {
        	final Thread mainThread = Thread.currentThread();
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                keepRunning = false;
	                System.out.println("joining...");
	                try {
	                	mainThread.join();
	                } catch (InterruptedException e) {
	                	// TODO Auto-generated catch block
	                	e.printStackTrace();
	                }
	                System.out.println("joining...");
	            }
	        });
            new GracefulShutdownTest1(
                    new File(args[0]), 
                    Integer.parseInt(args[1])
            ).run();
        }
    }
}