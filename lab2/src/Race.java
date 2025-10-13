import java.time.Duration;
import java.time.Instant;

public class Race
{
    private static final int _iterations = 1000000;

    public static void main(String[] args) throws InterruptedException
    {
        var brokenSemaphore = new BrokenSemaphore();
        var semaphore = new Semaphore();

        Unsynchronized(brokenSemaphore);
        Unsynchronized(semaphore);
    }

    public static void Unsynchronized(ISemaphore semaphore) throws InterruptedException
    {
        var counter = new UnsynchronizedCounter();

        var incrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++)
            {
                semaphore.P();
                counter.Value++;
                semaphore.V();
            }
        });

        var decrementingThread = new Thread(() ->
        {
            for(int i = 0; i < _iterations; i++)
            {
                semaphore.P();
                counter.Value--;
                semaphore.V();
            }
        });

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println(counter.Value);
    }


}

