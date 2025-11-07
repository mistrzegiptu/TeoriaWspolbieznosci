import java.util.Random;

public class ArbiterPhilosopher extends BasePhilosopher
{
    private ArbiterTable _table;
    private final int _leftFork;
    private final int _rightFork;
    private final int _id;

    public ArbiterPhilosopher(ArbiterTable table, int id, int n)
    {
        super(id);
        _table = table;
        _id = id;
        _leftFork = id;
        _rightFork = (id + 1) % n;
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
                _table.TryTakingBothForks(_leftFork, _rightFork);
                long endTime = System.currentTimeMillis();
                System.out.println("Philosopher " + _id + " took both forks");

                eatingCount++;
                waitingTimes.add(endTime - startTime);

                System.out.println("Philosopher " + _id + " is EATING");
                Thread.sleep(random.nextInt(100));

                _table.PlaceForksBack(_leftFork, _rightFork);
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
