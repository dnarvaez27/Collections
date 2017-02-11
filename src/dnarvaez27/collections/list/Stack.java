package dnarvaez27.collections.list;

import java.util.Iterator;

import dnarvaez27.collections.ICollection;
import dnarvaez27.collections.list.linkedlist.SimpleLinkedList;

/**
 * LIFO Stack Data Structure
 *
 * @author dnarvaez27
 * @param <T> Tipo de los elementos
 */
public class Stack<T> implements Iterable<T>, ICollection
{
	/**
	 * Lista implementada para el Stack
	 */
	private SimpleLinkedList<T> lista;
	
	/**
	 * Construye un Stack
	 */
	public Stack( )
	{
		lista = new SimpleLinkedList<>( );
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
		lista.addElements( values );
	}
	
	@Override
	public void clear( )
	{
		lista.clear( );
	}
	
	/**
	 * Verifica si el elemento dado por parametro esta en el Stack
	 *
	 * @param element Elemento de interes
	 * @return True si el elemento esta en el Queue, False de lo contrario
	 */
	public boolean contains( T element )
	{
		return lista.contains( element );
	}
	
	@Override
	public Iterable<T> elements( )
	{
		return this;
	}
	
	/**
	 * Verifica si el Stack esta vacio o no
	 *
	 * @return True si el Queue esta vacio, False de lo contrario
	 */
	@Override
	public boolean isEmpty( )
	{
		return lista.isEmpty( );
	}
	
	/**
	 * Retorna un Iterator para recorrer los elementos del Queue
	 */
	@Override
	public Iterator<T> iterator( )
	{
		return lista.iterator( );
	}
	
	/**
	 * Retorna el primer elemento del Stack
	 *
	 * @return Primer elemento del Stack
	 */
	public T peek( )
	{
		return lista.getFirst( );
	}
	
	/**
	 * Elimina y Retorna el elemento superior del Stack
	 *
	 * @return Elemento superior del Stack
	 */
	public T pop( )
	{
		return lista.removeFirst( );
	}
	
	/**
	 * Agrega un elemento en la parte superior del Stack
	 *
	 * @param element Elemento a agregar
	 */
	public void push( T element )
	{
		lista.addFirst( element );
	}
	
	/**
	 * Establece las clases a utilizar en el uso de varargs add(Object)
	 *
	 * @param classElement Clase de los elementos
	 */
	public void setClass( Class<T> classElement )
	{
		lista.setClass( classElement );
	}
	
	public Stack<T> reverse( )
	{
		Stack<T> reversed = new Stack<>( );
		for( T t : lista )
		{
			reversed.push( t );
		}
		return reversed;
	}
	
	/**
	 * Retorna el numero de elementos en el Stack
	 *
	 * @return Numero de elementos en el Stack
	 */
	@Override
	public int size( )
	{
		return lista.size;
	}
	
	/**
	 * Retorna una representacion del Stack
	 */
	@Override
	public String toString( )
	{
		StringBuffer toPrint = new StringBuffer( "-> " );
		Iterator<T> it = lista.iterator( );
		while( it.hasNext( ) )
		{
			T elem = it.next( );
			toPrint.append( elem );
			if( it.hasNext( ) )
			{
				toPrint.append( " \n   " );
			}
		}
		return toPrint.toString( );
	}
}