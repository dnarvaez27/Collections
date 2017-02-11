package list;

import org.junit.Test;

import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;
import junit.framework.TestCase;

public class TestDoubleLInkedList extends TestCase
{
	private DoubleLinkedList<String> doubleLinkedList;
	
	private void setup1( )
	{
		doubleLinkedList = new DoubleLinkedList<>( );
	}
	
	private void setup2( )
	{
		doubleLinkedList = new DoubleLinkedList<>( );
		
		doubleLinkedList.add( "El1" );
		doubleLinkedList.add( "El2" );
		doubleLinkedList.add( "El3" );
		doubleLinkedList.add( "El4" );
		doubleLinkedList.add( "El5" );
	}
	
	private void setup3( )
	{
		doubleLinkedList = new DoubleLinkedList<>( );
		
		doubleLinkedList.add( "El1" );
		doubleLinkedList.add( "El2" );
		doubleLinkedList.add( "El3" );
		doubleLinkedList.add( "El4" );
		doubleLinkedList.add( "El5" );
		doubleLinkedList.add( "El1" );
		doubleLinkedList.add( "El1" );
	}
	
	@Test
	public void test( )
	{
		doubleLinkedList = new DoubleLinkedList<>( );
	}
	
	public void testAdd( )
	{
		setup1( );
		String elem1 = "E6";
		doubleLinkedList.add( elem1 );
		assertEquals( elem1, doubleLinkedList.get( 0 ) );
		
		setup2( );
		String elem2 = "E7";
		doubleLinkedList.add( 3, elem2 );
		assertEquals( elem2, doubleLinkedList.get( 3 ) );
	}
	
	public void testAddAll( )
	{
		setup1( );
		DoubleLinkedList<String> toAdd1 = new DoubleLinkedList<>( "E1", "E2", "E3" );
		doubleLinkedList.addAll( toAdd1 );
		for( int i = 0; i < doubleLinkedList.size( ); i++ )
		{
			assertEquals( toAdd1.get( i ), doubleLinkedList.get( i ) );
		}
		
		setup2( );
		DoubleLinkedList<String> toAdd2 = new DoubleLinkedList<>( "A1", "A2", "A3" );
		int sizeToAdd2 = toAdd2.size( );
		doubleLinkedList.addAll( 3, toAdd2 );
		assertEquals( toAdd2.get( 0 ), doubleLinkedList.get( 3 ) );
		assertEquals( toAdd2.get( sizeToAdd2 - 1 ), doubleLinkedList.get( 3 + sizeToAdd2 - 1 ) );
	}
	
	public void testClear( )
	{
		setup2( );
		doubleLinkedList.clear( );
		assertEquals( 0, doubleLinkedList.size( ) );
		try
		{
			doubleLinkedList.get( 0 );
			fail( );
		}
		catch( Exception e )
		{
			// Throws excepción. There's no elements in list
		}
	}
	
	public void testContains( )
	{
		setup2( );
		assertTrue( doubleLinkedList.contains( "El3" ) );
		assertFalse( doubleLinkedList.contains( "FALLA" ) );
	}
	
	public void testContainsAll( )
	{
		setup2( );
		DoubleLinkedList<String> toTest1 = new DoubleLinkedList<>( "El1", "El2", "El4" );
		assertTrue( doubleLinkedList.containsAll( toTest1 ) );
		
		DoubleLinkedList<String> toTest2 = new DoubleLinkedList<>( "El1", "FALLA", "El4" );
		assertFalse( doubleLinkedList.containsAll( toTest2 ) );
	}
	
	public void testGet( )
	{
		setup2( );
		assertNotNull( doubleLinkedList.get( 0 ) );
		assertEquals( "El1", doubleLinkedList.get( 0 ) );
		
		try
		{
			doubleLinkedList.get( 50 );
			fail( );
		}
		catch( Exception e )
		{
			// Throws Exception. There's no 50th Element
		}
		
		assertEquals( "El1", doubleLinkedList.getFirst( ) );
	}
	
	public void testIndexOf( )
	{
		setup2( );
		assertEquals( 3, doubleLinkedList.indexOf( "El4" ) );
		
		assertNotSame( 1, doubleLinkedList.indexOf( "El4" ) );
	}
	
	public void testIsEmpty( )
	{
		setup1( );
		assertTrue( doubleLinkedList.isEmpty( ) );
	}
	
	public void testLastIndexOf( )
	{
		setup3( );
		assertEquals( 6, doubleLinkedList.lastIndexOf( "El1" ) );
	}
	
	public void testRemoveInt( )
	{
		setup2( );
		assertEquals( "El4", doubleLinkedList.remove( 3 ) );
		assertFalse( doubleLinkedList.contains( "El4" ) );
		
		try
		{
			doubleLinkedList.remove( 50 );
			fail( );
		}
		catch( Exception e )
		{
			// Throws Exception. No 50th Element
		}
	}
	
	public void testRemoveT( )
	{
		setup2( );
		assertTrue( doubleLinkedList.remove( "El4" ) );
		assertFalse( doubleLinkedList.contains( "El4" ) );
		
		assertFalse( doubleLinkedList.remove( "FALLA" ) );
	}
	
	public void testRemoveAll( )
	{
		setup2( );
		
		DoubleLinkedList<String> toRemove = new DoubleLinkedList<>( "El1", "El3", "El4" );
		assertTrue( doubleLinkedList.removeAll( toRemove ) );
		for( int i = 0; i < toRemove.size( ); i++ )
		{
			assertFalse( doubleLinkedList.contains( toRemove.get( i ) ) );
		}
		assertFalse( doubleLinkedList.removeAll( toRemove ) );
	}
	
	public void testRemoveFirst( )
	{
		setup2( );
		assertEquals( "El1", doubleLinkedList.removeFirst( ) );
		
		setup1( );
		assertNull( doubleLinkedList.removeFirst( ) );
	}
	
	public void testRetainAll( )
	{
		setup3( );
		DoubleLinkedList<String> toRemove = new DoubleLinkedList<>( "El1", "El3" );
		assertTrue( doubleLinkedList.retainAll( toRemove ) );
		assertEquals( "El1", doubleLinkedList.get( 0 ) );
		assertEquals( "El3", doubleLinkedList.get( 1 ) );
		assertEquals( "El1", doubleLinkedList.get( 2 ) );
		assertEquals( "El1", doubleLinkedList.get( 3 ) );
	}
	
	public void testSet( )
	{
		setup2( );
		assertEquals( "El2", doubleLinkedList.set( 1, "Ne1" ) );
		assertEquals( "Ne1", doubleLinkedList.get( 1 ) );
	}
	
	public void testSize( )
	{
		setup2( );
		assertEquals( 5, doubleLinkedList.size( ) );
		
		setup1( );
		assertEquals( 0, doubleLinkedList.size( ) );
		
		setup3( );
		assertEquals( 7, doubleLinkedList.size( ) );
	}
	
	public void testSubList( )
	{
		setup3( );
		DoubleLinkedList<String> sub = doubleLinkedList.subList( 2, 5 );
		assertEquals( 3, sub.size( ) );
		
		assertEquals( "El3", sub.get( 0 ) );
		assertEquals( "El4", sub.get( 1 ) );
		assertEquals( "El5", sub.get( 2 ) );
	}
	
	public void testToArray( )
	{
		setup2( );
		Object[ ] objs = doubleLinkedList.toArray( );
		assertEquals( doubleLinkedList.size( ), objs.length );
		
		for( int i = 0; i < objs.length; i++ )
		{
			assertEquals( objs[ i ], doubleLinkedList.get( i ) );
		}
	}
}