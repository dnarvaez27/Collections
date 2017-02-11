package dnarvaez27.collections.hashtable;

import dnarvaez27.collections.elements.Entry;

/**
 * Clase abstracta que define las funcionalidades en común de las HashTables
 * 
 * @author dnarvaez27
 * @param <N> Tipo de los elementos del arreglo que implementa la HashTable
 * @param <K> Tipo de las llaves de las entradas de la HashTable
 * @param <V> Tipo de los valores de las entradas de la HashTable
 */
public abstract class AbstractHashTable<N, K, V> implements IHashTable<K, V>
{
	/**
	 * Arreglo que representa la HashTable
	 */
	protected N[ ] arreglo;
	
	/**
	 * Factor de Carga de la HashTable
	 */
	protected float loadFactor;
	
	/**
	 * Atributo de clase de la llave, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<K> classKey;
	
	/**
	 * Atributo de clase del valor, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<V> classValue;
	
	/**
	 * Cantidad de elementos de la HashTable
	 */
	protected int size;
	
	private int capacidad;
	
	/**
	 * Inicializa la capacidad y el factor de carga de la HashTable
	 * 
	 * @param capacidad Capacidad inicial del arreglo que implementa la HashTable
	 * @param loadFactor Factor de Carga que se asignara al HashTable
	 */
	public AbstractHashTable( int capacidad, float loadFactor )
	{
		this.capacidad = capacidad;
		this.loadFactor = loadFactor;
	}
	
	/**
	 * Para poder usar este metodo, es <u><b>OBLIGATORIO</b></u> haber inicializado las clases por medio de
	 * {@link #setClasses(Class, Class)} o en el constructor {@link dnarvaez27.tree.RedBlackTree#RedBlackTree(Class, Class)}
	 *
	 * @param values Tupla Llave-valor
	 * @throws NullPointerException Si no se han inicializado las classes
	 * @throws IllegalArgumentException Si el numero de parametros no es el correcto
	 */
	@Override
	public void addElements( Object ... values )
	{
		if( ( classValue == null ) || ( classKey == null ) )
		{
			throw new NullPointerException( "classes must be initialized. Use setClasses( Class, Class ) or the constructor RedBlackTree( Class, Class )" );
		}
		if( values.length != 2 )
		{
			throw new IllegalArgumentException( "There must be 2 elements" );
		}
		
		put( classKey.cast( values[ 0 ] ), classValue.cast( values[ 1 ] ) );
	}
	
	@Override
	public boolean isEmpty( )
	{
		return size == 0;
	}
	
	/**
	 * Establece las clases a utilizar en el uso de varargs {@link #add(Object...)}
	 *
	 * @param classKey Clase de las llaves
	 * @param classValue Clase de los valores
	 */
	public void setClasses( Class<K> classKey, Class<V> classValue )
	{
		this.classKey = classKey;
		this.classValue = classValue;
	}
	
	/**
	 * Retorna el indice en el arreglo donde pertenece la llave dada por parametro
	 *
	 * @param key Llave de la cual se calculará el HashCode
	 * @return Indice en el arreglo donde pertenece la llave dada por parametro
	 */
	protected int hash( K key )
	{
		return ( key.hashCode( ) & 0x7FFFFFFF ) % arreglo.length;
	}
	
	@Override
	public int size( )
	{
		return size;
	}
	
	public Iterable<Entry<K, V>> elements( )
	{
		return entries( );
	}
	
	@SuppressWarnings( "unchecked" )
	public void clear( )
	{
		size = 0;
		arreglo = ( N[ ] ) new Object[ capacidad ];
	}
}