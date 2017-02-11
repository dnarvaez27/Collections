package hashtable;

import dnarvaez27.collections.hashtable.HashTableLP;
import junit.framework.TestCase;

public class TestHashTableLP extends TestCase
{
	private HashTableLP<Integer, String> tabla;
	
	private String[ ] abc = new String[ ]
	{
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G",
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O",
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W",
			"X",
			"Y",
			"Z"
	};
	
	private void setUpEscenario1( )
	{
		tabla = new HashTableLP<>( );
	}
	
	protected void setUp( )
	{
		tabla = new HashTableLP<Integer, String>( );
		
		for( int i = 0; i < abc.length; i++ )
		{
			tabla.put( i, abc[ i ] );
		}
	}
	
	public void testPut( )
	{
		tabla.put( 0, "TEST" );
		assertEquals( "TEST", tabla.get( 0 ) );
		
		tabla.put( 0, "B" );
		assertEquals( "B", tabla.get( 0 ) );
		
		assertEquals( 26, tabla.size( ) );
		
		tabla.put( 123, "ABC" );
		assertEquals( 27, tabla.size( ) );
	}
	
	public void testSize( )
	{
		assertEquals( 26, tabla.size( ) );
		
		setUpEscenario1( );
		assertEquals( 0, tabla.size( ) );
	}
	
	public void testGet( )
	{
		for( int i = 0; i < tabla.size( ); i++ )
		{
			assertEquals( abc[ i ], tabla.get( i ) );
		}
	}
	
	public void testDelete( )
	{
		assertEquals( abc[ 1 ], tabla.delete( 1 ) );
		assertNull( tabla.delete( 123 ) );
		assertNull( tabla.get( 1 ) );
		
		assertEquals( 25, tabla.size( ) );
	}
}