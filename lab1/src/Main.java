public class Main
{
    private static final int _iterations = 1000000;

    public static void main(String[] args) throws InterruptedException
    {
        Unsynchronized();
        Synchronized();
        ThreadBenchmark();
    }

    public static void Unsynchronized() throws InterruptedException {
        var counter = new UnsynchronizedCounter();

        var incrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++) counter.Value++;
        });

        var decrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++) counter.Value--;
        });

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println(counter.Value);
    }

    public static void Synchronized() throws InterruptedException {
        var counter = new SynchronizedCounter();

        var incrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++) counter.Increment();
        });

        var decrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++) counter.Decrement();
        });

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println(counter.GetValue());
    }

    public static void ThreadBenchmark() throws InterruptedException
    {
        int threadCounter = 0;
        var counter = new BenchmarkCounter();

        while(true)
        {
            new Thread(() ->
            {
                for(;;)
                {
                    counter.a -= counter.b;
                    counter.b += counter.a;
                }
            }).start();
            threadCounter++;
            System.out.println(threadCounter);
        }
    }
}

