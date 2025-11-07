public class StarvingTable extends AbstractTable
{
    public StarvingTable(int forkCount)
    {
        super(forkCount);
    }

    public synchronized void TakeBothForks(int leftIndex, int rightIndex) throws InterruptedException
    {
        if(!forks[leftIndex] || !forks[rightIndex])
            wait();

        forks[leftIndex] = false;
        forks[rightIndex] = false;
    }

    public synchronized void PlaceForksBack(int leftIndex, int rightIndex)
    {
        forks[leftIndex] = true;
        forks[rightIndex] = true;

        notifyAll();
    }
}
