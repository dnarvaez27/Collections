package list;

import java.util.Iterator;

import dnarvaez27.collections.list.Queue;
import junit.framework.TestCase;

public class TestQueue extends TestCase
{
	private Queue<Integer> queue;
	
	public void setup1( )
	{
		queue = new Queue<>( );
		queue.enqueue( 1 );
		queue.enqueue( 2 );
		queue.enqueue( 3 );
	}
	
	public void setup2( )
	{
		queue = new Queue<>( );
	}
	
	public void testContains( )
	{
		setup1( );
		assertTrue( queue.contains( 2 ) );
	}
	
	public void testDequeue( )
	{
		setup1( );
		assertEquals( 1, ( int ) queue.dequeue( ) );
		assertEquals( 2, ( int ) queue.dequeue( ) );
		assertEquals( 3, ( int ) queue.dequeue( ) );
		
		setup2( );
		assertNull( queue.dequeue( ) );
	}
	
	public void testEnqueue( )
	{
		setup1( );
		queue.enqueue( 4 );
		
		int lastElem = -1;
		Iterator<Integer> it = queue.iterator( );
		while( it.hasNext( ) )
		{
			lastElem = it.next( );
		}
		assertEquals( 4, lastElem );
	}
	
	public void testIsEmpty( )
	{
		setup2( );
		assertTrue( queue.isEmpty( ) );
	}
	
	public void testPeek( )
	{
		setup1( );
		assertEquals( 1, ( int ) queue.peek( ) );
		assertEquals( 1, ( int ) queue.dequeue( ) );
	}
	
	public void testSize( )
	{
		setup1( );
		assertEquals( 3, queue.size( ) );
		
		setup2( );
		assertEquals( 0, queue.size( ) );
	}
}