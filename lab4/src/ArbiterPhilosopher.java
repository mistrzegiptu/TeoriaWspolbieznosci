import java.util.Random;

public class ArbiterPhilosopher extends BasePhilosopher
{
    private final ArbiterTable _table;
    private final int _id;

    public ArbiterPhilosopher(ArbiterTable table, int id, int n)
    {
        super(id, n);
        _table = table;
        _id = id;
    }


    public void run()
    {
        var random = new Random();

        try
        {
            while (true)
            {
                System.out.println("Philosopher " + _id + " is THINKING");
                Thread.sleep(random.nextInt(100));

                long startTime = System.currentTimeMillis();
                _table.TryTakingBothForks(leftFork, rightFork);
                long endTime = System.currentTimeMillis();
                System.out.println("Philosopher " + _id + " took both forks");

                eatingCount++;
                waitingTimes.add(endTime - startTime);

                System.out.println("Philosopher " + _id + " is EATING");
                Thread.sleep(random.nextInt(100));

                _table.PlaceForksBack(leftFork, rightFork);
                System.out.println("Philosopher " + _id + " put forks back");
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Philosopher " + _id + " was interrupted");
        }
    }
}
