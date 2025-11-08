import java.util.ArrayList;
import java.util.List;

public abstract class BasePhilosopher extends Thread
{
    protected final int id;
    protected final int leftFork;
    protected final int rightFork;
    protected int eatingCount = 0;
    protected List<Long> waitingTimes = new ArrayList<Long>();

    public BasePhilosopher(int id, int n)
    {
        this.id = id;
        leftFork = id;
        rightFork = (id + 1) % n;
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
