package threadExample;

public class RunMultiThreads {
    public static void main(String[] args) {
        RunMultiThreads sample = new RunMultiThreads();
        sample.runMultiThread();
    }

    private void runMultiThread() {
        RunnableSample []runnable = new RunnableSample[5];
        ThreadSample []threads = new ThreadSample[5];
        for(int i=0;i<5;i++) {
            runnable[i] = new RunnableSample();
            threads[i] = new ThreadSample();

            new Thread(runnable[i]).start();
            threads[i].start();
        }
        System.out.println("RunMultiThreads.runMultiThread() method is ended.");
    }
}
