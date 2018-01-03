
public class StackArray<T> implements IStack<T>
{
    private T[] ary;
    private int count = 0;
    
    public StackArray()
    {
        ary = (T[]) new Object [0];
    }
    
    // Pushes a new value
    @Override
    public void push (T item)
    {
        T[] temp;
        temp = (T[]) new Object [getSize() + 1];
        for (int i = 0; i < getSize(); i++)
            temp[i] = ary [i];
        temp[getSize()] = item;
        
        ary = temp;
        count ++;
    }
    
    // Pops top value
    @Override
    public T pop()
    {
        if(isEmpty())
            throw new IllegalStateException("Pop Error: Stack is Empty!");
        else
        {
            T ret = ary[getSize() - 1];
            ary[getSize() - 1] = null;
            count --;
            return ret;
        }
    }
    
    // Displays top value without poping
    @Override
    public T top()
    {
        if(!isEmpty())
            return ary[getSize()-1];
        else
            throw new IllegalStateException("Top Error: Stack is Empty!");
    }
    
    // Tests if the array is empty
    @Override
    public boolean isEmpty()
    {
        if (getSize() == 0)
            return true;
        else
            return false;
    }
    
    // Returns the Array's size
    @Override
    public int getSize()
    {
        return count;
    }
    
    @Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("top->");
		if (!isEmpty())
		{
            for(int i = (ary.length - 1); i >= 0; i--)
            {
                if (ary[i] != null)
                {
                    sb.append(ary[i]);
                    if (i != 0)
                        sb.append("->");
                }
            }
		}
		return sb.toString();
	}

}