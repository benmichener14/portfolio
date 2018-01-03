/**
 * <p>Abstract Data Type for a Stack linear list</p>
 * <p>This interface contains all of the methods that should
 * be implemented by a Stack linear list.</p>
 * <p>Created for CSCI361 at MCLA</p>
 * 
 * @author Mark.Cohen@mcla.edu
 *
 * @param <T> the data type of the elements contained in the stack. 
 */
public interface IStack<T>
{
	/**
	 * Pushes an element onto the top of the stack. 
	 * @param item - the element to be pushed onto the stack.
	 */
	public void push(T item);
	
	/**
	 * Removes the topmost element in the stack (filo). 
	 * @return the element that was removed from the stack.
	 * @throws IllegalStateException if the stack is empty. 
	 */	
	public T pop();
	
	
	/**
	 * Retrieves the topmost element in the stack.  Unlike pop, 
	 * the element is not removed from the stack.
	 * @return the topmost element in the stack.
	 * @throws IllegalStateException if the stack is empty.
	 */
	public T top();
	
	/** 
	 * Determines if the stack contains any elements.
	 * @return true if the stack is empty, false otherwise. 
	 */
	public boolean isEmpty();
	
	/** 
	 * Determines the number of elements in the stack.
	 * @return the number of elements in the stack. 
	 */	
	public int getSize();
}
