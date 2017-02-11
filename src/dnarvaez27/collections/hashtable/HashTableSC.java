package dnarvaez27.collections.hashtable;

import java.text.DecimalFormat;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.list.Queue;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

/**
 * Estructura de HashTable implementada con Separate-Chaining
 *
 * @author dnarvaez27
 * @param <K> Tipo de las llaves
 * @param <V> Tipo de los valores
 */
@SuppressWarnings( "unchecked" )
public class HashTableSC<K, V> extends AbstractHashTable<DoubleLinkedList<Entry<K, V>>, K, V>
{
	/**
	 * Tamaño inicial del arreglo
	 */
	private static int TAMANO = 11;
	
	/**
	 * Construye un HashTable con un tamaño definido por la constante {@link #TAMANO} y un factor de carga de 8
	 */
	public HashTableSC( )
	{
		this( TAMANO, 8f );
	}
	
	/**
	 * Construye un HashTable con un tamaño y factor de carga definidos
	 *
	 * @param capacidad Tamaño del arreglo
	 * @param loadFactor Factor de carga
	 */
	public HashTableSC( int capacidad, float loadFactor )
	{
		super( capacidad, loadFactor );
		this.arreglo = new DoubleLinkedList[ capacidad ];
	}
	
	/**
	 * Agrega una entrada a la tabla de Hash. Esta entrada debe ser única<br>
	 * <b>pre:</b> La llave no se encuentra en la HashTable
	 *
	 * @param index Indice del arreglo donde debe ir la entrada
	 * @param key Llave asociada a la entrada
	 * @param value Valor asociado a la entrada
	 */
	private void agregarEntrada( int index, K key, V value )
	{
		if( arreglo[ index ] == null )
		{
			arreglo[ index ] = new DoubleLinkedList<>( );
		}
		Entry<K, V> nuevo = new Entry<>( key, value );
		arreglo[ index ].add( nuevo );
		size++;
		verificarLoadFactor( true );
	}
	
	@Override
	public V delete( K key )
	{
		V element = null;
		int index = hash( key );
		if( arreglo[ index ] != null )
		{
			int indexToRemove = -1;
			int indexActual = 0;
			for( Entry<K, V> nodo : arreglo[ index ] )
			{
				if( nodo.getKey( ).equals( key ) )
				{
					element = nodo.getValue( );
					indexToRemove = indexActual;
					break;
				}
				indexActual++;
			}
			if( indexToRemove != -1 )
			{
				arreglo[ index ].remove( indexToRemove );
				size--;
			}
		}
		verificarLoadFactor( false );
		return element;
	}
	
	@Override
	public Queue<Entry<K, V>> entries( )
	{
		Queue<Entry<K, V>> queue = new Queue<>( );
		for( DoubleLinkedList<Entry<K, V>> doubleLinkedList : arreglo )
		{
			if( doubleLinkedList != null )
			{
				for( Entry<K, V> nodoHash : doubleLinkedList )
				{
					queue.enqueue( nodoHash );
				}
			}
		}
		return queue;
	}
	
	@Override
	public V get( K key )
	{
		int index = hash( key );
		if( arreglo[ index ] != null )
		{
			for( Entry<K, V> nodo : arreglo[ index ] )
			{
				if( nodo.getKey( ).equals( key ) )
				{
					return nodo.getValue( );
				}
			}
		}
		return null;
	}
	
	@Override
	public V put( K key, V value )
	{
		int index = hash( key );
		
		if( arreglo[ index ] != null )
		{
			for( Entry<K, V> nodo : arreglo[ index ] )
			{
				if( nodo.getKey( ).equals( key ) )
				{
					V old = nodo.getValue( );
					nodo.setValue( value );
					return old;
				}
			}
		}
		
		agregarEntrada( index, key, value );
		return null;
	}
	
	/**
	 * Realiza un rehash de la HashTable. Reubicando las entradas<br>
	 *
	 * @param op True para Incrementa el tamaño, False para Reduce el tamaño
	 */
	private void rehash( boolean op )
	{
		int newCapacity = op ? arreglo.length + TAMANO : arreglo.length - TAMANO;
		DoubleLinkedList<Entry<K, V>> elements = new DoubleLinkedList<>( );
		for( DoubleLinkedList<Entry<K, V>> list : arreglo )
		{
			if( list != null )
			{
				elements.addAll( list );
			}
		}
		newCapacity = newCapacity < TAMANO ? TAMANO : newCapacity;
		arreglo = new DoubleLinkedList[ newCapacity ];
		size = 0;
		for( Entry<K, V> nodoHash : elements )
		{
			int index = hash( nodoHash.getKey( ) );
			agregarEntrada( index, nodoHash.getKey( ), nodoHash.getValue( ) );
		}
	}
	
	/**
	 * Retorna una representacion de la HashTable<br>
	 * El proposito de esta representacion es por Debug o
	 * interés del almacenamiento de los datos en la HashTable<br>
	 * <ul>
	 * <li>Se representa en la tabla un espacio vacio los valores vacios o nulos
	 * <li>Se representa en la tabla con <i>llave:valor</i> los nodos que contengan entradas
	 * </ul>
	 */
	@Override
	public String toString( )
	{
		StringBuilder sBuilder = new StringBuilder( );
		
		int cols = 1;
		for( DoubleLinkedList<Entry<K, V>> linkedList : arreglo )
		{
			if( linkedList != null )
			{
				cols = Math.max( linkedList.size( ), cols );
			}
		}
		
		int posArreglo = 0;
		DecimalFormat format = new DecimalFormat( "000" );
		for( int j = 0; j < 4; j++ )
		{
			if( j == 0 )
			{
				sBuilder.append( "_____________" );
			}
			else if( j == 3 )
			{
				sBuilder.append( "││_________││" );
			}
			else if( j == 2 )
			{
				sBuilder.append( "││   POS   ││" );
			}
			else
			{
				sBuilder.append( "││         ││" );
			}
			for( int i = 0; i < cols; i++ )
			{
				if( j == 0 )
				{
					sBuilder.append( "__________________" );
				}
				else if( j == 3 )
				{
					sBuilder.append( "________________││" );
				}
				else if( j == 2 )
				{
					sBuilder.append( "     " + format.format( i + 1 ) + " COL    ││" );
				}
				else
				{
					sBuilder.append( "                ││" );
				}
			}
			sBuilder.append( "\n" );
		}
		int line = 16;
		for( DoubleLinkedList<Entry<K, V>> linkedList : arreglo )
		{
			sBuilder.append( "││   " + format.format( posArreglo++ ) + "   ││ " );
			if( linkedList != null )
			{
				for( Entry<K, V> nodoHash : linkedList )
				{
					String data = nodoHash.toString( );
					if( data.length( ) < line )
					{
						int cant = ( line / 2 ) - 1 - ( data.length( ) / 2 );
						String space = "                    ".substring( 0, cant );
						String def = space + data + space;
						def += "                     ".substring( 0, line - 1 - def.length( ) );
						sBuilder.append( def + "││ " );
					}
					else
					{
						sBuilder.append( data.substring( 0, line - 5 ) + "... ││ " );
					}
				}
				for( int i = linkedList.size( ); i < cols; i++ )
				{
					sBuilder.append( "               ││ " );
				}
			}
			else
			{
				for( int i = 0; i < cols; i++ )
				{
					sBuilder.append( "               ││ " );
				}
			}
			sBuilder.append( "\n" );
		}
		
		sBuilder.append( "┴┴─────────┴┴" );
		for( int i = 0; i < cols; i++ )
		{
			sBuilder.append( "────────────────┴┴" );
			
		}
		sBuilder.append( "\n" );
		
		return sBuilder.toString( );
	}
	
	/**
	 * Verifica el factor de carga de la HashTable dependiendo de la operacion realizada
	 *
	 * @param add True si se verifica despues de una insercion, False si se verifica despues de una remoción
	 */
	private void verificarLoadFactor( boolean add )
	{
		float cap = size / arreglo.length;
		if( add )
		{
			if( cap >= loadFactor )
			{
				rehash( true );
			}
		}
		else
		{
			if( cap > 2f )
			{
				rehash( false );
			}
		}
	}
}