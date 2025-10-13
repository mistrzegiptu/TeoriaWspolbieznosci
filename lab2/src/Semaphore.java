public class Semaphore implements ISemaphore
{
    private boolean _state = true;

    public synchronized void P()
    {
        while(!_state)
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
        while(_state)
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
