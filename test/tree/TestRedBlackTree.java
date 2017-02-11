package tree;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.tree.RedBlackTree;
import junit.framework.TestCase;

public class TestRedBlackTree extends TestCase
{
	private RedBlackTree<Integer, Integer> tree;
	
	protected void setUp( )
	{
		tree = new RedBlackTree<>( );
	}
	
	public void testAdd( )
	{
		for( int i = 1; i <= 100; i++ )
		{
			tree.add( i, i * 100 );
			assertEquals( "El tamaño del arbol no es correcto. No se agregaron elementos", i, tree.size( ) );
		}
		assertEquals( "No se agrego correctamente", Integer.valueOf( 100 ), tree.get( 1 ) );
		
		int menor = -1;
		tree.add( 20, null );
		for( Entry<Integer, Integer> entry : tree.elements( ) )
		{
			assertTrue( "El arbol no se contruyo como se debería", menor <= entry.getKey( ) );
			if( entry.getKey( ) == 20 )
			{
				fail( "El elemento debió ser eliminado" );
			}
			else
			{
				assertEquals( "Los datos se agregaron erroneamente", Integer.valueOf( entry.getKey( ) * 100 ), entry.getValue( ) );
			}
			menor = entry.getKey( );
		}
		
		tree.add( 20, 123 );
		assertEquals( "No se cambio el valor de una entrada", Integer.valueOf( 123 ), tree.get( 20 ) );
		
		try
		{
			tree.add( null, 1 );
			fail( "Debió generar exception" );
		}
		catch( Exception e )
		{
			
		}
	}
	
	public void testGet( )
	{
		for( int i = 1; i <= 100; i++ )
		{
			tree.add( i, i * 100 );
		}
		
		assertEquals( "No se agrego correctamente", Integer.valueOf( 100 ), tree.get( 1 ) );
		assertEquals( "No se agrego correctamente", Integer.valueOf( 5000 ), tree.get( 50 ) );
		assertEquals( "No se agrego correctamente", Integer.valueOf( 10000 ), tree.get( 100 ) );
	}
	
	public void testDelete( )
	{
		for( int i = 1; i <= 100; i++ )
		{
			tree.add( i, i * 100 );
		}
		
		assertEquals( "El valor retornado no es correcto", Integer.valueOf( 5000 ), tree.delete( 50 ) );
		assertEquals( 99, tree.size( ) );
		assertNull( tree.delete( 123 ) );
		
		for( Entry<Integer, Integer> entry : tree.elements( ) )
		{
			if( entry.getKey( ) == 50 )
			{
				fail( "El elemento se debería haber eliminado" );
			}
		}
		tree.clear( );
		assertEquals( "El tamaño no es correcto", 0, tree.size( ) );
	}
}
