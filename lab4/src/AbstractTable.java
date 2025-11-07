public class AbstractTable
{
    protected boolean[] forks;

    public AbstractTable(int forkCount)
    {
        forks = new boolean[forkCount];

        for(int i = 0; i < forkCount; i++)
            forks[i] = true;
    }
}
