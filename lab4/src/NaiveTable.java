public class NaiveTable extends AbstractTable
{
    public NaiveTable(int forkCount)
    {
        super(forkCount);
    }

    public synchronized void TakeFork(int forkId) throws InterruptedException
    {
        while(!forks[forkId])
            wait();

        forks[forkId] = false;
    }

    public synchronized void PlaceForkBack(int forkId)
    {
        forks[forkId] = true;
        notifyAll();
    }
}
