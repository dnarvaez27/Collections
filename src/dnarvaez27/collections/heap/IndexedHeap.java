package dnarvaez27.collections.heap;

import java.util.Comparator;

public class IndexedHeap<T extends Comparable<? super T>> extends HeapArray<T>
{
	public IndexedHeap( )
	{
		super( );
	}
	
	public IndexedHeap( Comparator<T> comparator )
	{
		super( comparator );
	}
	
	public IndexedHeap( int orientation )
	{
		super( orientation );
	}
	
	public void set( T oldElem, T newElem )
	{
		for( int i = 0; i < data.length; i++ )
		{
			if( data[ i ] != null && data[ i ].equals( oldElem ) )
			{
				data[ i ] = newElem;
				swim( i );
				return;
			}
		}
		// IF doesnt exits
		super.add( newElem );
	}
	
	public boolean contains( T element )
	{
		for( T t : data )
		{
			if( t.equals( element ) )
			{
				return true;
			}
		}
		return false;
	}
	
	public void swim( int pos )
	{
		while( pos > 0 )
		{
			boolean isRight = ( pos % 2 ) == 0;
			int padre = isRight ? ( pos / 2 ) - 1 : ( pos - 1 ) / 2;
			boolean greater = comparator != null ? ( comparator.compare( data[ pos ], data[ padre ] ) > 0 ) : ( data[ pos ].compareTo( data[ padre ] ) > 0 );
			if( greater )
			{
				T temp = data[ pos ];
				data[ pos ] = data[ padre ];
				data[ padre ] = temp;
				pos = padre;
				continue;
			}
			break;
		}
	}
}
