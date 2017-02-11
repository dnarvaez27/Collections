package list;

import java.util.Iterator;

import dnarvaez27.collections.list.Stack;
import junit.framework.TestCase;

public class TestStack extends TestCase
{
	private Stack<Integer> stack;
	
	private void setup1( )
	{
		stack = new Stack<>( );
		
		stack.push( 1 );
		stack.push( 2 );
		stack.push( 3 );
		stack.push( 4 );
	}
	
	private void setup2( )
	{
		stack = new Stack<>( );
	}
	
	public void testContains( )
	{
		setup1( );
		assertTrue( stack.contains( 3 ) );
		assertFalse( stack.contains( 40 ) );
	}
	
	public void testIsEmpty( )
	{
		setup2( );
		assertTrue( stack.isEmpty( ) );
		
		setup1( );
		assertFalse( stack.isEmpty( ) );
	}
	
	public void testPeek( )
	{
		setup1( );
		assertEquals( 4, ( int ) stack.peek( ) );
		assertEquals( 4, ( int ) stack.pop( ) );
	}
	
	public void testPop( )
	{
		setup1( );
		assertEquals( 4, ( int ) stack.pop( ) );
		
		setup2( );
		assertNull( stack.pop( ) );
	}
	
	public void testPush( )
	{
		setup2( );
		Integer[ ] toTest = new Integer[ ]
		{
				1,
				5
		};
		int contador = 0;
		stack.push( 1 );
		stack.push( 5 );
		Iterator<Integer> it = stack.iterator( );
		while( it.hasNext( ) )
		{
			assertEquals( ( int ) toTest[ toTest.length - 1 - contador++ ], ( int ) it.next( ) );
		}
	}
	
	public void testSize( )
	{
		setup1( );
		assertEquals( 4, stack.size( ) );
	}
}