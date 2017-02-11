package dnarvaez27.collections.heap;

import java.util.Comparator;

import dnarvaez27.collections.list.Queue;

/**
 * Estructura de Heap implementado con arreglo
 *
 * @author d.narvaez11
 * @param <T> Tipo de Elementos a ser guardados en el Heap
 */
@SuppressWarnings( "unchecked" )
public class HeapArray<T extends Comparable<? super T>> extends AbstractHeap<T>
{
	/**
	 * Constante que define un "Nivel" de tamano para el Heap<br>
	 * Se establece como 1024 cada Nivel, dado que es un tamano estandar
	 * en diversas estructuras de datos
	 */
	private static int TAM = 1024;
	
	/**
	 * Arreglo contenedor de la información del Heap
	 */
	protected T[ ] data;
	
	/**
	 * Construye un Heap con el comparador predefinido de la clase<br>
	 * El Heap se construye con un tamano estbalecido por una constante
	 *
	 * @see #TAM
	 */
	public HeapArray( )
	{
		this( NORMAL, null );
	}
	
	/**
	 * Construye un Heap con un comparador dado por parametro<br>
	 * El Heap se construye con un tamano establecido por una constante
	 *
	 * @param comparator Comparador a usar en el Heap
	 * @see #TAM
	 */
	public HeapArray( Comparator<T> comparator )
	{
		this( NORMAL, comparator );
	}
	
	/**
	 * Construye un Heap utilizando una de las constantes de la clase: {@link IHeap#NORMAL} y {@link IHeap#REVERSE}
	 *
	 * @param orientation Orientecion del Heap utilizando las constantes de la clase
	 */
	public HeapArray( int orientation )
	{
		this( orientation, null );
	}
	
	/**
	 * Construye un Heap implementado con arreglo. Utilizando los valores dados por parametro.
	 * 
	 * @param orientation Orientacion del Heap. {@link IHeap#NORMAL} y {@link IHeap#REVERSE}
	 * @param comparator Comparador a utilizar en la clase
	 */
	private HeapArray( int orientation, Comparator<T> comparator )
	{
		super( orientation, comparator );
		data = ( T[ ] ) new Comparable[ TAM ];
	}
	
	@Override
	public void add( T elemento )
	{
		double porc = ( ( size * 100 ) / data.length );
		if( porc >= 80 )// Needs Resize
		{
			T[ ] nData = ( T[ ] ) new Comparable[ data.length + TAM ];
			System.arraycopy( data, 0, nData, 0, data.length );
			data = nData;
		}
		data[ size++ ] = elemento;
		swim( );
	}
	
	@Override
	public void clear( )
	{
		super.clear( );
		for( int i = 0; i < data.length; i++ )
		{
			data[ i ] = null;
		}
	}
	
	@Override
	public Iterable<T> elements( )
	{
		Queue<T> queue = new Queue<>( );
		for( T t : data )
		{
			if( t != null )
			{
				queue.enqueue( t );
			}
			else
			{
				break;
			}
		}
		return queue;
	}
	
	/**
	 * Retorna la altura de los nodos en la posicion dada por parametro
	 * 
	 * @param l Posicion del Nodo izquierdo
	 * @param r Posicion del Nodo derecho
	 * @return Altura de la jerarquia del nodo
	 */
	private int getHeight( int l, int r )
	{
		int heiL = 1;
		int heiR = 1;
		if( l < size )
		{
			int left = ( l * 2 ) + 1;
			int right = ( l + 1 ) * 2;
			heiL += getHeight( left, right );
		}
		if( ( r < size ) && ( r >= 0 ) )
		{
			int left = ( r * 2 ) + 1;
			int right = ( r + 1 ) * 2;
			heiR += getHeight( left, right );
		}
		return Math.max( heiL, heiR );
	}
	
	@Override
	public int height( )
	{
		int pos = 0;
		int left = ( pos * 2 ) + 1;
		int right = ( pos + 1 ) * 2;
		return getHeight( left, right );
	}
	
	private String paintHeap( int i, String pre, String tab )
	{
		StringBuilder sBuilder = new StringBuilder( );
		if( ( i < size ) && ( data[ i ] != null ) )
		{
			int left = ( i * 2 ) + 1;
			int right = ( i + 1 ) * 2;
			
			sBuilder.append( tab + pre + data[ i ].toString( ) + System.lineSeparator( ) );
			tab = tab.replace( "├", "│" );
			tab = tab.replace( "└", " " );
			
			tab += "     " + new String( new char[ data[ i ].toString( ).length( ) / 2 ] ).replace( '\0', ' ' );
			
			if( ( left < size ) && ( data[ left ] != null ) )
			{
				String t2 = "└";
				if( ( right < size ) && ( data[ right ] != null ) )
				{
					t2 = "├";
				}
				sBuilder.append( paintHeap( left, pre, tab + t2 ) );
			}
			if( ( right < size ) && ( data[ right ] != null ) )
			{
				sBuilder.append( paintHeap( right, pre, tab + "└" ) );
			}
		}
		return sBuilder.toString( );
	}
	
	@Override
	public T peek( )
	{
		return data[ 0 ];
	}
	
	@Override
	public T poll( )
	{
		T element = data[ 0 ];
		if( size > 0 )
		{
			int lastPos = size - 1;
			data[ 0 ] = lastPos != 0 ? data[ lastPos ] : null;
			data[ lastPos ] = null;
			size--;
			
			if( data.length > TAM )// is able to be resized
			{
				double porc = ( ( size ) * 100 ) / ( data.length - TAM );
				if( porc < 80 )
				{
					T[ ] nData = ( T[ ] ) new Comparable[ data.length - TAM ];
					System.arraycopy( data, 0, nData, 0, nData.length - 1 );
					data = nData;
				}
			}
			sink( );
		}
		return element;
	}
	
	/*
	 * Reubica los elementos en la jerarquia cuya posicion deba ser en un nivel inferior
	 */
	protected void sink( )
	{
		int pos = 0;
		while( pos < data.length )
		{
			if( data[ pos ] != null )
			{
				int left = ( pos * 2 ) + 1;
				int right = ( pos + 1 ) * 2;
				
				boolean leftLim = left < data.length;
				boolean rightLim = right < data.length;
				boolean posLim = pos < data.length;
				boolean exists4Left = posLim && leftLim && ( data[ pos ] != null ) && ( data[ left ] != null );
				boolean leftGreater = exists4Left && ( comparator != null ? comparator.compare( data[ pos ], data[ left ] ) < 0 : data[ pos ].compareTo( data[ left ] ) < 0 );
				boolean exists4Right = posLim && rightLim && ( data[ pos ] != null ) && ( data[ right ] != null );
				boolean rightGreater = exists4Right && ( comparator != null ? comparator.compare( data[ pos ], data[ right ] ) < 0 : data[ pos ].compareTo( data[ right ] ) < 0 );
				boolean maxLeft = exists4Left && exists4Right && ( comparator != null ? comparator.compare( data[ right ], data[ left ] ) < 0 : data[ right ].compareTo( data[ left ] ) < 0 );
				boolean rightNull = rightLim && ( data[ right ] == null );
				int greater = leftGreater && ( maxLeft || rightNull ) ? left : rightGreater ? right : -1;
				if( greater != -1 )
				{
					T temp = data[ pos ];
					data[ pos ] = data[ greater ];
					data[ greater ] = temp;
					pos = greater;
					continue;
				}
				else
				{
					break;
				}
			}
			pos++; // Se puede borrar (?)
		}
	}
	
	/**
	 * Reubica los elementos en la jerarquia cuya posicion deba ser en un nivel superior
	 */
	protected void swim( )
	{
		int pos = size - 1;
		while( pos > 0 )
		{
			if( data[ pos ] != null )
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
				else
				{
					break;
				}
			}
			pos--;// Se puede borrar (?)
		}
	}
	
	/**
	 * Retorna un String como una representacion de estructura arbolecente que representa el Heap
	 *
	 * @return String como una representacion de estructura arbolecente que representa el Heap
	 */
	@Override
	public String toString( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		sBuilder.append( paintHeap( 0, "─────", "" ) );
		
		return sBuilder.toString( );
	}
	
	/**
	 * Retorna un String como una representacion del Heap como un arreglo
	 * 
	 * @return String como una representacion del Heap como un arreglo
	 */
	public String toStringArray( )
	{
		StringBuilder sBuilder = new StringBuilder( "[" );
		
		for( int i = 0; i < data.length; i++ )
		{
			if( data[ i ] == null )
			{
				break;
			}
			sBuilder.append( data[ i ] );
			if( ( i < ( data.length - 1 ) ) && ( data[ i + 1 ] != null ) )
			{
				sBuilder.append( ", " );
			}
		}
		sBuilder.append( "]" );
		return sBuilder.toString( );
	}
}