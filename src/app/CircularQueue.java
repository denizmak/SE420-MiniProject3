package app;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author
 * 
 */
public class CircularQueue<E> implements FixedSizeQueueInterface<E> 
{
	private int capacity;
	private E dataArray[];
	private int head;
	private int tail;
	private int size;

	/**
	 * This constructor will instantiate a new circular queue of the given capacity/size.
	 * 
	 * @param maxQueueSize - This is the capacity of the circular queue.
	 * @throws Exception An exception will be thrown if an invalid capacity is passed into the method.
	 */
	public CircularQueue(int maxQueueSize) throws Exception 
	{
		super();
		if (maxQueueSize <= 0)
		{
			throw new Exception("Queue capacity invalid.");
		}
		else
		{
			this.capacity = maxQueueSize;
			clear();
		}
	}

	/**
	 * This method adds element to the end of the queue if the queue is not full. 
	 * Also, method updates size and tail variables.
	 * 
	 * @param element to be added the circular queue
	 * @return returns TRUE if element is added or IllegalStateException if queue is full and element is not added.
	 */
	@Override
	public boolean add(E arg0) 
	{	
		if (this.size >= this.capacity) throw new IllegalStateException("The queue is FULL! :(");
		
		else
		{
			this.dataArray[tail] = arg0;
			tail = (tail + 1) % this.capacity;
			
			this.size++;
			return true;
		}
	}

	/**
	 * This method adds element to the end of the queue if the queue is not full. 
	 * Also, method updates size and tail variables.
	 * 
	 * @param element to be added the circular queue
	 * @return returns TRUE if element is added or FALSE if queue is full and element is not added.
	 */
	@Override
	public boolean offer(E arg0) 
	{
		boolean retVal = false;
		
		if (this.size < this.capacity) 
		{
			this.dataArray[tail] = arg0;
			tail = (tail + 1) % this.capacity;
			
			this.size++;
			retVal = true;
		}
		
		return retVal;
	}

	/**
	 * This method returns the element from the front of the queue (first element added) without removing it.
	 * 
	 * @return first added element in queue or NULL if queue is empty.
	 */
	@Override
	public E peek() 
	{
		if (size == 0) {return null;} 
		else {return dataArray[head];}
	}
	
	/**
	 * This method returns the element from the front of the queue (first element added) without removing it.
	 * 
	 * @return first added element in queue or NoSuchElementException if queue is empty.
	 */
	@Override
	public E element() 
	{
		if (size == 0) throw new NoSuchElementException("The queue is EMPTY! :("); 
		else {return dataArray[head];}
	}

	/**
	 * This method returns and then removes the element from the front of the queue (first added element).
	 * If queue is empty NULL is returned. Also, method updates size and head variables.
	 * 
	 * @return first added element in queue or null if queue is empty
	 */
	@Override
	public E poll() 
	{
		E retVal = null;
		
		if (size > 0)
		{
			retVal = dataArray[head];
			dataArray[head] = null;
			head = (head + 1) % capacity;
			size--;
		}
		
		return retVal;
	}

	/**
	 * This method returns and then removes the element from the front of the queue (first added element).
	 * If queue is empty NoSuchElementException is thrown. Also, method updates size and head variables.
	 * 
	 * @return first added element in queue or NoSuchElementException if queue is empty
	 */
	@Override
	public E remove() 
	{		
		if (size == 0) {throw new NoSuchElementException("Circular queue is EMPTY. :(");}
		else 
		{
			E retVal = dataArray[head];
			dataArray[head] = null;
			head = (head + 1) % capacity;
			size--;
			
			return retVal;
		}
	}

	/**
	 * This method creates an array and copies all elements from circular queue to the array in order.
	 * 
	 *  @return array containing objects from circular queue in order that they were inserted
	 */
	@Override
	public Object[] toArray() 
	{
		Object retVal[] = new Object[size];

		for (int index = 0; index < size; index++) 
		{
			int myOffset = (head + index) % this.capacity;
			retVal[index] = this.dataArray[myOffset];
		}
		return retVal;
	}

	/**
	 * This method resets the queue. It creates new, empty array of objects of specified size and resets head, tail and size variables to 0.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() 
	{
		dataArray = ((E[]) new Object[capacity]);
		head = 0;
		tail = 0;
		size = 0;
	}

	/**
	 * This method returns the capacity of the created CircularQueue object
	 * 
	 * @return capacity of the created CircularQueue (int)
	 */
	@Override
	public int getQueueCapacity() {return this.capacity;}

	/**
	 * This method returns the remaining space of the created CircularQueue object.
	 * 
	 * @return remaining space of the created CircularQueue (capacity - size) 
	 */
	@Override
	public int getRemainingQueueSpace() {return this.capacity - this.size;}

	/**
	 * his method checks if CircularQueue is full and returns boolean value.
	 * 
	 * @return boolean value TRUE if queue if full, FALSE otherwise
	 */
	@Override
	public boolean isQueueFull() {return (this.size >= this.capacity);}

	/**
	 * This method checks if CircularQueue is empty and returns boolean value.
	 * 
	 * @return boolean value TRUE if queue if empty, FALSE otherwise
	 */
	@Override
	public boolean isEmpty() {return (this.size == 0);}

	/**
	 * This method returns the current size of CircularQueue.
	 * 
	 * @return current size of CircularQueue (int)
	 */
	@Override
	public int size() {return this.size;}
	

	//----------------------------------------- NOT YET SUPPORTED METHODS ---------------------------------------------------------------------
	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean contains(Object arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public Iterator<E> iterator() 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean remove(Object arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}

	/**
	 * NOT YET SUPPORTED
	 */
	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		throw new UnsupportedOperationException("Method not yet supported.");
	}
}