package heap;

import java.util.Random;

import dnarvaez27.collections.heap.HeapArray;
import junit.framework.TestCase;

public class TestHeapArray extends TestCase
{
	private HeapArray<Integer> heapArray;
	
	private final Random r = new Random( );
	
	protected void setUp( )
	{
		heapArray = new HeapArray<>( );
	}
	
	public void testAdd( )
	{
		int numMay = 0;
		int mayor = 0;
		int cont = 0;
		
		for( int i = 0; i < 100; i++ )
		{
			int numRand = r.nextInt( 200 );
			numMay = Math.max( numMay, numRand );
			heapArray.add( numRand );
			assertEquals( "No se agrego el elemento", ++cont, heapArray.size( ) );
		}
		mayor = heapArray.peek( );
		assertEquals( "No se agregaron los elementos como se debería. El elemento mayor debería estar en el tope del Heap", numMay, mayor );
		
		cont = 0;
		while( !heapArray.isEmpty( ) )
		{
			Integer num = heapArray.poll( );
			assertTrue( "No se agregaron los elementos correctamente", mayor >= num );
			mayor = num;
		}
	}
	
	public void testPeek( )
	{
		int numMay = 0;
		for( int i = 0; i < 100; i++ )
		{
			int numRand = r.nextInt( 200 );
			numMay = Math.max( numMay, numRand );
			heapArray.add( numRand );
		}
		int mayor = heapArray.peek( );
		assertEquals( "El elemento retornado no es el mayor", numMay, mayor );
		assertEquals( "El elemento mayor debía permanecer en el tope del Heap", numMay, mayor );
		assertEquals( "No se debió eliminar el elemento", 100, heapArray.size( ) );
	}
	
	public void testPoll( )
	{
		int numMay = 0;
		int cont = 0;
		for( int i = 0; i < 100; i++ )
		{
			int numRand = r.nextInt( 200 );
			numMay = Math.max( numMay, numRand );
			heapArray.add( numRand );
		}
		int mayor = heapArray.peek( );
		assertEquals( "El elemento retornado no es el mayor", numMay, mayor );
		
		while( !heapArray.isEmpty( ) )
		{
			Integer num = heapArray.poll( );
			assertTrue( "El elemento retornado es mayor a uno retornado previamente", mayor >= num );
			assertEquals( "No se elimino el elemento", 100 - ++cont, heapArray.size( ) );
			mayor = num;
		}
	}
}