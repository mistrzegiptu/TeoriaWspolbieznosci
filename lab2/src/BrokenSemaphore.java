public class BrokenSemaphore implements ISemaphore
{
    private boolean _state = true;

    public BrokenSemaphore() { }

    public synchronized void P()
    {
        if(!_state)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                System.out.println("Semaphore interrupted");
            }
        }

        _state = false;
        notify();
    }

    public synchronized void V()
    {
        if(_state)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                System.out.println("Semaphore interrupted");
            }
        }

        _state = true;
        notify();
    }
}
