package dnarvaez27.collections.tree;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.elements.Entry;
import dnarvaez27.collections.elements.NodoBinarioKV;
import dnarvaez27.collections.list.Queue;

/**
 * Clase abstracta que define las funcionalidades compartidas
 * para estructuras arbolecentes con entradas tipo Llave-Valor
 * 
 * @author dnarvaez27
 * @param <K> Tipo de las Llaves de las entradas
 * @param <V> Tipo de los Valores de las entradas
 */
public abstract class AbstractTree<K, V> implements ICollection, ITree<K, V>
{
	/**
	 * Atributo de clase de la llave, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<K> classKey;
	
	/**
	 * Atributo de clase del valor, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<V> classValue;
	
	/**
	 * Raiz del arbol
	 */
	protected NodoBinarioKV<K, V> root;
	
	/**
	 * Inicializa la raiz del arbol
	 */
	public AbstractTree( )
	{
		this.root = null;
	}
	
	/**
	 * Para poder usar este metodo, es <u><b>OBLIGATORIO</b></u> haber inicializado las clases por medio de
	 * {@link #setClasses(Class, Class)} o en el constructor {@link RedBlackTree#RedBlackTree(Class, Class)}
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
		
		add( classKey.cast( values[ 0 ] ), classValue.cast( values[ 1 ] ) );
	}
	
	@Override
	public void clear( )
	{
		root = null;
	}
	
	/**
	 * Elimina una tupla Llave-Valor del arbol
	 *
	 * @param key Llave a eliminar
	 * @return El valor de la llave eliminada, null si no encontró la llave
	 */
	@Override
	public V delete( K key )
	{
		if( key == null )
		{
			throw new NullPointerException( "La llave no puede ser null" );
		}
		
		if( root != null )
		{
			return root.delete( key );
		}
		return null;
	}
	
	/**
	 * Retorna un Iterable de los elementos del arbol <b>In-Orden</b>
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public Iterable<Entry<K, V>> elements( )
	{
		Queue<Entry<K, V>> queue = new Queue<>( );
		if( root != null )
		{
			queue = ( Queue<Entry<K, V>> ) root.elements( );
		}
		return queue;
	}
	
	/**
	 * Obtiene el valor de la tupla con la llave dada por parámetro
	 *
	 * @param key Llave de la tupla a buscar
	 * @return Retorna el valor de la tupla con la llave dada por parametro
	 */
	@Override
	public V get( K key )
	{
		if( key == null )
		{
			throw new NullPointerException( "La llave no puede ser null" );
		}
		
		if( root != null )
		{
			return root.get( key );
		}
		return null;
	}
	
	/**
	 * Retorna la altura del Heap
	 *
	 * @return Altura del Heap
	 */
	@Override
	public int height( )
	{
		int height = 0;
		if( root != null )
		{
			height = root.height( );
		}
		return height;
	}
	
	/**
	 * Verifica si el arbol esta vacio o no
	 *
	 * @return True si el arbol está vacio, False de lo contrario
	 */
	@Override
	public boolean isEmpty( )
	{
		return root == null;
	}
	
	/**
	 * Establece las clases a utilizar en el uso de varargs {@link #add(Object...)}
	 *
	 * @param classKey Clase de las llaves
	 * @param classValue Clase de los valores
	 */
	@Override
	public void setClasses( Class<K> classKey, Class<V> classValue )
	{
		if( ( classKey == null ) || ( classValue == null ) )
		{
			throw new NullPointerException( "Los valores no pueden ser null" );
		}
		this.classKey = classKey;
		this.classValue = classValue;
	}
	
	/**
	 * Retorna la cantidad de elementos en el arbol
	 *
	 * @return Cantidad de elementos en el arbol
	 */
	@Override
	public int size( )
	{
		if( root != null )
		{
			return root.size( );
		}
		return 0;
	}
	
	/**
	 * Retorna una representacion del arbol
	 */
	@Override
	public String toString( )
	{
		if( root != null )
		{
			return root.toStringTree( );
		}
		return "--Plant a Tree--";
	}
}