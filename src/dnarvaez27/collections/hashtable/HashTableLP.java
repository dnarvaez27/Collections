package dnarvaez27.collections.hashtable;

import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.hashtable.HashTableLP.NodoHash;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

/**
 * Estructura de HashTable implementado con LinearProbing
 *
 * @author dnarvaez27
 * @param <K> Tipo de las llaves
 * @param <V> Tipo de los valores
 */
@SuppressWarnings(
{
		"unchecked",
		"rawtypes",
} )
public class HashTableLP<K, V> extends AbstractHashTable<NodoHash, K, V>
{
	/**
	 * Clase que modela un Nodo de la HashTable<br>
	 * Este nodo se constituye por una tupla Llave-Valor y una marca asociada
	 *
	 * @author dnarvaez27
	 */
	public class NodoHash extends Entry<K, V>
	{
		/**
		 * Marca del nodo<br>
		 * Establece si en este nodo existia o no un elemento antes
		 */
		private boolean marked;
		
		/**
		 * Construye un nodo a partir de la informacion dada por parametro
		 *
		 * @param key Llave del nodo
		 * @param value Valor del nodo
		 */
		public NodoHash( K key, V value )
		{
			super( key, value );
			marked = false;
		}
		
		/**
		 * Verifica si el nodo esta marcado o no
		 *
		 * @return True si esta marcado, False de lo contrario
		 */
		public boolean isMarked( )
		{
			return marked;
		}
		
		/**
		 * Reinicia los valores del elemento del nodo: Llave y Valor<br>
		 */
		public void reset( )
		{
			key = null;
			value = null;
		}
		
		/**
		 * Establece la marca del nodo
		 *
		 * @param marked Nuevo valor de la marca del nodo
		 */
		public void setMarked( boolean marked )
		{
			this.marked = marked;
		}
		
		/**
		 * Retorna una representacion del nodo
		 */
		@Override
		public String toString( )
		{
			return key != null ? key + ":" + value : null;
		}
	}
	
	/**
	 * Tamaño inicial del arreglo
	 */
	private static final int TAMANO = 20;
	
	/**
	 * Numero de "Flags" o marcas en el arreglo
	 */
	private int flags;
	
	/**
	 * Construye un HashTable con un tamaño definido por la constante {@link #TAMANO} y un factor de carga de 0.75
	 */
	public HashTableLP( )
	{
		this( TAMANO, 0.75f );
	}
	
	/**
	 * Construye un HashTable con un tamaño y factor de carga definidos
	 *
	 * @param size Tamaño del arreglo
	 * @param loadFactor Factor de carga
	 */
	public HashTableLP( int size, float loadFactor )
	{
		super( size, loadFactor );
		arreglo = new HashTableLP.NodoHash[ size ];
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
		if( ( arreglo[ index ] != null ) && arreglo[ index ].isMarked( ) )
		{
			flags--;
		}
		arreglo[ index ] = new NodoHash( key, value );
		size++;
		verificarLoadFactor( true );
	}
	
	@Override
	public V delete( K key )
	{
		int index = hash( key );
		
		while( ( arreglo[ index ] != null ) && ( arreglo[ index ].isMarked( ) || !arreglo[ index ].getKey( ).equals( key ) ) )
		{
			index++;
			if( index >= arreglo.length )
			{
				index = 0;
			}
		}
		if( ( arreglo[ index ] != null ) && arreglo[ index ].getKey( ).equals( key ) )
		{
			V value = ( V ) arreglo[ index ].getValue( );
			arreglo[ index ].setMarked( true );
			arreglo[ index ].reset( );
			flags++;
			size--;
			verificarLoadFactor( false );
			verificarFlags( );
			return value;
		}
		return null;
	}
	
	@Override
	public DoubleLinkedList<Entry<K, V>> entries( )
	{
		DoubleLinkedList<Entry<K, V>> list = new DoubleLinkedList<>( );
		for( NodoHash nodoHash : arreglo )
		{
			if( ( nodoHash != null ) && !nodoHash.isMarked( ) )
			{
				list.add( nodoHash );
			}
		}
		return list;
	}
	
	public DoubleLinkedList<V> values( )
	{
		DoubleLinkedList<V> list = new DoubleLinkedList<>( );
		for( NodoHash nodoHash : arreglo )
		{
			if( ( nodoHash != null ) && !nodoHash.isMarked( ) )
			{
				list.add( nodoHash.getValue( ) );
			}
		}
		return list;
	}
	
	public Entry<K, V> getEntry( K key )
	{
		int index = hash( key );
		
		while( ( arreglo[ index ] != null ) && ( arreglo[ index ].isMarked( ) || !arreglo[ index ].getKey( ).equals( key ) ) )
		{
			index++;
			if( index >= arreglo.length )
			{
				index = 0;
			}
		}
		if( ( arreglo[ index ] != null ) && arreglo[ index ].getKey( ).equals( key ) )
		{
			return arreglo[ index ];
		}
		return null;
	}
	
	@Override
	public V get( K key )
	{
		int index = hash( key );
		
		while( ( arreglo[ index ] != null ) && ( arreglo[ index ].isMarked( ) || !arreglo[ index ].getKey( ).equals( key ) ) )
		{
			index++;
			if( index >= arreglo.length )
			{
				index = 0;
			}
		}
		if( ( arreglo[ index ] != null ) && arreglo[ index ].getKey( ).equals( key ) )
		{
			return ( V ) arreglo[ index ].getValue( );
		}
		return null;
	}
	
	@Override
	public V put( K key, V value )
	{
		int index = hash( key );
		while( ( arreglo[ index ] != null ) && !arreglo[ index ].isMarked( ) )
		{
			if( arreglo[ index ].getKey( ).equals( key ) )
			{
				arreglo[ index ].setValue( value );
				return null;
			}
			index++;
			if( index >= arreglo.length )
			{
				index = 0;
			}
		}
		agregarEntrada( index, key, value );
		return null;
	}
	
	/**
	 * Realiza un rehash de la HashTable. Reubicando las entradas<br>
	 *
	 * @param op Opcion de rehash:
	 *        <ol>
	 *        <li>Incrementa el tamaño
	 *        <li>Reduce el tamaño
	 *        <li>Mantiene el tamaño, <i>Remueve los Flags</i>
	 *        </ol>
	 */
	private void rehash( int op )
	{
		DoubleLinkedList<NodoHash> lista = new DoubleLinkedList<>( );
		for( NodoHash nodoHash : arreglo )
		{
			if( ( nodoHash != null ) && !nodoHash.isMarked( ) )
			{
				lista.add( nodoHash );
			}
		}
		size = 0;
		flags = 0;
		int newSize = arreglo.length;
		if( op == 1 )
		{
			newSize = arreglo.length + TAMANO;
		}
		else if( op == 2 )
		{
			newSize = arreglo.length - TAMANO;
		}
		arreglo = new HashTableLP.NodoHash[ newSize ];
		for( NodoHash nodoHash : lista )
		{
			put( nodoHash.getKey( ), nodoHash.getValue( ) );
		}
	}
	
	/**
	 * Retorna una representacion de la HashTable<br>
	 * El proposito de esta representacion es por Debug o
	 * interés del almacenamiento de los datos en la HashTable<br>
	 * <ul>
	 * <li>Se representa con |X| los valores vacios o nulos
	 * <li>Se representa con |M| los valores marcados
	 * <li>Se representa con <i>llave:valor</i> los nodos que contengan entradas
	 * </ul>
	 */
	@Override
	public String toString( )
	{
		StringBuilder sBuilder = new StringBuilder( "[" );
		
		int i = 0;
		for( NodoHash nodoHash : arreglo )
		{
			if( nodoHash != null )
			{
				sBuilder.append( !nodoHash.isMarked( ) ? nodoHash.toString( ) : "|M|" );
			}
			else
			{
				sBuilder.append( "|X|" );
			}
			if( ++i < arreglo.length )
			{
				sBuilder.append( ", " );
			}
		}
		sBuilder.append( "]" );
		return sBuilder.toString( );
	}
	
	/**
	 * Realiza una verificacion de flags.<br>
	 * Si estos han superado una cota se realiza un rehash manteniendo el tamaño
	 */
	private void verificarFlags( )
	{
		float porcFlags = ( float ) flags / ( float ) arreglo.length;
		if( ( flags >= size ) || ( porcFlags >= 0.3f ) )
		{
			rehash( 3 );
		}
	}
	
	/**
	 * Verifica el factor de carga de la HashTable dependiendo de la operacion realizada
	 *
	 * @param add True si se verifica despues de una insercion, False si se verifica despues de una remoción
	 */
	private void verificarLoadFactor( boolean add )
	{
		if( add )
		{
			float conUp = ( float ) size / ( float ) arreglo.length;
			if( conUp > loadFactor )
			{
				rehash( 1 );
			}
		}
		else
		{
			float conDown = ( ( float ) size ) / ( ( float ) ( arreglo.length - TAMANO ) );
			if( ( conDown > 0 ) && ( conDown < loadFactor ) )
			{
				rehash( 2 );
			}
		}
	}
}