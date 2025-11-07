public class ArbiterTable extends AbstractTable
{
    private final int MAX_EATING_AT_ONCE;

    private int _currentlyEating = 0;

    public ArbiterTable(int philosophersCount)
    {
        super(philosophersCount);

        MAX_EATING_AT_ONCE = philosophersCount - 1;
    }

    public synchronized boolean TryTakingBothForks(int leftIndex, int rightIndex) throws InterruptedException {
        while (_currentlyEating >= MAX_EATING_AT_ONCE || !forks[leftIndex] || !forks[rightIndex])
        {
            wait();
        }

        forks[leftIndex] = false;
        forks[rightIndex] = false;
        _currentlyEating++;
        return true;
    }

    public synchronized void PlaceForksBack(int leftIndex, int rightIndex)
    {
        forks[leftIndex] = true;
        forks[rightIndex] = true;
        _currentlyEating--;

        notifyAll();
    }
}
