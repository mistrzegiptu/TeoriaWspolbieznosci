import java.util.Random;

public class NaivePhilosopher extends BasePhilosopher
{
    private NaiveTable _table;
    private final int _leftFork;
    private final int _rightFork;
    private final int _id;

    public NaivePhilosopher(NaiveTable table, int id, int n)
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

        try {
            while (true) {
                System.out.println("Philosopher " + _id + " is THINKING");
                Thread.sleep(random.nextInt(100));

                long startTime = System.currentTimeMillis();
                _table.TakeFork(_leftFork);
                System.out.println("Philosopher " + _id + " took left fork " + _leftFork);

                _table.TakeFork(_rightFork);
                long endTime = System.currentTimeMillis();
                System.out.println("Philosopher " + _id + " took right fork " + _rightFork);

                eatingCount++;
                waitingTimes.add(endTime - startTime);

                System.out.println("Philosopher " + _id + " is EATING");
                Thread.sleep(random.nextInt(100));

                _table.PlaceForkBack(_leftFork);
                _table.PlaceForkBack(_rightFork);
                System.out.println("Philosopher " + _id + " put forks back");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
