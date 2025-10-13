public class CountingSemaphore implements ISemaphore
{
    private int _resources;
    private final ISemaphore _mutex;
    private final ISemaphore _resourcesAvailavle;

    public CountingSemaphore(int resources)
    {
        _resources = resources;

        _mutex = new Semaphore();
        _resourcesAvailavle = new Semaphore();
    }

    public void P()
    {
        _resourcesAvailavle.P();
        _mutex.P();

        _resources--;

        if(_resources > 0)
            _mutex.V();

        _mutex.V();
    }

    public void V()
    {
        _resourcesAvailavle.P();
        _mutex.P();

        _resources++;

        if(_resources == 1)
            _resourcesAvailavle.V();

        _mutex.V();
    }

    public int GetAvailableResources()
    {
        return _resources;
    }
}
