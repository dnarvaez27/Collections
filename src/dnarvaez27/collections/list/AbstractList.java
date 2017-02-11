package dnarvaez27.collections.list;

import java.util.Iterator;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.elements.INodoLineal;
import dnarvaez27.collections.elements.NodoLineal;
import dnarvaez27.collections.list.linkedlist.DoubleLinkedList;

public abstract class AbstractList<T> implements ICollection, IList<T>
{
	/**
	 * Clase que modela el Iterador de la Lista Doblemente Encadenada
	 *
	 * @author dnarvaez27
	 */
	private class IteratorLL implements Iterator<T>
	{
		/**
		 * Nodo actual de la iteracion
		 */
		private INodoLineal<T> nodo;
		
		/**
		 * Construye un iterador con el nodo de inicio dado por parametro
		 *
		 * @param nodo Nodo de inicio
		 */
		public IteratorLL( INodoLineal<T> nodo )
		{
			this.nodo = nodo;
		}
		
		@Override
		public boolean hasNext( )
		{
			return nodo != null;
		}
		
		@Override
		public T next( )
		{
			T elem = nodo.getElement( );
			nodo = nodo.getNext( );
			return elem;
		}
		
		@Override
		public void remove( )
		{
			
		}
	}
	
	/**
	 * Atributo de clase de los elementos, utilizado para el uso de {@link #add(Object...)}
	 */
	protected Class<T> classElement;
	
	/**
	 * Cabeza de la lista. Primer elemento
	 */
	protected NodoLineal<T> head;
	
	/**
	 * Numero de elementos de la lista
	 */
	protected int size;
	
	public AbstractList( )
	{
		head = null;
	}
	
	/**
	 * Inicializa la lista y agrega los elementos de la lista dada por parametro
	 * 
	 * @param list Lista con elementos a ser agregados
	 */
	public AbstractList( IList<T> list )
	{
		this( );
		for( T t : list )
		{
			add( t );
		}
		// addAll( list );
	}
	
	/**
	 * Inicializa la lista y agrega los elementos dados por parametro
	 * 
	 * @param elements Elementos a ser agregados
	 */
	@SafeVarargs
	public AbstractList( T ... elements )
	{
		this( arrayToList( elements ) );
	}
	
	/**
	 * Retorna una lista a partir de los elementos dados por parametro
	 *
	 * @param elements Elementos a convertir en una Lista Doblemente Encadenada
	 * @param <T> Tipo de elementos
	 * @return Lista Doblemente Encadenada con los elementos dados por parametro
	 */
	@SafeVarargs
	public static <T> IList<T> arrayToList( T ... elements )
	{
		IList<T> lista = new DoubleLinkedList<>( );
		for( T t : elements )
		{
			lista.add( t );
		}
		return lista;
	}
	
	/**
	 * Para poder usar este metodo, es <u><b>OBLIGATORIO</b></u> haber inicializado las clases por medio de {@link #setClass(Class)}
	 * Agrega el o los elementos especificados por parametro
	 *
	 * @param values Valores a ser agregados al Heap
	 * @throws NullPointerException Si no se han inicializado la classe del elemento
	 */
	@Override
	public void addElements( Object ... values )
	{
		if( classElement == null )
		{
			throw new NullPointerException( "Class must be initialized. Use setClass( Class )" );
		}
		for( Object object : values )
		{
			add( classElement.cast( object ) );
		}
	}
	
	@Override
	public boolean addLast( T elem )
	{
		return add( elem );
	}
	
	@Override
	public void clear( )
	{
		size = 0;
		head = null;
	}
	
	@Override
	public boolean contains( T element )
	{
		INodoLineal<T> nodo = head;
		while( nodo != null )
		{
			if( nodo.getElement( ).equals( element ) )
			{
				return true;
			}
			nodo = nodo.getNext( );
		}
		return false;
	}
	
	@Override
	public Iterable<T> elements( )
	{
		return this;
	}
	
	@Override
	public T getFirst( )
	{
		T elemento = null;
		if( head != null )
		{
			elemento = head.getElement( );
		}
		return elemento;
	}
	
	@Override
	public int indexOf( T element )
	{
		int contador = 0;
		INodoLineal<T> nodo = head;
		while( nodo != null )
		{
			if( nodo.getElement( ).equals( element ) )
			{
				return contador;
			}
			contador++;
			nodo = nodo.getNext( );
		}
		return -1;
	}
	
	@Override
	public boolean isEmpty( )
	{
		return size == 0;
	}
	
	@Override
	public Iterator<T> iterator( )
	{
		IteratorLL iteratorDLL = new IteratorLL( head );
		return iteratorDLL;
	}
	
	@Override
	public boolean removeAll( IList<T> collection )
	{
		boolean removedAll = true;
		Iterator<T> iterator = collection.iterator( );
		while( iterator.hasNext( ) )
		{
			removedAll &= remove( iterator.next( ) );
		}
		return removedAll;
	}
	
	@Override
	public void setClass( Class<T> classElement )
	{
		this.classElement = classElement;
	}
	
	@Override
	public int size( )
	{
		return size;
	}
	
	@Override
	public Object[ ] toArray( )
	{
		Object[ ] array = new Object[ size ];
		INodoLineal<T> nodo = head;
		int contador = 0;
		while( nodo != null )
		{
			array[ contador++ ] = nodo.getElement( );
			nodo = nodo.getNext( );
		}
		return array;
	}
	
	@Override
	public String toString( )
	{
		StringBuilder toPrint = new StringBuilder( );
		toPrint.append( "[" );
		NodoLineal<T> nodo = head;
		while( nodo != null )
		{
			toPrint.append( nodo.getElement( ) );
			if( nodo.hasNext( ) )
			{
				toPrint.append( ", " );
			}
			nodo = nodo.getNext( );
		}
		toPrint.append( "]" );
		return toPrint.toString( );
	}
}