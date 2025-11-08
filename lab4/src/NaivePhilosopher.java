import java.util.Random;

public class NaivePhilosopher extends BasePhilosopher
{
    private final NaiveTable _table;
    private final int _id;

    public NaivePhilosopher(NaiveTable table, int id, int n)
    {
        super(id, n);
        _table = table;
        _id = id;
    }

    public void run()
    {
        var random = new Random();

        try {
            while (true) {
                System.out.println("Philosopher " + _id + " is THINKING");
                Thread.sleep(random.nextInt(100));

                long startTime = System.currentTimeMillis();
                _table.TakeFork(leftFork);
                System.out.println("Philosopher " + _id + " took left fork " + leftFork);

                _table.TakeFork(rightFork);
                long endTime = System.currentTimeMillis();
                System.out.println("Philosopher " + _id + " took right fork " + rightFork);

                eatingCount++;
                waitingTimes.add(endTime - startTime);

                System.out.println("Philosopher " + _id + " is EATING");
                Thread.sleep(random.nextInt(100));

                _table.PlaceForkBack(leftFork);
                _table.PlaceForkBack(rightFork);
                System.out.println("Philosopher " + _id + " put forks back");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
