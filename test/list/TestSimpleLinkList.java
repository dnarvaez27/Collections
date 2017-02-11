package list;

import dnarvaez27.collections.list.linkedlist.SimpleLinkedList;
import junit.framework.TestCase;

public class TestSimpleLinkList extends TestCase
{
	private SimpleLinkedList<String> simpleLinkList;
	
	private void setupEscenario1( )
	{
		simpleLinkList = new SimpleLinkedList<>( );
	}
	
	private void setupEscenario2( )
	{
		simpleLinkList = new SimpleLinkedList<>( );
		simpleLinkList.add( "a" );
		simpleLinkList.add( "b" );
		simpleLinkList.add( "c" );
		simpleLinkList.add( "d" );
	}
	
	public void testRemoveAll( )
	{
		setupEscenario2( );
		assertTrue( simpleLinkList.removeAll( simpleLinkList ) );
		assertEquals( 0, simpleLinkList.size( ) );
	}
	
	public void testSize( )
	{
		setupEscenario1( );
		assertEquals( 0, simpleLinkList.size( ) );
		setupEscenario2( );
		assertEquals( 4, simpleLinkList.size( ) );
	}
	
	public void testAdd( )
	{
		setupEscenario1( );
		simpleLinkList.add( "YES" );
		assertEquals( "YES", simpleLinkList.get( 0 ) );
		
		setupEscenario2( );
		simpleLinkList.add( 4, "e" );
		assertEquals( "e", simpleLinkList.get( 4 ) );
	}
	
	public void testAddAll( )
	{
		setupEscenario1( );
		SimpleLinkedList<String> agregar = new SimpleLinkedList<>( "1", "2", "3", "4" );
		simpleLinkList.addAll( agregar );
		for( int i = 0; i < simpleLinkList.size( ); i++ )
		{
			assertEquals( agregar.get( i ), simpleLinkList.get( i ) );
		}
		
		setupEscenario2( );
		SimpleLinkedList<String> agregar2 = new SimpleLinkedList<>( "1", "2", "3", "4" );
		simpleLinkList.addAll( 2, agregar2 );
		assertEquals( agregar2.get( 0 ), simpleLinkList.get( 2 ) );
		assertEquals( agregar2.get( 3 ), simpleLinkList.get( 1 + agregar2.size( ) ) );
	}
	
	public void testClear( )
	{
		setupEscenario2( );
		simpleLinkList.clear( );
		assertEquals( 0, simpleLinkList.size( ) );
	}
	
	public void testContains( )
	{
		setupEscenario2( );
		assertFalse( simpleLinkList.contains( "hola" ) );
		assertTrue( simpleLinkList.contains( "c" ) );
	}
	
	public void testGet( )
	{
		setupEscenario2( );
		assertEquals( "c", simpleLinkList.get( 2 ) );
		assertEquals( "d", simpleLinkList.get( 3 ) );
		assertEquals( "b", simpleLinkList.get( 1 ) );
		assertEquals( "a", simpleLinkList.getFirst( ) );
		
		try
		{
			simpleLinkList.get( 20 );
			fail( );
		}
		catch( Exception e )
		{
			// Throws Exception.
		}
	}
	
	public void testIsEmpty( )
	{
		setupEscenario1( );
		assertTrue( simpleLinkList.isEmpty( ) );
	}
	
	public void testRemoveElement( )
	{
		setupEscenario2( );
		assertTrue( simpleLinkList.remove( "c" ) );
		assertFalse( simpleLinkList.contains( "c" ) );
	}
	
	public void testRemoveEnPos( )
	{
		setupEscenario2( );
		assertEquals( "c", simpleLinkList.remove( 2 ) );
		assertFalse( simpleLinkList.contains( "c" ) );
	}
	
	public void testSet( )
	{
		setupEscenario2( );
		assertEquals( "b", simpleLinkList.set( 1, "f" ) );
		assertEquals( "f", simpleLinkList.get( 1 ) );
	}
}