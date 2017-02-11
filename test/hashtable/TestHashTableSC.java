package hashtable;

import dnarvaez27.collections.hashtable.HashTableSC;
import junit.framework.TestCase;

public class TestHashTableSC extends TestCase
{
	private HashTableSC<Integer, String> tabla;
	
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
	
	protected void setUp( )
	{
		tabla = new HashTableSC<Integer, String>( );
		
		for( int i = 0; i < abc.length; i++ )
		{
			tabla.put( i, abc[ i ] );
		}
	}
	
	private void setUpEscenario1( )
	{
		tabla = new HashTableSC<Integer, String>( );
	}
	
	public void testSize( )
	{
		assertEquals( 26, tabla.size( ) );
		
		setUpEscenario1( );
		assertEquals( 0, tabla.size( ) );
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