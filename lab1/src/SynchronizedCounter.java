public class SynchronizedCounter
{
    private int _value;

    public void Increment()
    {
        synchronized(this)
        {
            _value++;
        }
    }

    public void Decrement()
    {
        synchronized(this)
        {
            _value--;
        }
    }

    public int GetValue()
    {
        return _value;
    }
}
