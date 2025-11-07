import java.util.ArrayList;
import java.util.List;

public abstract class BasePhilosopher extends Thread
{
    protected final long startTime;
    protected final int id;
    protected int eatingCount = 0;
    protected List<Long> waitingTimes = new ArrayList<Long>();

    public BasePhilosopher(int n)
    {
        startTime = System.currentTimeMillis();
        id = n;
    }

    public int GetEatingCount()
    {
        return eatingCount;
    }

    public List<Long> GetWaitingTimes()
    {
        return new ArrayList<>(waitingTimes);
    }

    public int GetId()
    {
        return id;
    }
}
