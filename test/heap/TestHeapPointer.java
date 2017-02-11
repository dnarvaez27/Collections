package heap;

import java.util.Random;

import dnarvaez27.collections.heap.HeapPointer;
import junit.framework.TestCase;

public class TestHeapPointer extends TestCase
{
	private HeapPointer<Integer> heapPointer;
	
	private final Random r = new Random( );
	
	protected void setUp( )
	{
		heapPointer = new HeapPointer<>( );
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
			heapPointer.add( numRand );
			assertEquals( "No se agrego el elemento", ++cont, heapPointer.size( ) );
		}
		mayor = heapPointer.peek( );
		assertEquals( "No se agregaron los elementos como se debería. El elemento mayor debería estar en el tope del Heap", numMay, mayor );
		
		cont = 0;
		while( !heapPointer.isEmpty( ) )
		{
			Integer num = heapPointer.poll( );
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
			heapPointer.add( numRand );
		}
		int mayor = heapPointer.peek( );
		assertEquals( "El elemento retornado no es el mayor", numMay, mayor );
		assertEquals( "El elemento mayor debía permanecer en el tope del Heap", numMay, mayor );
		assertEquals( "No se debió eliminar el elemento", 100, heapPointer.size( ) );
	}
	
	public void testPoll( )
	{
		int numMay = 0;
		int cont = 0;
		int mayor = 0;
		
		for( int i = 0; i < 100; i++ )
		{
			int numRand = r.nextInt( 200 );
			numMay = Math.max( numMay, numRand );
			heapPointer.add( numRand );
		}
		mayor = heapPointer.peek( );
		assertEquals( "El elemento retornado no es el mayor", numMay, mayor );
		while( !heapPointer.isEmpty( ) )
		{
			Integer num = heapPointer.poll( );
			assertTrue( "El elemento retornado es mayor a uno retornado previamente", mayor >= num );
			assertEquals( "No se elimino el elemento", 100 - ++cont, heapPointer.size( ) );
			mayor = num;
		}
	}
}
