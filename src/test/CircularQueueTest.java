package test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.CircularQueue;

/**
 * This class tests all the methods in CircularQueue test and achieves 100% coverage.
 * NOTE: We achieved 100% coverage without testing the possibility of the buffer to go in circle
 * (add element to 0th location after adding element to last location if the buffer is not full).
 * Therefore, we proved that 100% coverage can be achieved without making sure program will work 
 * correctly in all situations. In order to try to cover that circular scenario we added additional 
 * Test called TestingCircularScenario.
 * @author DZ & FA
 *
 */
public class CircularQueueTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{	
		System.out.println("Starting testing! :/");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		System.out.println("Testing Finished! :)");
	}

	/*
	@Before
	public void setUp() throws Exception 
	{
		System.out.println("Before single test");
	}

	@After
	public void tearDown() throws Exception 
	{
		System.out.println("After single test");
	}
	*/
	
	
	//--------------------------------------------- F A R E S ----------------------------------------------------------------------
	/**
	 * This test checks if default constructor is properly constructing CircularQueue object
	 * @throws Exception
	 */
	@Test
	public void testingConstructorAndGetMethods() throws Exception 
	{
		//create CircularQueue object with capacity 11 (Should not throw exception)
		CircularQueue<Object> cq = new CircularQueue<>(11);
		
		//test if constructor created CircularQueue with specified capacity
		assertEquals(11, cq.getQueueCapacity());
		
		//test if initial assertEquals size is 0
		assertEquals(0, cq.size());
		
		//test if getRemainingQueueSpace returns correct value
		assertEquals(11, cq.getRemainingQueueSpace());
		
		//test if newly created queue is full (Should be false)
		assertFalse(cq.isQueueFull());
		
		//test if newly created queue is empty (Should be true)
		assertTrue(cq.isEmpty());
	}
	
	/**
	 * This test properly creates CircularQueue object and performs typical buffer operation actions.
	 * @throws Exception
	 */
	@Test
	public void testingAddRemoveAndPeakMethods() throws Exception 
	{
		//create CircularQueue object with capacity 2 (Should not throw exception)
		CircularQueue<Object> cq = new CircularQueue<>(5);
		
		//test asserting methods (Should return True)
		assertTrue(cq.add(11));
		assertTrue(cq.offer(22));
		
		//test if getRemainingQueueSpace returns correct value
		assertEquals(3, cq.getRemainingQueueSpace());
		
		//test if element returns appropriate value
		assertEquals(11, cq.element());
		
		assertTrue(cq.add(33));
		assertTrue(cq.offer(44));
		assertTrue(cq.offer(55));
		
		//fail to add element in full queue
		assertFalse(cq.offer(99));
		
		//test queue size
		assertEquals(5, cq.size());
		
		//test if queue if empty (Should be false)
		assertFalse(cq.isEmpty());
		
		//test if queue if full (Should be true)
		assertTrue(cq.isQueueFull());
		
		//test if peek returns appropriate value (from head pointer)
		assertEquals(11, cq.peek());
		
		//test if poll return the appropriate value
		assertEquals(11, cq.poll());
		
		//test updated queue size
		assertEquals(4, cq.size());
				
		//test if queue if empty (Should be false)
		assertFalse(cq.isEmpty());
				
		//test if queue if full (Should be false)
		assertFalse(cq.isQueueFull());
		
		//test again if peek returns appropriate value to check if head is properly updated
		assertEquals(22, cq.remove());
		assertEquals(33, cq.remove());
		assertEquals(44, cq.remove());
		assertEquals(55, cq.poll());
		
		//test if queue if empty (Should be false)
		assertTrue(cq.isEmpty());
		
		//test if peek returns appropriate value (SPECIAL CASE when size = 0)
		assertNull(cq.peek());
		
		//test if peek returns appropriate value (SPECIAL CASE when size = 0)
		assertNull(cq.poll());
	}
	
	/**
	 * This test checks if toArray function properly copies buffer content into an array.
	 * @throws Exception
	 */
	@Test
	public void testingToArray() throws Exception 
	{
		//create CircularQueue object with capacity 22 (Should not throw exception)
		CircularQueue<Integer> cq = new CircularQueue<>(22);
		
		cq.add(1);
		cq.add(2);
		cq.add(3);
		cq.add(4);
		cq.add(5);
		
		//store returned array into created arrayObject
		Object array[] = cq.toArray();
		
		//check array elements
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		assertEquals(3, array[2]);
		assertEquals(4, array[3]);
		assertEquals(5, array[4]);
	}
	
	
	//----------------------------------------------------- D E N I -------------------------------------------------------
	/**
	 * This test checks if Exception is thrown when user tries to create CircularQueue object 
	 * with incorrect queue size (0 or negative number)
	 * @throws Exception
	 */
	@Test (expected = Exception.class)
	public void testingConstructorException() throws Exception 
	{
		CircularQueue<?> cq = null;
		
		//create CircularQueue object with capacity -2 (Should throw exception)
		cq = new CircularQueue<Object>(-2);
		
		//create CircularQueue object with capacity 0 (Should throw exception)
		cq = new CircularQueue<Object>(0);
	}
	
	/**
	 * This test checks if program throws NoSuchElementException if user calls poll method on empty queue
	 * @throws Exception
	 */
	@Test(expected = IllegalStateException.class)
	public void testingIllegalStateExceptionOnAdd() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		cq.add("88");
		cq.add("77");
		
		//test for NoSuchElementException when trying to add element to full queue
		cq.add("99");
	}
	
	/**
	 * This test checks if program throws NoSuchElementException if user calls element method on empty queue
	 * @throws Exception
	 */
	@Test(expected = NoSuchElementException.class)
	public void testingNoSuchElementExceptionOnElement() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		
		//test for NoSuchElementException when trying to retrieve element from empty queue
		cq.element();
	}
	
	/**
	 * This test checks if program throws NoSuchElementException if user calls element method on empty queue
	 * @throws Exception
	 */
	@Test(expected = NoSuchElementException.class)
	public void testingNoSuchElementExceptionOnRemove() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		
		//test for NoSuchElementException when trying to retrieve element from empty queue
		cq.remove();
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented addAll method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingAddAll() throws Exception 
	{
		CircularQueue<Integer> cq = new CircularQueue<>(2);
		List<Integer> collection = new ArrayList<Integer>();
		collection.add(22);
		collection.add(77);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.addAll(collection);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented containsAll method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingContainsAll() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		List<Integer> collection = new ArrayList<Integer>();
		collection.add(22);
		collection.add(77);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.containsAll(collection);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented removeAll method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingRemoveAll() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		List<Integer> collection = new ArrayList<Integer>();
		collection.add(22);
		collection.add(77);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.removeAll(collection);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented retainAll method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingRetainAll() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		List<Integer> collection = new ArrayList<Integer>();
		collection.add(22);
		collection.add(77);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.retainAll(collection);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented contains method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingContains() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.contains(2);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented iterator method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingItrator() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.iterator();
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented remove method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingRemoveObject() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		
		//test for UnsupportedOperationException when calling addAll method
		cq.remove(2);
	}
	
	/**
	 * This test checks if program throws UnsupportedOperationException if user calls 
	 * unimplemented toArray method
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testingToArrayT() throws Exception 
	{
		CircularQueue<Object> cq = new CircularQueue<>(2);
		Object array[] = null;
		
		//test for UnsupportedOperationException when calling addAll method
		cq.toArray(array);
	}
	
	
	
	
	
	/**
	 * This test properly creates CircularQueue object and checks the possibility of the buffer to go in circle
	 * (add element to 0th location after adding element to last location if the buffer is not full).
	 * @throws Exception
	 */
	@Test
	public void TestingCircularScenario() throws Exception 
	{
		//create CircularQueue object with capacity 2 (Should not throw exception)
		CircularQueue<Object> cq = new CircularQueue<>(5);
		
		//test asserting methods (Should return True)
		assertTrue(cq.add(11)); //after this action --> head = 0, tail = 1
		assertTrue(cq.offer(22)); //after this action --> head = 0, tail = 2
		
		assertEquals(11, cq.poll()); //after this action --> head = 1, tail = 2
		
		assertTrue(cq.add(33)); //after this action --> head = 1, tail = 3
		
		assertEquals(22, cq.poll()); //after this action --> head = 2, tail = 3
		
		assertTrue(cq.offer(44)); //after this action --> head = 2, tail = 4
		assertTrue(cq.offer(55)); //after this action --> head = 2, tail = 0
		assertTrue(cq.offer(66)); //after this action --> head = 2, tail = 1
		assertTrue(cq.offer(77)); //after this action --> head = 2, tail = 2

		//in this case buffer is full and it shouldn't allow adding new objects
		assertFalse(cq.offer(222)); //after this action --> head = 2, tail = 2
		
		assertEquals(33, cq.poll()); //after this action --> head = 3, tail = 2
		assertEquals(44, cq.poll()); //after this action --> head = 4, tail = 2
		assertEquals(55, cq.poll()); //after this action --> head = 0, tail = 2
		assertEquals(66, cq.poll()); //after this action --> head = 1, tail = 2
		assertEquals(77, cq.poll()); //after this action --> head = 2, tail = 2
		
		//queue should be empty now
		assertTrue(cq.isEmpty());
	}
}